package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import oebbelements.Id;
import oebbelements.XPath;
import org.junit.Assert;
import utility.CucumberHook;

public class TicketBuchungStep {

    private AppiumDriver driver;

    @Given("Android Applikation")
    public void androidApplikation() throws Exception {
        this.driver = CucumberHook.getDriver();
    }

    @When("Suche Zugverbindung")
    public void sucheZugverbindung(DataTable table) {
        startSearchingForTrain();
        setDestination("Wien", "Linz");
        clickOnTravelAction();
    }

    // TODO: ErgebnisListe erhalten? Überlegung ob das Then doch abzuändern und etwas anderes zu definieren als eine Liste zurückzuerhalten.
    @Then("Ergebnis Liste erhalten")
    public void ergebnisListeErhalten() {
    }

    // TODO: Aus der Datentabelle die Daten lesen und die als Argumente an die methoden schicken
    // Zeit Änderung implementieren
    @When("Wähle")
    public void waehle(DataTable table) throws InterruptedException {
        startSearchingForTrain();
        setDestination("Wien", "Linz");
//        setDepartmentTime("09:00");
        clickOnTravelAction();
        clickOnTicket();
        setAmountOfTickets(1);
    }

    @Then("Ticket kostet {double}€")
    public void ticketKostet(double price) {
        var priceElement = driver.findElementById("at.oebb.ts:id/offers_offer_price");
        var result = Double.parseDouble(priceElement.getText().split("€ ")[1]);
        Assert.assertEquals(price, result);
    }

    private void startSearchingForTrain() {
        driver.findElementById(Id.TicketService).click();
    }

    private void setDestination(String from, String to) {
        var fromElement = driver.findElementById(Id.StartDestination);
        fromElement.click();
        fromElement.sendKeys(from);
        driver.findElementByXPath(XPath.FirstDestinationOption).click();

        var toElement = driver.findElementById(Id.TargetDestination);
        toElement.click();
        toElement.sendKeys(to);
        driver.findElementByXPath(XPath.FirstDestinationOption).click();
    }

    private void clickOnTravelAction() {
        driver.findElementByXPath(XPath.TravelAction).click();
    }

    private void clickOnTicket() {
        driver.findElementByXPath(XPath.Ticket).click();
    }

    private void setDepartmentTime(String time) {
    }

    private void setAmountOfTickets(int amount) {
        driver.findElementById(Id.TravelersInformation).click();
        for (int i = 1; i < amount; i++)
            driver.findElementByXPath(XPath.AddAdult).click();
        driver.findElementById(Id.TravelersInformationConfirmButton).click();
    }
}