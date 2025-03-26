package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Ignore
public class SvetaMashHWTest {
    private WebDriver driver;
    private final String CURRENT_URL = "https://the-internet.herokuapp.com/";


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test // open page and check the text on the welcome page
    public void OpenPageTest() {
        driver.get(CURRENT_URL);
        WebElement welcomePage = driver.findElement(By.cssSelector(".heading"));
        Assert.assertEquals(welcomePage.getText(), "Welcome to the-internet");
    }

    @Test
    public void OpenAD_Remove_LinkTest() {
        driver.get(CURRENT_URL);
        new WebDriverWait(driver, Duration.ofMillis(1000)).
                until(el -> driver.findElement(By.cssSelector(".heading")).isDisplayed());

        WebElement openLink = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[2]/a"));
        openLink.click();

        WebElement pageAddEl = driver.findElement(By.xpath("//*[@id=\"content\"]/h3"));
        Assert.assertEquals(pageAddEl.getText(), "Add/Remove Elements");
    }

    @Test
    public void checkIfCheckboxClickable() {
        driver.get(CURRENT_URL);
        new WebDriverWait(driver, Duration.ofMillis(1000)).
                until(el -> driver.findElement(By.cssSelector(".heading")).isDisplayed());

        WebElement checkBoxLink = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[6]/a"));
        checkBoxLink.click();

        WebElement checkbox = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")) ;
        if (checkbox.isEnabled()) {
            checkbox.click();
        }
        Assert.assertTrue(checkbox.isSelected(), "Checkbox should be selected.");
    }

    @Test
    public void checkPageKeyPress () throws InterruptedException {
        driver.get(CURRENT_URL);
        new WebDriverWait(driver, Duration.ofMillis(1000)).
                until(el -> driver.findElement(By.cssSelector(".heading")).isDisplayed());

        WebElement keyPressLink = driver.findElement(By.cssSelector("#content > ul > li:nth-child(31) > a"));
        keyPressLink.click();
        Thread.sleep(1000);

        WebElement inputForm = driver.findElement(By.id("target"));
        inputForm.sendKeys("A");
        Assert.assertEquals(inputForm.getDomProperty("value"), "A", "Input value does not match expected text.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}