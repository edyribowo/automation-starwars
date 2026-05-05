package org.example.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected AppiumDriver appiumDriver;

    public BasePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void click(String xpath) {
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public void clickWithoutWait(String s) {
        WebElement webElement = appiumDriver.findElement(By.xpath(s));
        webElement.click();
    }

    public void type(String xpath, String input) {
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(input);
    }

    public void typeAndEnter(String xpath, String input) {
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(input);
        appiumDriver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
    }

    public boolean isElementVisible(String xpath) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getText(String xpath) {
        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
    }
}
