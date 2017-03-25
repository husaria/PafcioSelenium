package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by pblaszyk on 3/24/2017.
 */
public class LufthansaMainPage {

    public LufthansaMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;

    @FindBy(id = "flightmanagerFlightsFormOrigin")
    WebElement fromInput;

    @FindBy(id = "flightmanagerFlightsFormDestination")
    WebElement toInput;

    public void fillForm(String from) {
        fromInput.sendKeys(from);
    }

    public void fillTo(String to) {
        toInput.sendKeys(to);
    }


}
