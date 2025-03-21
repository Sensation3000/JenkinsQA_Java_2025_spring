package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class EkatTest {

    @Test
    public void testSelenium() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        String actualMessage = driver.findElement(By.id("message")).getText();
        assertEquals(actualMessage, "Received!");

        driver.quit();
    }

    @Test
    public void testTitle() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://coffee-cart.app/");

        String title = driver.getTitle();
        assertEquals(title, "Coffee cart");

        driver.quit();
    }

    @Test
    public void testMenuButtons() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://coffee-cart.app/");

        WebElement menuButton = driver.findElement(By.cssSelector("a[aria-label='Menu page']"));
        assertEquals(menuButton.getText(), "menu");

        WebElement cartButton = driver.findElement(By.cssSelector("a[aria-label='Cart page']"));
        assertEquals(cartButton.getText(), "cart (0)");

        WebElement gitButton = driver.findElement((By.cssSelector("a[aria-label='GitHub page']")));
        assertEquals(gitButton.getText(),"github");

        driver.quit();
    }

    @Test
    public void testClickButtonCart() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://coffee-cart.app/");

        WebElement cartButton = driver.findElement(By.cssSelector("a[aria-label='Cart page']"));
        cartButton.click();
        assertEquals(driver.getCurrentUrl(), "https://coffee-cart.app/cart");

        driver.quit();
    }

    @Test
    public void testClickButtonGithub() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://coffee-cart.app/");

        WebElement gitButton = driver.findElement(By.cssSelector("a[aria-label='GitHub page']"));
        gitButton.click();
        assertEquals(driver.getCurrentUrl(), "https://coffee-cart.app/github");

        driver.quit();
    }
}
