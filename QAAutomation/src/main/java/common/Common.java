package common;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.BaseWebMobileElement;

import static org.junit.Assert.fail;


public class Common extends BaseWebMobileElement {
    public Common(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebDriver driver;


    By loadingBarLocator = By.id("loading-bar");
    By spinnerLocator = By.cssSelector(".spinner");


    public void waitOnLoaderForSeconds(int seconds) throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, seconds);

        try {
            WebDriverWait wait2 = new WebDriverWait(driver, 2);
            wait2.until(ExpectedConditions.visibilityOfElementLocated(spinnerLocator));
        } catch (TimeoutException ignored) {
            Thread.sleep(1000);
        }

        try {
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBarLocator));
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));

        } catch (TimeoutException e) {
            fail("Timeout to load page. Spinner is displayed more than max seconds: " + seconds + ". Error:" + e);
        }
    }


    public void waitForPageTitle(String title) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        try {
            webDriverWait.until(ExpectedConditions.titleIs(title));
        } catch (Exception e) {
            fail("Page is not loaded. Expected title: " + title + ". Error: " + e);
        }
    }


    public WebElement getElementByText(String text) {
        try {
            return getWebElement(By.xpath("//*[.='" + text + "']"));
        } catch (Exception e) {
            return null;
        }
    }


    public void fillTextToElementById(String elementId, String textToFill) {
        try {
            WebElement element = driver.findElement(By.id(elementId));
            element.clear();
            element.sendKeys(textToFill);

        } catch (Exception e) {
            fail("Unable to find / fill in element by id. Error: " + e);
        }
    }
}
