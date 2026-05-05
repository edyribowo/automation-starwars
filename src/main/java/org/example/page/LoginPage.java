package org.example.page;

import io.appium.java_client.AppiumDriver;

import java.sql.SQLOutput;
import java.util.concurrent.ThreadLocalRandom;

public class LoginPage extends BasePage {
    private String emailRegistered = "";
    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void clickAllowNotification() {
        if(isElementVisible("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]"))
            click("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");
    }

    public void clickCreateNewAccount() {
        click("//android.widget.TextView[@text=\"Create a new account\"]");
    }

    public void clickCameraIcon() {
        click("//android.widget.TextView[@text=\"NA\"]");
    }

    public void clickWhileUsingTheApp() {
        click("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]");
    }

    public void clickCapturePhoto() {
        click("//android.widget.TextView[@text=\"Capture Photo\"]");
    }

    public void clickSelectPhoto() {
        click("//android.view.ViewGroup[@content-desc=\"Capture Photo\"]");
    }

    public void fillEmail(String email) {
        this.emailRegistered = email + ThreadLocalRandom.current().nextInt(1, 1000)
                + "@starwars.com";
        System.out.println("Email registed : " + emailRegistered);
        click("//android.widget.EditText[@text=\"Email\"]");
        type("//android.widget.EditText[@text=\"Email\"]", emailRegistered);
    }

    public void fillFirstName(String firstName) {
        click("//android.widget.EditText[@text=\"First name\"]");
        type("//android.widget.EditText[@text=\"First name\"]", firstName);
    }

    public void fillLastName(String lastName) {
        click("//android.widget.EditText[@text=\"Last name\"]");
        type("//android.widget.EditText[@text=\"Last name\"]", lastName);
    }

    public void fillPassword(String password) {
        click("//android.widget.EditText[@text=\"Password\"]");
        type("//android.widget.EditText[@text=\"Password\"]", password);
    }

    public void fillConfirmPassword(String password) {
        click("//android.widget.EditText[@text=\"Confirm password\"]");
        type("//android.widget.EditText[@text=\"Confirm password\"]", password);
    }

    public void selectUserRole(String userRole) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Click select user role");
        click("//android.view.ViewGroup[@content-desc='Select user role']");
        click("//android.widget.TextView[@text='" + userRole + "']");
    }

    public void clickSave() {
        click("//android.widget.TextView[@text=\"Save\"]");
    }

    public void clickPopUp(String s) {
        click("//android.widget.TextView[@text='" + s + "']");
    }

    public void clickBackNavigation() {
        click("//android.widget.TextView[@text=\"Create group account\"]/preceding-sibling::android.view.ViewGroup");
    }

    public void clickProfilIcon() {
        click("//android.widget.TextView[@text=\"You\"]");
    }

    public void clickLogout() {
        click("//android.view.ViewGroup[@content-desc=\"Logout\"]");
    }

    public void confirmLogout() {
        click("//android.widget.Button[@resource-id=\"android:id/button1\"]");
    }

    public void clickLogin() {
        click("//android.widget.TextView[@text=\"LOGIN\"]");
    }

    public void fillEmailLogin() {
        click("//android.widget.EditText[@text=\"E-mail address\"]");
        type("//android.widget.EditText[@text=\"E-mail address\"]", emailRegistered);
    }

    public void fillPasswordLogin(String password) {
        click("//android.widget.EditText[@text=\"Password\"]");
        type("//android.widget.EditText[@text=\"Password\"]", password);
    }

    public boolean isProfileIconVisible() {
        return isElementVisible("\"//android.widget.TextView[@text='You']\"");
    }
}
