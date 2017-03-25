package lufthansa;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LufthansaMainPage;

import static org.junit.Assert.fail;

public class SearchFlights {
    protected WebDriver driver;

    @Before
    public void startDriver() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();


    }


    @Given("^I am on the Lufthansa home side$")
    public void i_am_on_the_Lufthansa_home_side() throws Throwable {
        driver.get("http://lufthansa.com");
    }

    @When("^I fill in lufthansa side with data From: \"([^\"]*)\", To: \"([^\"]*)\"$")
    public void I_fill_in_lufthansa_side_with_data_From_To(String from, String to) throws Throwable {
        LufthansaMainPage mainPage = new LufthansaMainPage(driver);
        mainPage.fillForm(from);
        mainPage.fillTo(to);
        fail();
        Thread.sleep(3000);

    }

    @And("^I search for flights$")
    public void I_search_for_flights() throws Throwable {
        // Express the Regexp above with the code you wish you had
    }

    @Then("^I see search results with available flights From: \"([^\"]*)\", To: \"([^\"]*)\"$")
    public void I_see_search_results_with_available_flights_From_To(String arg1, String arg2) throws Throwable {
        // Express the Regexp above with the code you wish you had
    }

    @After
    public void closeDriver(Scenario scenario) {

        if(scenario.isFailed()) {
            scenario.write("Sie zepsulo ;(" + scenario.getName());
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        driver.quit();
    }

    @And("^I see price for each flight$")
    public void iSeePriceForEachFlight() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I fill in lufthansa side with data From data below$")
    public void iFillInLufthansaSideWithDataFromDataBelow(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(dataTable.raw().get(1).get(0));
    }
}
