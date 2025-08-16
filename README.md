UI Automation Testing Project for OrangeHRM
Overview
This project automates UI testing for the OrangeHRM web application (https://opensource-demo.orangehrmlive.com/) using Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern. The primary test case validates the addition and deletion of a user in the Admin module, including login, navigation, and verification of record counts.
Project Structure
ui-automation/
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── pages
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── AdminPage.java
│   │   │   ├── tests
│   │   │   │   └── AdminUserTest.java
│   │   └── resources
├── pom.xml
└── README.md


pages/: Contains POM classes (LoginPage, AdminPage) for encapsulating page elements and actions.
tests/: Contains TestNG test class (AdminUserTest) for the test scenario.
pom.xml: Maven configuration with dependencies.
README.md: This documentation.

Prerequisites

Java: JDK 17 or higher (ensure JAVA_HOME is set).
Maven: For dependency management (run mvn -v to verify).
Chrome Browser: Latest version installed.
ChromeDriver: Compatible with your Chrome version (or use WebDriverManager for automatic management).
IDE: IntelliJ IDEA, Eclipse, or similar (optional for running tests).

Setup Instructions

Clone the Repository:git clone <repository-url>
cd ui-automation


Install Dependencies:Run the following command to download dependencies:mvn clean install


ChromeDriver Setup (if not using WebDriverManager):
Download ChromeDriver from https://chromedriver.chromium.org/downloads.
Set the system property in AdminUserTest.java:System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");


Alternatively, add WebDriverManager dependency to pom.xml:<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>

Then use:WebDriverManager.chromedriver().setup();





Dependencies

Selenium WebDriver: 4.23.0
TestNG: 7.10.2
Maven Compiler Plugin: Configured for Java 17

See pom.xml for full details.
Running Tests

From IDE:
Open the project in your IDE.
Right-click src/test/java/tests/AdminUserTest.java and select "Run As > TestNG Test".


From Command Line:mvn test


Test Output:
Console logs show test execution steps.
TestNG reports are generated in target/surefire-reports.



Test Scenario
The test case (AdminUserTest) performs the following:

Logs in to OrangeHRM with valid credentials (Admin, admin123).
Navigates to the Admin tab.
Captures the initial number of user records.
Adds a new user with details (e.g., username: testuser123).
Verifies the record count increases by 1.
Searches for the new user.
Deletes the user.
Verifies the record count returns to the initial value.

Features

POM Design: Modular page classes (LoginPage, AdminPage) for maintainability.
Explicit Waits: WebDriverWait ensures reliable element interaction.
Assertions: TestNG assertions verify expected outcomes.
Clean Teardown: Browser closes after each test.

Notes

Locators: Based on the current OrangeHRM UI (as of August 2025). Update locators in LoginPage.java and AdminPage.java if the UI changes.
Browser Compatibility: Tested with Chrome. For other browsers, update the driver (e.g., FirefoxDriver for Firefox).
Credentials: Uses default OrangeHRM credentials (Admin, admin123). Update if different.
Error Handling: Tests assume a stable environment. Add try-catch for production use.

Troubleshooting

ElementNotFoundException: Verify locators in page classes; the UI may have changed.
Driver Issues: Ensure ChromeDriver matches your Chrome version or use WebDriverManager.
Test Failures: Check network connectivity and OrangeHRM site availability.

Future Improvements

Add data-driven tests using TestNG @DataProvider.
Support multiple browsers (e.g., Firefox, Edge).
Integrate with CI/CD (e.g., Jenkins, GitHub Actions).
Add logging (e.g., Log4j or SLF4J) for detailed debugging.
