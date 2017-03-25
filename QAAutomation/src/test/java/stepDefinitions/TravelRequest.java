package stepDefinitions;

import common.Common;
import common.CommonHelpers;
import common.Utils;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import pages.NewTravelRequest;
import pages.TableTravelRequest;
import util.driver.DriverHelper;
import util.driver.WebDriverFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TravelRequest extends CommonHelpers {
    private WebDriverFactory driverFactory = new WebDriverFactory();
    private WebDriver driver = driverFactory.getDriver();
    private Common common = new Common(driver);
    private NewTravelRequest newTravelRequest = new NewTravelRequest(driver);
    private TableTravelRequest tableTravelRequest = new TableTravelRequest(driver);
    private Utils utils = new Utils();

    private String originStored;


    @Before
    public void setUp() {
//        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            //logs
            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            scenario.write("Browser logs: (empty = no logs)");
            for (LogEntry entry : logEntries) {
                scenario.write(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            }
            //screenshot
            final byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        driver.close();
        driver.quit();
    }

    @Given("^Login \"([^\"]*)\" and open home page \"([^\"]*)\"$")
    public void loginAndOpenHomePage(String login, String url) throws Throwable {
        driver.get(login);
        assertNotNull("URL is empty", url);
        common.waitForPageTitle("Travel requests");
        DriverHelper.waitForPageIsReady(driver);

        //need to reload the page one more time without login
        driver.get(url);
        assertNotNull("URL is empty", url);
        common.waitForPageTitle("Travel requests");
        DriverHelper.waitForPageIsReady(driver);
        common.waitOnLoaderForSeconds(15);
    }


    @When("^Add new travel request using data: Employees: \"([^\"]*)\" Project: \"([^\"]*)\" Region: \"([^\"]*)\" Start date: \"([^\"]*)\" End date: \"([^\"]*)\" Origin: \"([^\"]*)\" Is billable: \"([^\"]*)\" Destination: \"([^\"]*)\" Comments: \"([^\"]*)\"$")
    public void addNewTravelRequestUsingData(String employees, String project, String region, String startDate, String endDate, String origin, String isBillable, String destination, String comments) throws Throwable {
        String[] employeesList = employees.split("; ");

        //open new travel request
        tableTravelRequest.openNewTravelRequest();
        assertNotNull("New travel request page is not displayed!", common.getElementByText("Submit"));
        common.waitOnLoaderForSeconds(15);

        //employees
        for (String employee : employeesList) {
            newTravelRequest.selectDropdownByLabelFor("employee", employee);
            common.waitOnLoaderForSeconds(15);
        }
        //project
        newTravelRequest.selectDropdownByLabelFor("project", project);
        //region
        newTravelRequest.selectDropdownByLabelFor("region-selectized", region);
        //dates
        if (startDate.equals("today")) {
            newTravelRequest.selectStartDateToday();
        }
        if (endDate.equals("today")) {
            newTravelRequest.selectEndDateToday();
        }
        //origin
        if (origin.equals("{RANDOM}")) {
            originStored = origin = utils.generateRandomText();
        }
        common.fillTextToElementById("origin", origin);
        //is billable
        if (isBillable.equals("Billable")) {
            newTravelRequest.clickBillable();
        }
        //destination
        common.fillTextToElementById("destination", destination);
        //comments
        newTravelRequest.fillComment(comments);
        //submit
        newTravelRequest.submitRequest();
    }

    @Then("^I expect my request \"([^\"]*)\" added$")
    public void iExpectMyRequestAdded(String shouldBeAdded) throws Throwable {
        switch (shouldBeAdded) {
            case "is":
                assertTrue("Request submitted confirmation is not visible.", newTravelRequest.isRequestSubmittedConfirmationDisplayed());
                common.waitOnLoaderForSeconds(15);
                break;
            case "isn't":   //make sure new travel request is displayed
                assertNotNull("New travel request page should be visible. Validation is not working.", common.getElementByText("Submit"));
                break;
            default:
                fail("Invalid travel request state option. Only is / isn't is allowed!");
        }
    }

    @And("^there \"([^\"]*)\" my request added for each Employee: \"([^\"]*)\" on the list with data Project: \"([^\"]*)\" Region: \"([^\"]*)\" Start: \"([^\"]*)\" End: \"([^\"]*)\" Origin: \"([^\"]*)\" Billable: \"([^\"]*)\" Destination: \"([^\"]*)\" Comments: \"([^\"]*)\"$")
    public void thereMyRequestAddedForEachEmployeeOnTheListWithDataProjectRegionStartEndOriginBillableDestinationComments(String isCorrect, String employees, String project, String region, String start, String end, String origin, String billable, String destination, String comments) throws Throwable {
        if (isCorrect.equals("is")) {
            //split employees list. It will help with validation or rows
            List<String> employeesList = Arrays.asList(employees.split("; "));
            //filter results by origin
            if (origin.equals("{RANDOM}")) {
                origin = originStored;
            }
            tableTravelRequest.filterTableOrigin(origin);

            //Validate table
            assertEquals("List of employees is not equal!", employeesList, tableTravelRequest.getCellsTextByHeader("Employee"));

            //make sure each column value is the same for each employee
            //project
            List<String> projectList = tableTravelRequest.getCellsTextByHeader("Project");
            for (String projectItem : projectList) {
                assertEquals("Projects for all employees added in request are not the same", project, projectItem);
            }

            //region
            List<String> regionList = tableTravelRequest.getCellsTextByHeader("Region");
            for (String regionItem : regionList) {
                assertEquals("Projects for all employees added in request are not the same", region, regionItem);
            }

            //origin
            List<String> originList = tableTravelRequest.getCellsTextByHeader("Origin");
            for (String originItem : originList) {
                assertEquals("Origins for all employees added in request are not the same", origin, originItem);
            }

            //destination
            List<String> destinationList = tableTravelRequest.getCellsTextByHeader("Destination");
            for (String destinationItem : destinationList) {
                assertEquals("Destinations for all employees added in request are not the same", destination, destinationItem);
            }

            //start
            if (start.equals("today")) {
                start = utils.getTodayDateUSFormat();
            }
            List<String> startList = tableTravelRequest.getCellsTextByHeader("Start");
            for (String startItem : startList) {
                assertEquals("Start date for all employees added in request are not the same", start, startItem);
            }

            //end
            if (end.equals("today")) {
                end = utils.getTodayDateUSFormat();
            }
            List<String> endList = tableTravelRequest.getCellsTextByHeader("End");
            for (String endItem : endList) {
                assertEquals("End date for all employees added in request are not the same", end, endItem);
            }

            //code - validate if not empty
            List<String> codeList = tableTravelRequest.getCellsTextByHeader("Code");
            for (String codeItem : codeList) {
                assertNotEquals("Code for all employees added in request are empty", "", codeItem.trim());
            }

            //comments
            List<String> commentsList = tableTravelRequest.getCellsTextByHeader("Comments");
            for (String commentItem : commentsList) {
                assertEquals("Comments for all employees added in request are not the same", comments, commentItem);
            }

            //billable
            List<String> billableList = tableTravelRequest.getCellsTextByHeader("Billable");
            for (String billableItem : billableList) {
                assertEquals("Billable for all employees added in request are not the same", billable, billableItem);
            }
        }
    }


    @When("^Add new travel request and Go back without saving changes$")
    public void addNewTravelRequestAndGoBackWithoutSavingChanges() throws Throwable {
        tableTravelRequest.openNewTravelRequest();
        assertNotNull("New travel request page is not displayed!", common.getElementByText("Submit"));
        common.waitOnLoaderForSeconds(15);
        newTravelRequest.goBack();
        common.waitOnLoaderForSeconds(15);
    }


    @Then("^landing page should be displayed$")
    public void landingPageShouldBeDisplayed() throws Throwable {
        //make sure home page is displayed - Search button is visible
        assertNotNull("Go back doesn't switch to home page", common.getElementByText("Search"));
    }


    @And("^copy last trip$")
    public void copyLastTrip() throws Throwable {
        newTravelRequest.clickCopy();
    }


    @Then("^I expect my request has data copied from last trip$")
    public void iExpectMyRequestHasDataCopiedFromLastTrip() throws Throwable {

        
        Assert.assertEquals("newTravelRequest.originLastTripLocatorString", "newTravelRequest.originInputLocatorString", "oj");

    }

    @And("^I want to delete \"([^\"]*)\" my request and validate delete process$")
    public void iWantToDeleteMyRequestAndValidateDeleteProcess(String arg0) throws Throwable {
        if(arg0.equals("is")){
            tableTravelRequest.deleteTravel();
            common.waitOnLoaderForSeconds(15);
            assertEquals("Travel hasn't been removed", 0, tableTravelRequest.getCellsTextByHeader("Employee").size());
        }
    }
}
