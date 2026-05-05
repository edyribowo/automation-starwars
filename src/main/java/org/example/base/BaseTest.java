package org.example.base;

import io.appium.java_client.AppiumDriver;
import org.example.utils.ConfigReader;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public static AppiumDriver appiumDriver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:platformName", ConfigReader.get("platformName"));
        desiredCapabilities.setCapability("appium:deviceName", ConfigReader.get("deviceName"));
        desiredCapabilities.setCapability("appium:automationName", ConfigReader.get("automationName"));
        desiredCapabilities.setCapability("appium:appPackage", ConfigReader.get("appPackage"));
        desiredCapabilities.setCapability("appium:appActivity", ConfigReader.get("appActivity"));

        appiumDriver = new AppiumDriver(new URL(ConfigReader.get("appiumURL")), desiredCapabilities);
    }

    @AfterClass
    public void tearDown() {
        if(appiumDriver!=null)
            appiumDriver.quit();
    }
}
