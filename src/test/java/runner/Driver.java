package runner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.hamcrest.Matcher;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;

import utils.HelperUtil;

public class Driver{
	
	private String uri;
	private ValidatableResponse response;
	private String contentType;
	private String body;
	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String RANDOM = "random";
	private Map<String, String> headersMap = Collections.EMPTY_MAP;

	public void sendRequest(String reqestMethod) {
		 if(GET.equalsIgnoreCase(reqestMethod)) {
			 if (this.headersMap.isEmpty())
				 response = given().when().get(uri).then();
			 else
				 response = given().when().headers(this.headersMap).get(uri).then();

		 } else if(POST.equalsIgnoreCase(reqestMethod)) {
			 RequestSpecification request = RestAssured.given();
				request.body(this.body);

			 if (this.headersMap.isEmpty())
				 response = request.post(uri).then();
			 else
				 response = request.headers(this.headersMap).post(uri).then();

		 }
	}
	
	public void createURI(String serviceName) {
		this.uri = serviceName;
	}

	public void addEndpoint(String endpoint) {
		this.uri = uri.concat(endpoint);
	}

	public void expectedResponse(int responseCode) {
		response.statusCode(responseCode);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void validateResponse(String filePath) throws IOException {
		ObjectMapper objectmapper = new ObjectMapper();
		Map<String, String> map = objectmapper.readValue(HelperUtil.getJsonStringFromPath(filePath), Map.class);
				
		for(Map.Entry<String, String> entrySet: map.entrySet()) {
			String key = entrySet.getKey();
			final String value = entrySet.getValue();
			response.body(key, new ResponseAwareMatcher() {
	             public Matcher matcher(ResponseBody response) throws Exception {
					return equalTo(value);
				}
	        });
		}
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setRequestBody(String filePath) throws IOException {
		String jsonString = HelperUtil.getJsonStringFromPath(filePath);
		if(jsonString != null && jsonString.contains(RANDOM)) {
			jsonString = getManipulatedString(jsonString);
		}
		this.body = jsonString;
	}
	
	@SuppressWarnings("unchecked")
	private String getManipulatedString(String jsonString) throws IOException {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper objectmapper = new ObjectMapper();
		
		Map<String, String> map = objectmapper.readValue(jsonString, Map.class);
		for(Map.Entry<String, String> entrySet: map.entrySet()) {
			String value = entrySet.getValue();
			if(value.contains(RANDOM)) {
				entrySet.setValue(HelperUtil.getRandomValue(value));
			}
			jsonObject.put(entrySet.getKey(), entrySet.getValue());
		}
		return jsonObject.toJSONString();
	}

	public void setHeaders(Map<String, String> headersStringMap) {
		this.headersMap = headersStringMap;
	}
	

	
}
