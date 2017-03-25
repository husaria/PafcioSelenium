package pages;

import common.Common;
import common.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.driver.DriverHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class NewTravelRequest extends Common {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Utils utils = new Utils();

    public NewTravelRequest(WebDriver driver) {
        super(driver);
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, 15);
    }


    private By submitButtonLocator = By.xpath("//button[.='Submit']");
    private By goBackButtonLocator = By.xpath("//button[.='Go back']");
    private String fieldInput = ("//label[@for='%s']/following-sibling::div//input");
    private String listByName = "//span[@class='highlight' and .='%s']";
    private By startDateOpeLocator = By.xpath("//*[@id='startdate']/following-sibling::span[@class='input-group-btn']");
    private By endDateOpeLocator = By.xpath("//*[@id='enddate']/following-sibling::span[@class='input-group-btn']");
    private By startDateInputLocator = By.id("startdate");
    private By endDateInputLocator = By.id("enddate");
    //public By originInputLocator = By.xpath("//input[@id='origin']");
    public By originInputLocator = By.id("origin");
    //public String originInputLocatorString = driver.findElement(By.id("origin")).getText();
    private By requestSubmittedPromptLocator = By.xpath("//div[@aria-label='Request submitted']");
    private By isBillableSliderLocator = By.className("EMP-zip__slider");
    private By commentTextAreaLocator = By.xpath("//label[@for='comments']/following-sibling::textarea");
    //user's last trips
    private By copyButtonLocator = By.xpath("//div[.=\"Copy\"]");
    private By projectLastTripLocator = By.xpath("//tr[1]/td[@data-title=\"'Project'\"]");
    private By regionLastTripLocator = By.xpath("//tr[1]/td[@data-title=\"'Region'\"]");
    public By originLastTripLocator = By.xpath("//tr[1]/td[@data-title=\"'Origin'\"]");
    //public String originLastTripLocatorString = driver.findElement(By.xpath("//tr[1]/td[@data-title=\"'Origin'\"]")).getText();
    private By destinationLastTripLocator = By.xpath("//tr[1]/td[@data-title=\"'Destination'\"]");
    private By commentsLastTripLocator = By.xpath("//tr[1]/td[@data-title=\"'Comments'\"]");





    public void submitRequest() {
        getWebElement(submitButtonLocator).click();
    }


    public void goBack() {
        getWebElement(goBackButtonLocator).click();
    }


    public void selectDropdownByLabelFor(String fieldName, String text) {
        By fieldInputLocator = By.xpath(String.format(fieldInput, fieldName));
        By listByNameLocator = By.xpath(String.format(listByName, text));

        WebElement textInput = getWebElement(fieldInputLocator);
        DriverHelper.click(driver, textInput);
        //clear field if text is empty
        if (text.equals("")) {
            textInput.clear();
            return;
        }
        textInput.sendKeys(text);
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(listByNameLocator)).click();
        } catch (TimeoutException e) {
            fail("Unable to select from dropdown: " + fieldName + ". Expected text to select: " + text + ". Error: " + e);
        }
    }


    public void selectStartDateToday() {
        getWebElement(startDateOpeLocator).click();
        getElementByText("Today").click();
        String dateActual = getWebElement(startDateInputLocator).getAttribute("value");
        assertEquals("Today date is not selected in Start date input", utils.getTodayDateDefaultFormat(), dateActual);
    }


    public void selectEndDateToday() {
        getWebElement(endDateOpeLocator).click();
        getElementByText("Today").click();
        String dateActual = getWebElement(endDateInputLocator).getAttribute("value");
        assertEquals("Today date is not selected in End date input", utils.getTodayDateDefaultFormat(), dateActual);
    }

    public boolean isRequestSubmittedConfirmationDisplayed() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(requestSubmittedPromptLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickBillable() {
        driver.findElement(isBillableSliderLocator).click();
    }

    public void fillComment(String comments) {
        getWebElement(commentTextAreaLocator).sendKeys(comments);
    }

    public void clickCopy() {
        driver.findElement(copyButtonLocator).click();
    }



}
