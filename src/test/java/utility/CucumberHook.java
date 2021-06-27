package utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

public class CucumberHook {
    private static AppiumDriver driver;

    private static void setUpAppium() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability( "deviceName", "Nexus");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "11");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("appPackage", "at.oebb.ts");
        desiredCapabilities.setCapability("appActivity", ".SplashActivity");
//        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "2000");

        driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public static AppiumDriver getDriver() throws MalformedURLException {
        setUpAppium();
        return driver;
    }
}
