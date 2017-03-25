package stepDefinitions;

import common.CommonHelpers;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import util.driver.WebDriverFactory;


public class GeneralTests extends CommonHelpers {

    @Before
    public void setUp() {
        initDriver();
        initPages();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("^Open url \"([^\"]*)\"$")
    public void openPageUrl(String url)  {
        driver.get(url);
        Assert.assertNotNull("URL is empty", url);
    }

}
