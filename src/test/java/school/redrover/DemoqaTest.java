package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoqaTest {
    @Test
    public void testTextBox() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com");

        WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']"));
        elementsSection.click();

        WebElement textBoxSection = driver.findElement(By.xpath("//span[text()='Text Box']"));
        textBoxSection.click();

        driver.findElement(By.id("userName")).sendKeys("Nick");
        driver.findElement(By.id("userEmail")).sendKeys("nick@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("325 Main St");
        driver.findElement(By.id("permanentAddress")).sendKeys("576 Main St");

        driver.findElement(By.cssSelector("#submit")).click();

        String outputName = driver.findElement(By.id("name")).getText();
        String outputEmail = driver.findElement(By.id("email")).getText();
        String outputCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
        String outputPermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();

        Assert.assertEquals(outputName, "Name:Nick");
        Assert.assertEquals(outputEmail, "Email:nick@gmail.com");
        Assert.assertTrue(outputCurrentAddress.contains("Current Address :325 Main St"));
        Assert.assertTrue(outputPermanentAddress.contains("Permananet Address :576 Main St"));

        driver.quit();
    }
}
