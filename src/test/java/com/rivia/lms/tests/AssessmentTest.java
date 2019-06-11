package com.rivia.lms.tests;

import com.rivia.lms.pages.LoginPage;
import com.rivia.lms.utils.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AssessmentTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "http://lms.rivia.internal";

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver("chrome");
        wait = new WebDriverWait(driver, 10);
        driver.get(BASE_URL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("student@rivia.com", "Student@123");
    }

    @Test
    public void testAssessmentLoads() {
        driver.get(BASE_URL + "/assessments/1");
        WebElement title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".assessment-title"))
        );
        Assert.assertNotNull("Assessment title should be visible", title);
    }

    @Test
    public void testAssessmentSubmission() {
        driver.get(BASE_URL + "/assessments/1");
        List<WebElement> questions = driver.findElements(By.cssSelector(".question-block"));
        Assert.assertTrue("Assessment should have at least one question", questions.size() > 0);

        // answer first MCQ
        WebElement firstOption = driver.findElement(By.cssSelector(".question-block:first-child .option:first-child input"));
        firstOption.click();

        WebElement submitBtn = driver.findElement(By.id("submit-assessment"));
        submitBtn.click();

        WebElement result = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".assessment-result"))
        );
        Assert.assertTrue("Result should be displayed after submission", result.isDisplayed());
    }

    @Test
    public void testAssessmentTimerDisplayed() {
        driver.get(BASE_URL + "/assessments/1");
        WebElement timer = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".assessment-timer"))
        );
        Assert.assertTrue("Timer should be visible during assessment", timer.isDisplayed());
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
