package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        tags = "~@Ignore",
        glue = {"stepDefinition"},
        plugin = {"pretty", "html:target/cucumber-reports", "json:results/cucumber.json",
        			"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
        monochrome = true)
public class TestRunner {
	
}
