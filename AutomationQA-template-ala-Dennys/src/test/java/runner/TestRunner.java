package runner;

import com.cucumber.listener.ExtentCucumberFormatter;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.NetworkMode;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions( tags = {"@Main, @Smoke"},
        format = { "pretty", "html:target/cucumber" },
        glue = "stepDefinitions",
        features = "src/test/resources/features",
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter"} )

public class TestRunner {
    @BeforeClass
    public static void setup() {
        // Initiates the extent report and generates the output in the output/Run_<unique timestamp>/report.html file by default.
        ExtentCucumberFormatter.initiateExtentCucumberFormatter(new File("target/extent/report.html"), false, DisplayOrder.NEWEST_FIRST, NetworkMode.ONLINE);
        // Loads the extent config xml to customize on the report.
        ExtentCucumberFormatter.loadConfig(new File("src/test/resources/extent-config.xml"));
    }
}
