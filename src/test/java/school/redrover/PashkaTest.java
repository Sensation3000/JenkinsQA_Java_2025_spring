package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PashkaTest {

    @Test
    public void testGoogle()  {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement inputLog = driver.findElement(By.id("username"));
        inputLog.sendKeys("tomsmith");

        WebElement inputPass = driver.findElement(By.id("password"));
        inputPass.sendKeys("SuperSecretPassword!");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"login\"]/button/i"));
        button.click();

        WebElement site = driver.findElement(By.xpath("//*[@id=\"flash\"]"));
        String siteText = site.getText();

        Assert.assertTrue(siteText.contains("You logged into a secure area!"));

        driver.quit();



    }
}


