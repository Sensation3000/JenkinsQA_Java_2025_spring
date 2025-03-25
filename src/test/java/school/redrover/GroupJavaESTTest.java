package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.UUID;
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
    public void testLockedOutUserLogin() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        String title = driver.getTitle();
        Assert.assertEquals(title, "Swag Labs");

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("locked_out_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        sleep(1000);

        WebElement errorMessage = driver.findElement(By.xpath("//h3"));
        Assert.assertTrue(errorMessage.isDisplayed());

        sleep(1000);
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

    @Test
    public void testSuccessfulPurchase() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_badge")).click();

        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Amanda");
        driver.findElement(By.id("last-name")).sendKeys("Smith");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        assertEquals(driver.findElement(By.className("complete-header")).getText(), "Thank you for your order!");

        driver.quit();
    }

    public static String generateUniqueEmail(String domain) {
            String uniqueId = UUID.randomUUID().toString();
            return uniqueId + "@" + domain;
        }

    @Test
    public void testW3schoolsSignUp() throws InterruptedException {

        String domain = "gmail.com";
        String uniqueEmail = generateUniqueEmail(domain);

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.w3schools.com/");
        driver.findElement(By.xpath("//*[@id='pagetop']/div[3]/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div/div[5]/div/form/div[1]/input[1]")).sendKeys(uniqueEmail);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div/div[5]/div/form/div[1]/input[2]")).sendKeys("W3schoolstest!");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div/div[5]/div/form/div[1]/input[3]")).sendKeys("Natalya");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div/div[5]/div/form/div[1]/input[4]")).sendKeys("Galkina");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div/div[5]/div/form/div[3]/button")).click();

        Thread.sleep(1000);

        WebElement verifyEmailMessage = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div/div[3]/div/div/div/div[1]"));
        Assert.assertTrue(verifyEmailMessage.isDisplayed());

        driver.quit();
    }

    @Test
    public void testW3schoolsLoginWithValidCredentials() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/");
        driver.findElement(By.xpath("//*[@id=\"pagetop\"]/div[3]/a[1]")).click();
        driver.findElement(By.name("email")).sendKeys("bullkie2@gmail.com");
        driver.findElement(By.name("password")).sendKeys("W3schoolstest!");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div/div[5]/div/form/div[3]/button[2]")).click();

        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//p")).isDisplayed());

        driver.quit();
    }
}

