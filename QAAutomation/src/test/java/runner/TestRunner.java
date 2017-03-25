package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( tags = {"@Main, @Smoke"},
        format = { "pretty", "html:target/cucumber" },
        glue = "stepDefinitions",
        features = "src/test/resources/features")

public class TestRunner {

}
