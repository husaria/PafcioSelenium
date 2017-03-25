package pages;


import common.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;


public class TableTravelRequest extends Common {
    private WebDriver driver;

    public TableTravelRequest(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    private By newTravelRequestButton = By.xpath("//button[.='New Travel Request']");
    private String filterRequestedByCells = "//td[@data-title-text='%s']";
    private By filterOriginInput = By.name("Origin");
    private By deleteButton = By.cssSelector(".fa-plane");


    public void openNewTravelRequest() throws InterruptedException {
        getWebElement(newTravelRequestButton).click();
    }

    public void filterTableOrigin(String text) {
        WebElement originInput = getWebElement(filterOriginInput);
        originInput.clear();
        originInput.sendKeys(text);
    }


    public List<String> getCellsTextByHeader(String field) {
        By cellLocator = By.xpath(String.format(filterRequestedByCells, field));
        List<WebElement> elementList = driver.findElements(cellLocator);
        return elementList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void deleteTravel(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton)).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
