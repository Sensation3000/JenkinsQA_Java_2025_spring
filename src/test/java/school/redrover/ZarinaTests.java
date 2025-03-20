package school.redrover;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ZarinaTests {
    @Test
    public void uploadImageTest() {

        final String imageFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\uploadFiles\\java.png";
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[@href='/upload']")).click();
        driver.findElement(By.id("file-upload")).sendKeys(imageFilePath);
        driver.findElement(By.id("file-submit")).click();

        WebElement successMessage = driver.findElement(By.xpath("//h3[text()='File Uploaded!']"));
        String message = successMessage.getText();
        Assert.assertEquals(message, "File Uploaded!");
        driver.quit();
    }

    @Test
    public void dragDropTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement cubeA = driver.findElement(By.id("column-a"));
        WebElement cubeB = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(cubeA, cubeB).build().perform();
        driver.quit();
    }
}