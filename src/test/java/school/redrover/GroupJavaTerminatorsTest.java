package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class GroupJavaTerminatorsTest {
    private static WebDriver driver = new ChromeDriver();
    private static WebDriverWait wait;
    private static WebDriverWait  getWait() {
        return wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @BeforeMethod
    public void setUp() {


    }

    @Test
    public void authorizationSwagLabs() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.cssSelector("#user-name"));
        WebElement password = driver.findElement(By.cssSelector("#password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        Thread.sleep(5000);


        String pageTitle = driver.getTitle();

        assertEquals(pageTitle, "Swag Labs");

        driver.quit();
    }

    @Test(description = "Check, that Sauce Labs Backpack has been added to the cart and total price is 32.39")
    public void totalOrderPriceTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("90-021");
        driver.findElement(By.id("continue")).click();

        String expectedRes = driver.findElement(By.xpath("//div[@data-test='total-label']")).getText();

        Assert.assertEquals(expectedRes, "Total: $32.39");

        driver.quit();
    }

    @Test
    public void testValidRegistration() throws InterruptedException {
        Random random = new Random();
        String email = "Jacks" + random.nextInt(10) + "@gmail.com";


        driver.manage().window().maximize();


        driver.get("https://magento.softwaretestingboard.com/");

        WebElement createAccountButton = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='panel header']/ul/li[3]")));
        createAccountButton.click();

        driver.findElement(By.cssSelector("#firstname")).sendKeys("Jack");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Jack");
        driver.findElement(By.cssSelector("#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("#password")).sendKeys("12345678Ll");
        driver.findElement(By.cssSelector("#password-confirmation")).sendKeys("12345678Ll");
        WebElement submitButton = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.submit")));

        submitButton.click();

        String successMessageText = driver.findElement(By.cssSelector("div[data-ui-id]")).getText();

        assertEquals(successMessageText, "Thank you for registering with Main Website Store.");
      
         driver.quit();
    }
  
    @Test
    public void testTextAfterClickingButton() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://practice-automation.com/");

        driver.findElement(By.xpath("//div/div/a[@href='https://practice-automation.com/click-events/']")).click();
        driver.findElement(By.xpath("//button[@onclick='dogSound()']")).click();

        String actualResult = driver.findElement(By.xpath("//h2[@id='demo']")).getText();
        String expectedResult = "Woof!";

        Assert.assertEquals(actualResult, expectedResult);
      
        driver.quit();
    }
}