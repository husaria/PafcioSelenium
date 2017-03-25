import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Properties;

public class Main {
    private static WebDriver driver;
    public Properties properties;

    @BeforeClass
    public static void initializeWebDriver() {

        driver = new FirefoxDriver();
        driver.get(getProperty("Browser.Url"));
    }


    @Test
    public void google() {
        WebElement lucky = driver.findElement(By.name(getProperty("Locator.Button.Lucky.Name")));
        lucky.click();
    }


    @AfterClass
    public static void closeDriver() {
        driver.close();
        driver.quit();
    }


    private static String getProperty(String propertyName) {
        Utils utils = new Utils();
        try {
            return utils.getPropertyValue(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }
}