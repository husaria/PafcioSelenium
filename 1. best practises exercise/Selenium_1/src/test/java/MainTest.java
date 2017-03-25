import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainTest {

    private static WebDriver driver;

    @BeforeClass
    public static void initializeWebDriver() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get("http://bing.com");
    }

    @Test
    public void menu() {
        driver.get("http://demos.telerik.com/kendo-ui/menu/index");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement mainMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='menu']/li[contains(.,'Products')]")));
//        mainMenu.click();
        WebElement subMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/span[normalize-space(text()) = 'Lights']")));
//        subMenu.click();

        Actions actions = new Actions(driver);
        actions.click(mainMenu).perform();
        actions.moveToElement(subMenu).perform();
    }

    @Test
    public void bing() throws IOException {
        Utils utils = new Utils();
        //driver.get("http://bing.com");
        //driver.get(utils.getPropertyValue("Page.URL"));
        driver.get(System.getenv("Page.URL"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        MainPage mainPage = new MainPage(driver, wait);

        mainPage.searchBing("infusion.com");
        mainPage.selectBingResults("Infusion | www.infusion.com");

        String actualPageName = mainPage.getPageTitle();
        Assert.assertEquals("Infusion page is not displayed!", "Infusion | www.infusion.com", actualPageName);

    }

    @AfterClass
    public static void closeDriver() {
        driver.close();
        driver.quit();
    }
}
