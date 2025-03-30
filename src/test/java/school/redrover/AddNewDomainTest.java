package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;


public class AddNewDomainTest extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert() ;

    @Test
    public void testAddNewDomain(){
        WebDriver driver = getDriver() ;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.xpath("//a[contains(.,'Manage Jenkins')]")).click();
        driver.findElement(By.xpath("//a[@href='credentials']")).click();
        driver.findElement(By.cssSelector("a[href='/manage/credentials/store/system']")).click();
        driver.navigate().to("http://localhost:8080/manage/credentials/store/system");
        driver.findElement(By.xpath("//a[@href='newDomain']")).click();
        driver.findElement(By.xpath("//input[@name='_.name']")).sendKeys("Test Domain");
        driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Test domain description");
        driver.findElement(By.xpath("//button[@type='submit' and @name='Submit']")).click();

        String testDomainLink = driver.findElement(By.cssSelector("a[href='/manage/credentials/store/system/domain/Test%20Domain/']")).getText();
        softAssert.assertEquals(testDomainLink, "Test Domain") ;

        String testDomainDescription = driver.findElement(By.xpath("//div[text()='Test domain description']")).getText();
        softAssert.assertEquals(testDomainDescription, "Test domain description") ;

        String testDomainHeader = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[1]/div[1]/h1")).getText();
        softAssert.assertEquals(testDomainHeader, "Test Domain") ;

        driver.findElement(By.xpath("//a[@href='delete']")).click();
        driver.findElement(By.name("Submit")).click();
        softAssert.assertAll();
    }
}
