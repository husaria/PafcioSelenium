package configuration;

import org.openqa.selenium.WebDriver;
import util.driver.WebDriverFactory;
import util.property.PropertyLoader;

public class EnvironmentSetup {

    public static PropertyLoader propertyLoader = new PropertyLoader("App.properties");
    public static String prefix;
    public static WebDriver driver;

    public static void initDriver() {
        WebDriverFactory driverFactory = new WebDriverFactory();
//        Map<String, Object> caps = new HashMap<>();
//        caps.put(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//        caps.put("requireWindowFocus", true);
//        driverFactory.updateCapabilities(caps);
        driver = driverFactory.getDriver();
    }
}
