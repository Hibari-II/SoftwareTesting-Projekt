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

    private static final String MAP_FROM_KEY = "Von";
    private static final String MAP_TO_KEY = "Nach";

    private WebDriver webDriver;


    @Given("Website ticket bookingpage")
    public void seleniumWebsite() throws Exception {
        this.webDriver = WebHook.getDriver();
        this.webDriver.get("https://tickets.oebb.at/de/ticket/travel");
    }

    @When("I choose location")
    public void chooselocation(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> columns = rows.get(0);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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

    @When("I click on Einfach-Raus")
    public void clickOnEinfachRaus(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> columns = rows.get(0);
//        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(10000);
        var fromElem = this.webDriver.findElement(By.xpath(XPath.WEB_TICKET_EINFACH_RAUS));
        fromElem.click();
//        fromElem.sendKeys(columns.get(MAP_FROM_KEY));
//        fromElem.sendKeys(Keys.ENTER);
//        var toElem = this.webDriver.findElement(By.cssSelector("input[name='stationTo']"));
//        toElem.click();
//        toElem.sendKeys(columns.get(MAP_TO_KEY));
//        webDriver.findElement(By.xpath(XPath.WEB_FIRST_DESTINATION_OPTION)).click();
//
//        webDriver.findElement(By.xpath(XPath.WEB_TICKET_SHOW_ALL)).click();

    }

    @Then("the Ticket price is")
    public void ticketcosts() throws Exception {
        var result = webDriver.findElements(By.xpath(XPath.WEB_TICKET_LIST_ITEM));
        Assert.assertNotNull("Erwartet, dass mindestens ein ergebniss gefunden wird.", result);
    }

    @Then("Ticket price should be {string}")
    public void ticketpriceShouldBe(String price) throws Exception {
        var result = webDriver.findElements(By.xpath(XPath.WEB_TICKET_EINFACH_RAUS_PRICE));
        Assert.assertNotNull("Erwartet, dass mindestens ein ergebniss gefunden wird.", result);
    }

}
