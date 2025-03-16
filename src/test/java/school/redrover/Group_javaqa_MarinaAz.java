package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class Group_javaqa_MarinaAz {

    @Test
    public void testSelenium() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
            WebElement textBox1 = driver.findElement(By.name("password"));
            WebElement submitButton = driver.findElement(By.tagName("button"));

            textBox.sendKeys("Admin");
            textBox1.sendKeys("admin123");
            submitButton.click();

            WebElement pageName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h6")));
            String value = pageName.getText().trim();

            System.out.println("Extracted text: " + value);
            assertEquals("Dashboard", value);

        } finally {
            driver.quit();
        }
    }
}




