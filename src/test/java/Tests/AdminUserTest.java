package Tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.AdminPage;
import Pages.LoginPage;
import org.testng.asserts.SoftAssert;



public class AdminUserTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private AdminPage adminPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
    }

    @Test
    public void testAddAndDeleteUser() {
        loginPage.login("Admin", "admin123");
        adminPage.clickAdminTab();


        int initialCount = adminPage.getRecordsCount();

        adminPage.clickAddButton();
        adminPage.fillUserData("Admin", "John Peter Vaz", "testuser123", "Test@123");
        adminPage.clickSave();
        //adminPage.clickAdminTab();
        int newCount = adminPage.getRecordsCount();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newCount, initialCount + 1, "Records did not increase by 1");


       adminPage.searchUser("testuser123");

       adminPage.deleteUser();

       int finalCount = adminPage.getRecordsCount();
       softAssert.assertEquals(finalCount, initialCount, "Records did not decrease by 1");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
       }
   }
}
