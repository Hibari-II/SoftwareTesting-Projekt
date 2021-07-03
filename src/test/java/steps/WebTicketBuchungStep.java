package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import oebbelements.XPath;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utility.WebHook;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebTicketBuchungStep {

    private static final String MAP_FROM_KEY = "From";
    private static final String MAP_TO_KEY = "To";
    private static final String MAP_TIME_KEY = "Time";

    private WebDriver webDriver;


    @Given("Website ticket bookingpage")
    public void seleniumWebsite() throws Exception {
        this.webDriver = WebHook.getDriver();
        this.webDriver.get("https://tickets.oebb.at/de/ticket/travel");
    }

    @When("I click on Einfach-Raus")
    public void clickOnEinfachRaus() throws Exception {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        var fromElem = this.webDriver.findElement(By.xpath(XPath.WEB_TICKET_EINFACH_RAUS));
        fromElem.click();
    }

    @When("I search for a train")
    public void searchForATrain(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> columns = rows.get(0);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        var fromElem = this.webDriver.findElement(By.cssSelector("input[name='stationFrom']"));
        fromElem.click();
        fromElem.sendKeys(columns.get(MAP_FROM_KEY));
        fromElem.sendKeys(Keys.ENTER);
        var toElem = this.webDriver.findElement(By.cssSelector("input[name='stationTo']"));
        toElem.click();
        toElem.sendKeys(columns.get(MAP_TO_KEY));
        webDriver.findElement(By.xpath(XPath.WEB_FIRST_DESTINATION_OPTION)).click();
        webDriver.findElement(By.xpath(XPath.WEB_TICKET_SHOW_ALL)).click();
    }

    @When("I add a second adult person")
    public void addSecondAdultPerson() throws Exception {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.webDriver.findElement(By.xpath(XPath.WEB_TICKET_BOOKING_ADD_PASSENGER_BUTTON)).click();
        this.webDriver.findElement(By.xpath(XPath.WEB_TICKET_BOOKING_ADD_ADULT_BUTTON)).click();
        this.webDriver.findElement(By.xpath(XPath.WEB_TICKET_BOOKING_CONTINUE_ADD_ADULT_BUTTON)).click();
    }

    @When("I click on a train connection")
    public void clickOnTrainConnection() throws Exception {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.webDriver.findElement(By.xpath(XPath.WEB_TICKET_BOOKING_RESULT_BUTTON)).click();
    }

    @Then("Ticket price should be {string}")
    public void ticketpriceShouldBe(String price) throws Exception {
        var result = webDriver.findElement(By.xpath(XPath.WEB_TICKET_EINFACH_RAUS_PRICE)).getText();
        Assert.assertNotNull("Erwartet, dass mindestens ein ergebniss gefunden wird.", result);
        //todo: magic string?
        Assert.assertEquals("€ 35,00",result);
    }

    @Then("I should be able to put a ticket into the cart")
    public void thereShouldBeAtLeastOneResult() throws Exception {
        var result = webDriver.findElement(By.xpath(XPath.WEB_TICKET_BOOKING_IN_WARENKORB_BUTTON)).getText();
        Assert.assertNotNull("Erwartet, dass mindestens ein ergebniss gefunden wird.", result);
        //todo: magic string?
        Assert.assertEquals("in den\nWarenkorb",result);
    }
}
