package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SvetaMashHWTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test // open page and check the text on the welcome page
    public void OpenPageTest(){
        driver.get("https://the-internet.herokuapp.com/");

        WebElement welcomePage = driver.findElement(By.cssSelector(".heading"));
        Assert.assertEquals(welcomePage.getText(), "Welcome to the-internet");

    }

    @Test // open the page and click the first link
    public void OpenABLinkTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");

        WebElement welcomePage = driver.findElement(By.cssSelector(".heading"));
        Assert.assertEquals(welcomePage.getText(), "Welcome to the-internet");

        Thread.sleep(1000);

        WebElement openLink = driver.findElement(By.cssSelector("#content > ul:nth-child(4) > li:nth-child(1) > a:nth-child(1)"));
        openLink.click();

        Thread.sleep(1000);

        WebElement pageAB = driver.findElement(By.cssSelector(".example > h3:nth-child(1)"));
        Assert.assertEquals(pageAB.getText(), "A/B Test Variation 1");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
