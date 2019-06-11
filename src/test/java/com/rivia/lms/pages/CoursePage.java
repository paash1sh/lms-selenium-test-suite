package com.rivia.lms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CoursePage extends BasePage {

    @FindBy(css = ".course-catalog")
    private WebElement courseCatalog;

    @FindBy(css = ".enroll-btn")
    private WebElement enrollButton;

    @FindBy(css = ".course-title")
    private WebElement courseTitle;

    @FindBy(css = ".progress-bar")
    private WebElement progressBar;

    @FindBy(css = ".course-list .course-item")
    private List<WebElement> courseItems;

    @FindBy(id = "search-course")
    private WebElement searchField;

    @FindBy(css = ".enrollment-success")
    private WebElement enrollmentSuccess;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public void searchCourse(String courseName) {
        sendKeys(searchField, courseName);
    }

    public void enrollInCourse(String courseName) {
        searchCourse(courseName);
        WebElement course = driver.findElement(
            By.xpath("//div[@class='course-item']//h3[text()='" + courseName + "']")
        );
        clickElement(course);
        clickElement(enrollButton);
    }

    public boolean isEnrolledSuccessfully() {
        try {
            waitForElement(enrollmentSuccess);
            return enrollmentSuccess.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCourseCount() {
        return courseItems.size();
    }

    public String getCourseProgress() {
        waitForElement(progressBar);
        return progressBar.getAttribute("aria-valuenow");
    }
}
