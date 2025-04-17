package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import org.openqa.selenium.JavascriptExecutor;


public class HealthMetricsTest extends BaseTest {

    @Test
    public void  checkAvailabilityHealthMetrics(){
        WebDriver driver = getDriver();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys("Test Folder");
        WebElement element = driver.findElement(By.className("com_cloudbees_hudson_plugins_folder_Folder"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);  // Скроллим к элементу
        element.click();
        driver.findElement(By.xpath("//*[@id='ok-button']")).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='side-panel']/div[1]/div[1]/h1")));
        driver.findElement(By.cssSelector("button[data-section-id='health-metrics']")).click();
        WebElement titleHM = driver.findElement(By.xpath("//*[@id='health-metrics']"));
        Assert.assertEquals(titleHM.getText(), "Health metrics");
    }

}