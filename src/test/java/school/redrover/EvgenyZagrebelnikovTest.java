package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EvgenyZagrebelnikovTest {

    @Test
    public void testGoToTheArchiveSection() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://world-weather.ru/pogoda/");

        WebElement archiveButton = driver.findElement(By.xpath("//*[@id=\"meny\"]/li[2]/a"));
        archiveButton.click();

        WebElement textTitle = driver.findElement(By.xpath("//*[@id=\"content-left\"]/h1"));
        String text = textTitle.getText();

        Assert.assertEquals(text, "Архив погоды в мире");

        driver.quit();
    }

    @Test
    public void testSearchAndSelectCity() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://openweathermap.org/");

        WebElement searchInputFieldAndEnterCityName = driver.findElement(By.xpath("//input[@placeholder='Search city']"));
        searchInputFieldAndEnterCityName.sendKeys("London");

        WebElement searchButtonClick = driver.findElement(By.xpath("//button[text()='Search']"));
        searchButtonClick.click();
        Thread.sleep(2000);

        WebElement selectCityFromList = driver.findElement(By.xpath("//*[text()='London, CA ']"));
        selectCityFromList.click();
        Thread.sleep(2000);

        WebElement checkCityTitle = driver.findElement(By.xpath("//*[text()='London, CA']"));
        String text = checkCityTitle.getText();

        Assert.assertEquals(text, "London, CA");

        driver.quit();
    }

    @Test
    public void testCheckBoxElement() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        WebElement buttonElements = driver.findElement(By.xpath("//*[text()='Elements']"));
        buttonElements.click();

        WebElement selectTabCheckBox = driver.findElement(By.xpath("//*[text()='Check Box']"));
        selectTabCheckBox.click();

        WebElement searchCheckBoxAndClick = driver.findElement(By.xpath("//*[@class='rct-checkbox']"));
        searchCheckBoxAndClick.click();

        WebElement textAfterCheckboxSelect = driver.findElement(By.xpath("//*[text()='You have selected :']"));
        String text = textAfterCheckboxSelect.getText();

        Assert.assertEquals(text, "You have selected :");

        driver.quit();
    }

    @Test
    public void testRadioButtonElement() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.findElement(By.xpath("//*[text()='Elements']")).click();
        driver.findElement(By.xpath("//*[text()='Radio Button']")).click();
        driver.findElement(By.xpath("//*[text()='Yes']")).click();

        String text = driver.findElement(By.xpath("//*[text()='You have selected ']")).getText();

        Assert.assertEquals(text, "You have selected Yes");

        driver.quit();
    }
}
