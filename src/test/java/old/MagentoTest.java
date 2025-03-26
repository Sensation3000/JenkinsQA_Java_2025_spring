package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class MagentoTest {
    private WebDriver driver;
    private static final String PASSWORD = "Password123!";

    private String generateUniqueEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @Test
    public void testHomePageLoads() {
        Assert.assertTrue(driver.getTitle().contains("Home Page"), "Home Page should load correctly.");
    }

    @Test
    public void testNewUserRegistration() {

        driver.findElement(By.linkText("Create an Account")).click();
        driver.findElement(By.id("firstname")).sendKeys("Test");
        driver.findElement(By.id("lastname")).sendKeys("User");

        String email = generateUniqueEmail();

        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("password-confirmation")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("button[title='Create an Account']")).click();

        WebElement successMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='message-success success message']")));

        Assert.assertTrue(successMsg.getText().contains("Thank you for registering"),
                "Registration success message should be displayed.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
