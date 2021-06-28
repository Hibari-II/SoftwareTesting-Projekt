package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import oebbelements.Id;
import oebbelements.XPath;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utility.CucumberHook;

import java.util.List;
import java.util.Map;

public class TicketBuchungStep {

    private static final String MAP_FROM_KEY = "Von";
    private static final String MAP_TO_KEY = "Nach";

    private AppiumDriver driver;

    @Given("Android Applikation")
    public void androidApplikation() throws Exception {
        this.driver = CucumberHook.getDriver();
    }

    @When("Suche Zugverbindung")
    public void sucheZugverbindung(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> columns = rows.get(0);
        startSearchingForTrain();
        setDestination(columns.get(MAP_FROM_KEY), columns.get(MAP_TO_KEY));
        clickOnTravelAction();
    }

    @Then("Ergebnis Liste erhalten")
    public void ergebnisListeErhalten() {
        List travelList = driver.findElementsById(Id.FAHRPLAN_LIST);
        Assert.assertNotNull("Erwartet, dass die Liste nicht leer ist.", travelList);
    }

    @When("Wähle")
    public void waehle(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> columns = rows.get(0);
        startSearchingForTrain();
        setDestination(columns.get(MAP_FROM_KEY), columns.get(MAP_TO_KEY));
        clickOnTravelAction();
        clickOnTicket();
    }

    @Then("Ticket kostet")
    public void ticketKostet() {
        var priceElement = driver.findElementById(Id.OFFER_PRICE);
        Assert.assertNotNull(priceElement);
        Assert.assertNotNull(priceElement.getText());
    }

    @When("weiteren Angeboten")
    public void weiterenAngeboten() {
        driver.findElementById(Id.OEBB_ICON_LOGO).click();
        driver.findElementByXPath(XPath.WEITER_ANGEBOTE).click();
    }

    @And("Einfach Raus Ticket")
    public void einfachRausTicket() {
        driver.findElementByXPath(XPath.EINFACH_RAUS_TICKET).click();
    }

    @Then("Ticket kostet price {string}")
    public void ticketKostetPrice(String price) {
        var priceElement = driver.findElementById(Id.OFFER_PRICE);
        Assert.assertEquals(price, priceElement.getText());
    }

    private void startSearchingForTrain() {
        driver.findElementById(Id.TICKET_SERVICE).click();
    }

    private void setDestination(String from, String to) {
        var fromElement = driver.findElementById(Id.START_DESTINATION);
        fromElement.click();
        fromElement.sendKeys(from);
        driver.findElementByXPath(XPath.FIRST_DESTINATION_OPTION).click();

        var toElement = driver.findElementById(Id.TARGET_DESTINATION);
        toElement.click();
        toElement.sendKeys(to);
        driver.findElementByXPath(XPath.FIRST_DESTINATION_OPTION).click();
    }

    private void clickOnTravelAction() {
        driver.findElementByXPath(XPath.TRAVEL_ACTION).click();
    }

    private void clickOnTicket() {
        driver.findElementByXPath(XPath.TICKET).click();
    }


}
