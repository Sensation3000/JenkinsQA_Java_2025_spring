package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupAlexLeo {

    @Test
    public void testVeriyClickMeButton() {
        final String expectedButtonsText = "Buttons";
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.findElement(By.xpath("//div[@class='card-body']/h5[normalize-space(.)='Elements']")).click();
        driver.findElement(By.xpath("//li[@id='item-4']//span[normalize-space(.)='Buttons']")).click();

        String text = driver.findElement(By.xpath("//h1[@class='text-center']")).getText();

        Assert.assertEquals(text, expectedButtonsText);

        driver.quit();
    }
}
