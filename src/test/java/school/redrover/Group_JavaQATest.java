package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Group_JavaQATest {
    By firstName = By.id("request-pricing_firstName");
    By lastName = By.id("request-pricing_lastName");
    By countryOfResidence = By.xpath("//*[@data-id='request-pricing_country']");
    By countryOfResidenceSelect = By.xpath("//span[text()='United States']");
    By state = By.xpath("//*[@id='request-pricing_state']/following-sibling::button");
    By selectState = By.xpath("//*[@id='request-pricing']//select[@id='request-pricing_state']/parent::div//a[span[text()='Alaska']]");
    By telephoneNumber = By.id("request-pricing_phone");
    By email = By.id("request-pricing_email");
    By voyageOfInterest = By.xpath("//*[@id='request-pricing_voyageOfInterest']/following-sibling::button");
    By voyageOfInterestSelect = By.xpath("//span[contains(text(),'168 nights')]");
    By submitButton = By.xpath("//*[@id='request-pricing']//button[@type='submit']");
    By conformationPage = By.xpath("//div[contains(text(),'Your Personal Consultant')]/ancestor::article//h2");

    @Test
    public void testRequestQuote () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa1-aws.rssc.com/request-quote");

        driver.findElement(firstName).sendKeys("Viviana");
        driver.findElement(lastName).sendKeys("Costa");
        driver.findElement(countryOfResidence).click();
        driver.findElement(countryOfResidenceSelect).click();
        Thread.sleep(3000);
        driver.findElement(state).click();
        Thread.sleep(3000);
        driver.findElement(selectState).click();
        driver.findElement(telephoneNumber).sendKeys("7865187654");
        driver.findElement(email).sendKeys("viviana@gmail.com");
        driver.findElement(voyageOfInterest).click();
        driver.findElement(voyageOfInterestSelect).click();
        driver.findElement(submitButton).click();
        Thread.sleep(5000);
        assertTrue(driver.findElement(conformationPage).isDisplayed(),"Thank you page is displayed");

        driver.quit();
    }

    @Test
    public void testMarina() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
            WebElement textBox1 = driver.findElement(By.name("password"));
            WebElement submitButton = driver.findElement(By.tagName("button"));

            textBox.sendKeys("Admin");
            textBox1.sendKeys("admin123");
            submitButton.click();

            WebElement pageName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h6")));
            String value = pageName.getText().trim();

            System.out.println("Extracted text: " + value);
            assertEquals("Dashboard", value);

        } finally {
            driver.quit();
        }
    }
}
