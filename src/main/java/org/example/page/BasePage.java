package org.example.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected AppiumDriver appiumDriver;

    public BasePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void click(String xpath) {
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, Duration.ofSeconds(2));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        appiumDriver.findElement(By.xpath(xpath)).click();
    }

    public void typeAndEnter(String xpath, String input) {
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, Duration.ofSeconds(2));
        appiumDriver.findElement(By.xpath(xpath)).sendKeys(input);
        appiumDriver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
    }

    public String getText(String xpath) {
        return appiumDriver.findElement(By.xpath(xpath)).getText();
    }
}
