package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class ZeroToQAHeroTest {

    @Test
    public void testProductToCart() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().window().maximize();

        driver.get("https://www.demoblaze.com/");
        Thread.sleep(2000);

        List <WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'col-lg-4 col-md-6')]//h4/a[contains(@href, 'prod')]"));
        Assert.assertEquals(elements.size(), 9);

        String itemTitle = elements.get(0).getText();
        elements.get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//a[@onclick='addToCart(1)']"))).click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        WebElement cart = driver.findElement(By.xpath("//div//a[@href='cart.html']"));
        cart.click();

        String actualItemInCart =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//tr[@class='success']/td[2]"))).getText();
        Assert.assertEquals(actualItemInCart,itemTitle);
        driver.quit();
    }

    @Test
    public void testLogin() throws InterruptedException {

        WebDriver driver = new ChromeDriver();


        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://www.saucedemo.com/");

        String title = driver.getTitle();
        assertEquals("Swag Labs", title);

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement submitButton = driver.findElement(By.id("login-button"));
        Thread.sleep(1000);
        submitButton.click();

        //String productsTitile = driver.getTitle();
        WebElement pageName = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span"));
        assertEquals("Products", pageName.getText());

        driver.quit();

    }
}
