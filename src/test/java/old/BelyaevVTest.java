package old;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class BelyaevVTest {

    @Test
    public void testCheckResultSearch() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://thecode.media/");

        WebElement searchButton = driver.findElement(By.className("heading-search__open"));
        searchButton.click();

        WebElement searchText = driver.findElement(By.className("heading-search__input"));
        searchText.sendKeys("Api1110");

        searchButton.click();

        WebElement foundText = driver.findElement(By.className("search__title"));
        String resultSearch = foundText.getText();

        Assert.assertEquals(resultSearch, "apin");

        driver.quit();
    }

    @Test
    public void testAppearedHeader() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://thecode.media/");

        WebElement searchArea = driver.findElement(By.className("tab-questions"));

        Action myAction = new Actions(driver).doubleClick(searchArea).build();
        myAction.perform();

        WebElement foundText = driver.findElement(By.xpath("(//h1[@class='search__title'])"));
        String foundSearchTitle = foundText.getText();

        Assert.assertEquals(foundSearchTitle, "Как решить");

        driver.quit();
    }

    @Test
    public void testFillForm() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys("Ivan");
        driver.findElement(By.id("lastName")).sendKeys("Ivanov");
        driver.findElement(By.id("userEmail")).sendKeys("qaeng@aga.com");
        driver.findElement(By.cssSelector("[for='gender-radio-1']")).click();
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

        WebElement submitButton = driver.findElement(By.id("submit"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", submitButton);
        submitButton.click();

        String foundSearchTitle = driver.findElement(By.className("modal-title")).getText();

        Assert.assertEquals(foundSearchTitle, "Thanks for submitting the form");

        driver.quit();
    }

}
