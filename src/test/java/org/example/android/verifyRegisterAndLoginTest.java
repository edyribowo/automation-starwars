package org.example.android;

import io.appium.java_client.AppiumDriver;
import org.example.base.BaseTest;
import org.example.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class verifyRegisterAndLoginTest
        extends BaseTest {

    @Test
    public void verifyRegisterAndLoginTest() throws InterruptedException {
        String email = "edyribowo";
        String firstName = "Edy";
        String lastName = "Ribowo";
        String password = "Admin@123";
        String userRole = "Luke Skywalker";

        LoginPage loginPage = new LoginPage(appiumDriver);
        loginPage.clickAllowNotification();
        loginPage.clickCreateNewAccount();
        loginPage.clickCameraIcon();
        loginPage.clickWhileUsingTheApp();
        loginPage.clickCapturePhoto();
        loginPage.clickSelectPhoto();

        loginPage.fillEmail(email);
        loginPage.fillFirstName(firstName);
        loginPage.fillLastName(lastName);
        loginPage.fillPassword(password);
        loginPage.fillConfirmPassword(password);
        loginPage.selectUserRole(userRole);

        loginPage.clickSave();

        loginPage.clickPopUp("Yes, I do");
        loginPage.clickWhileUsingTheApp();

        loginPage.clickBackNavigation();
        loginPage.clickProfilIcon();
        loginPage.clickLogout();
        loginPage.confirmLogout();

        loginPage.fillEmailLogin();
        loginPage.fillPasswordLogin(password);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isProfileIconVisible(), "Login Failed!");

    }
}
