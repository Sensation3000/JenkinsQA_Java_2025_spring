package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class EvgenyZagrebelnikovTest {

    @Test
    public void testGoToTheArchiveSection() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://world-weather.ru/pogoda/");

        driver.findElement(By.xpath("//*[@id=\"meny\"]/li[2]/a")).click();

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

        driver.findElement(By.xpath("//*[text()='Elements']")).click();
        driver.findElement(By.xpath("//*[text()='Check Box']")).click();
        driver.findElement(By.xpath("//*[@class='rct-checkbox']")).click();

        WebElement textAfterCheckboxSelect = driver.findElement(By.xpath("//*[text()='You have selected :']"));
        String resultAfterCheckBoxSelected = textAfterCheckboxSelect.getText();

        Assert.assertEquals(resultAfterCheckBoxSelected, "You have selected :");

        driver.quit();
    }

    @Test
    public void testRadioButtonYes() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.findElement(By.xpath("//*[text()='Elements']")).click();
        driver.findElement(By.xpath("//*[text()='Radio Button']")).click();
        driver.findElement(By.xpath("//*[text()='Yes']")).click();

        String resultAfterRadioButtonSelected = driver.findElement(By.xpath("//*[text()='You have selected ']")).getText();

        Assert.assertEquals(resultAfterRadioButtonSelected, "You have selected Yes");

        driver.quit();
    }

    @Test
    public void testDoubleClickButton() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.findElement(By.xpath("//*[text()='Elements']")).click();
        driver.findElement(By.xpath("//*[text()='Buttons']")).click();
        WebElement doubleClickMeButton = driver.findElement(By.xpath("//*[text()='Double Click Me']"));

        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickMeButton).perform();
        String resultDoubleClick = driver.findElement(By.xpath("//*[text()='You have done a double click']")).getText();

        Assert.assertEquals(resultDoubleClick, "You have done a double click");

        driver.quit();
    }

    @Test
    public void testRightClickButton() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.findElement(By.xpath("//*[text()='Elements']")).click();
        driver.findElement(By.xpath("//*[text()='Buttons']")).click();
        WebElement rightClickButton = driver.findElement(By.xpath("//*[text()='Right Click Me']"));

        Actions actions = new Actions(driver);
        actions.contextClick(rightClickButton).perform();
        String resultRightClick = driver.findElement(By.xpath("//*[text()='You have done a right click']")).getText();

        Assert.assertEquals(resultRightClick, "You have done a right click");

        driver.quit();
    }

    @Test
    public void testRadioButtonImpressive() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.findElement(By.xpath("//*[text()='Elements']")).click();
        driver.findElement(By.xpath("//*[text()='Radio Button']")).click();
        driver.findElement(By.xpath("//*[text()='Impressive']")).click();

        String resultAfterRadioButtonSelected = driver.findElement(By.xpath("//*[text()='You have selected ']")).getText();

        Assert.assertEquals(resultAfterRadioButtonSelected, "You have selected Impressive");

        driver.quit();
    }
}
