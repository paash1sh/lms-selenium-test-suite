# lms-selenium-test-suite

Automated UI test suite for a Learning Management System (LMS) built with Selenium WebDriver 3.x and JUnit 4. Covers login flows, course enrollment, assessment submission, and role-based access for Admin and Student user types.

## Tech Stack

- Java 8
- Selenium WebDriver 3.141.59
- JUnit 4.12
- Maven 3.6.x
- Page Object Model (POM) design pattern
- ChromeDriver / GeckoDriver

## Project Structure

```
src/
└── test/java/com/rivia/lms/
    ├── pages/          # Page Object classes
    │   ├── BasePage.java
    │   ├── LoginPage.java
    │   └── CoursePage.java
    ├── tests/          # Test classes
    │   ├── LoginTest.java
    │   ├── CourseEnrollmentTest.java
    │   └── AssessmentTest.java
    └── utils/
        └── DriverFactory.java
```

## Test Coverage

| Module | Test Cases |
|--------|-----------|
| Login | Valid login, invalid credentials, empty fields, SQL injection |
| Course Enrollment | Catalog load, enroll, search, progress tracking |
| Assessments | Load, submission, timer display |

## Setup

1. Clone the repo
2. Install Java 8 and Maven
3. Download ChromeDriver and place in `drivers/`
4. Update `src/main/resources/config.properties` with your environment URL

## Running Tests

```bash
# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=LoginTest

# Run with Firefox
mvn test -Dbrowser=firefox
```

## Notes

- Tests use Page Object Model for maintainability
- Driver setup is handled by `DriverFactory` — swap browser via config
- Screenshots on failure can be enabled in `DriverFactory.java`
# readme
