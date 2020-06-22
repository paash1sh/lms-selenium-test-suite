package com.rivia.lms.tests;

import com.rivia.lms.pages.LoginPage;
import com.rivia.lms.utils.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private static final String BASE_URL = "http://lms.rivia.internal";

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver("chrome");
        driver.get(BASE_URL + "/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidAdminLogin() {
        loginPage.login("admin@rivia.com", "Admin@123");
        Assert.assertTrue("Admin login should succeed", loginPage.isLoginSuccessful());
    }

    @Test
    public void testValidStudentLogin() {
        loginPage.login("student@rivia.com", "Student@123");
        Assert.assertTrue("Student login should succeed", loginPage.isLoginSuccessful());
    }

    @Test
    public void testInvalidPasswordLogin() {
        loginPage.login("admin@rivia.com", "wrongpassword");
        Assert.assertFalse("Login with wrong password should fail", loginPage.isLoginSuccessful());
        Assert.assertEquals("Invalid credentials. Please try again.",
                loginPage.getErrorMessage());
    }

    @Test
    public void testEmptyCredentials() {
        loginPage.login("", "");
        Assert.assertFalse("Login with empty fields should fail", loginPage.isLoginSuccessful());
    }

    @Test
    public void testSQLInjectionAttempt() {
        loginPage.login("' OR '1'='1", "' OR '1'='1");
        Assert.assertFalse("SQL injection attempt should not succeed", loginPage.isLoginSuccessful());
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
# login test
# sql test
# retry fix
