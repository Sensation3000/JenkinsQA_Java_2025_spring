package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GroupJavaESTTest {

    @Test
    public void testSuccessfulLogin() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        assertTrue(driver.findElement(By.className("app_logo")).isDisplayed());

        driver.quit();
    }

    @Test
    public void testLockedLogin() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        assertTrue(driver.findElement(By.className("error-message-container")).isDisplayed());

        driver.quit();
    }

    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        WebElement textBox = driver.findElement(By.id("user-name"));
        WebElement submitButton = driver.findElement(By.name("login-button"));
        textBox.sendKeys("standard_user");

        WebElement textBox2 = driver.findElement(By.id("password"));
        textBox2.sendKeys("secret_sauce");
        submitButton.click();

        driver.quit();
    }
}
