package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdminPage {
    private final WebDriver driver;
    private final By adminTab = By.linkText("Admin");
    private final By recordsFound = By.cssSelector(".orangehrm-horizontal-padding");
    private final By addButton = By.cssSelector("button.oxd-button--secondary:nth-child(1)");

    private final By userRoleSelect = By.cssSelector("div.oxd-form-row:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)");
    private final By statusSelect = By.cssSelector("div.oxd-grid-item:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)");
    private final By employeeName = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input");
    private final By usernameField = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(1) > div > div:nth-child(4) > div > div:nth-child(2) > input");
    private final By passwordField = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.oxd-form-row.user-password-row > div > div.oxd-grid-item.oxd-grid-item--gutters.user-password-cell > div > div:nth-child(2) > input");
    private final By confirmPasswordField = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.oxd-form-row.user-password-row > div > div:nth-child(2) > div > div:nth-child(2) > input");
    private final By saveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");
    private final By searchUsername = By.cssSelector("input.oxd-input:nth-child(1)");
    private final By searchButton = By.cssSelector("button.oxd-button:nth-child(2)");
    private final By deleteButton = By.cssSelector("button.oxd-table-cell-action-space:nth-child(1)");
    private final By confirmDelete = By.cssSelector(".oxd-button--label-danger");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAdminTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(adminTab));
        driver.findElement(adminTab).click();
    }

    public int getRecordsCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordsFound));
        String text = driver.findElement(recordsFound).getText();
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    public void clickAddButton() {
        driver.findElement(addButton).click();
    }

    public void fillUserData(String userRole, String empName, String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userRoleSelect));

        WebElement roleDropdown = driver.findElement(userRoleSelect);
        WebElement statusDropdown = driver.findElement(statusSelect);

        roleDropdown.click();
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Admin']")).click();

        statusDropdown.click();
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Enabled']")).click(); // Select 'Admin'

        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        WebElement empInput = driver.findElement(employeeName);
        empInput.sendKeys(empName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-autocomplete-dropdown")));
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='John Peter Vaz']")).click();
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public void searchUser(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsername));
        driver.findElement(searchUsername).sendKeys(username);
        driver.findElement(searchButton).click();
    }

    public void deleteUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        driver.findElement(deleteButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDelete));
        driver.findElement(confirmDelete).click();
    }
}