package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import com.google.common.base.Strings;

public class HelperUtil {

	/**
	 * This function will generate random integer/alphanumeric/email based on random
	 * parameter passed
	 * @param key
	 * @return random value generated
	 */
	public static String getRandomValue(String key) {
		String randomValue = "";
		Random random = new Random();
		if("randomInteger".equalsIgnoreCase(key)) {
			randomValue = String.valueOf(random.nextInt());
		} else if("randomAlphaNumeric".equalsIgnoreCase(key)) {
			randomValue = String.valueOf(random.nextInt()).concat("test");
		} else if("randomEmail".equalsIgnoreCase(key)) {
			randomValue = String.valueOf(random.nextInt()).concat("test@gmail.com");
		}
		return randomValue;
	}
	
	public static String getJsonStringFromPath(String filePath) throws IOException {
		String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
		return Strings.isNullOrEmpty(jsonString) ? " " : jsonString;			
	}
}
