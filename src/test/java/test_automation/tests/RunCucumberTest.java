package test_automation.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber-report/cucumber.json"},
        tags = "~@to-do",
        glue = "test_automation.tests",
        features = "classpath:test_automation"
)
public class RunCucumberTest {



}
