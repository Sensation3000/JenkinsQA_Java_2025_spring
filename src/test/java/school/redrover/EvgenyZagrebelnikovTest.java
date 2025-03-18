package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EvgenyZagrebelnikovTest {

    @Test
    public void testTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://world-weather.ru/pogoda/");

        WebElement documentationButton = driver.findElement(By.xpath("//*[@id=\"meny\"]/li[2]/a"));
        Thread.sleep(4000);
        documentationButton.click();

        WebElement textTitle = driver.findElement(By.xpath("//*[@id=\"content-left\"]/h1"));
        String text = textTitle.getText();

        Assert.assertEquals(text, "Архив погоды в мире");

        driver.quit();
    }
}
