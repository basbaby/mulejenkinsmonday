package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.FtpDriver;

import javax.jms.JMSException;
import java.io.IOException;

public class FtpStepDefination {
    private FtpDriver ftpDriver = new FtpDriver();

    @Given("^I get the expected outputFile from the location (.*) path$")
    public void getInputFilelocation(String expectedFileLocation) throws Exception {

        ftpDriver.readFile(expectedFileLocation);
    }

    @When("^I get Activemq endpoint (.*) and queue name (.*) and actual outputFile location (.*) path with extention (.*)$")
    public void getEndpointQueueName(String endpoint, String queueName, String outputfileLocation,String fileExt) throws JMSException, IOException {
        ftpDriver.setEndpointandQueueName(endpoint, queueName, outputfileLocation,fileExt);
    }


    @Then("^I need to compare the two files where to match the content of the data by making assertion as (.*) value$")
    public void responseCodeValidation(boolean assertValue) throws Throwable {
        ftpDriver.expectedResponse(assertValue);
    }

}
