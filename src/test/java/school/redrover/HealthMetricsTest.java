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

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"))).click();
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Test Folder");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"j-add-item-type-nested-projects\"]/ul/li[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);  // Скроллим к элементу
        element.click();
        driver.findElement(By.xpath("//*[@id=\"ok-button\"]")).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"side-panel\"]/div[1]/div[1]/h1")));
        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[2]/span/button"));
        WebElement titleHM = driver.findElement(By.xpath("//*[@id=\"health-metrics\"]"));
        Assert.assertEquals(titleHM.getText(), "Health metrics");
    }

}