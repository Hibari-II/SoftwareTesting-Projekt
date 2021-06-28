package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;

public class WebHook {

    private static WebDriver setUpSelenium() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\chrome\\chromedriver.exe");
        return new ChromeDriver();
    }

    public static WebDriver getDriver() throws MalformedURLException {
        return setUpSelenium();
    }
}
