package Runners;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/Feature/BasicAuth_Bearer.feature", glue = { "StepDefinations" }, monochrome = true, plugin = {
		"pretty", "html:target/Html-reports/cucumber-reports.html", "json:target/Json-reports/cucumber.json",
		"junit:target/XML-reports/Cucumber.xml", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"timeline:test-output-thread/" }, tags = "@smoketest or @Regression")


@Test
public class TestRunner extends AbstractTestNGCucumberTests {
	
             
                  
     
}

