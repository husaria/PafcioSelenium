import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id = "sb_form_q")
    private WebElement searchInputWebElement;

    private String searchResultLinkLocator = "//a[.='%s']";

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void searchBing(String textToSearch) {
        wait.until(ExpectedConditions.visibilityOf(searchInputWebElement));
        searchInputWebElement.sendKeys(textToSearch);
        searchInputWebElement.sendKeys(Keys.ENTER);

    }

    public void markText() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).perform();
        actions.sendKeys("a").perform();
        actions.keyUp(Keys.CONTROL).perform();
    }

    public void selectBingResults(String linkToSelect) {
        driver.findElement(By.xpath(String.format(searchResultLinkLocator, linkToSelect))).click();

    }

    public String getPageTitle() {
        return driver.getTitle();
    }


}