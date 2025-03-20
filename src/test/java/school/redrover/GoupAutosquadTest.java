package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class GoupAutosquadTest {

    @Test
    public void test(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://the-internet.herokuapp.com");
        driver.findElement(By.linkText("Form Authentication")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content h2")));
        WebElement h2element = driver.findElement(By.cssSelector("#content h2"));
        assertEquals(h2element.getText(), "Login Page", "H2 header is incorrect");
        driver.quit();
    }
}
