package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;


public class KirillinaTest {
    WebDriver driver;

    @BeforeMethod
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    @Test
    void testHeading(){
        assertEquals(
                driver.findElement(By.xpath("//h1[@class='display-4']")).getText(),
                "Hands-On Selenium WebDriver with Java");
    }

    @Test
    void testChaptersSize(){
        List<WebElement> chapters = driver.findElements(By.cssSelector("h5.card-title"));

        assertEquals(chapters.size(), 6);
    }
}
