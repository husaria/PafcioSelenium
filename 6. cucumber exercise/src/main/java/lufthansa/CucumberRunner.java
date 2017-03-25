package lufthansa;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:cucumber"},
        features = "src/main/resources/"
)

public class CucumberRunner {
}
