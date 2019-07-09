package com.rivia.lms.tests;

import com.rivia.lms.pages.CoursePage;
import com.rivia.lms.pages.LoginPage;
import com.rivia.lms.utils.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class CourseEnrollmentTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private CoursePage coursePage;
    private static final String BASE_URL = "http://lms.rivia.internal";

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver("chrome");
        driver.get(BASE_URL + "/login");
        loginPage = new LoginPage(driver);
        loginPage.login("student@rivia.com", "Student@123");
        coursePage = new CoursePage(driver);
    }

    @Test
    public void testCourseListLoads() {
        driver.get(BASE_URL + "/courses");
        Assert.assertTrue("Course catalog should display at least one course",
                coursePage.getCourseCount() > 0);
    }

    @Test
    public void testEnrollInCourse() {
        driver.get(BASE_URL + "/courses");
        coursePage.enrollInCourse("Java Fundamentals");
        Assert.assertTrue("Enrollment should succeed", coursePage.isEnrolledSuccessfully());
    }

    @Test
    public void testCourseSearchReturnsResults() {
        driver.get(BASE_URL + "/courses");
        coursePage.searchCourse("Java");
        Assert.assertTrue("Search should return matching courses",
                coursePage.getCourseCount() > 0);
    }

    @Test
    public void testCourseProgressTracked() {
        driver.get(BASE_URL + "/my-courses");
        String progress = coursePage.getCourseProgress();
        Assert.assertNotNull("Progress bar should be present", progress);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
# enroll test
