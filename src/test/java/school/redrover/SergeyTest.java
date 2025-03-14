package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SergeyTest {

    @Test
    public void testExample() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textBox = driver.findElement(By.id("my-text-id"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();

        assertEquals(value, "Received!");

        driver.quit();
    }

}
