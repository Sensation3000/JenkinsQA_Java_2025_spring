package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JenkinsTest extends BaseTest{
    @Test
    public void testAddItem () {
        WebDriver driver = getDriver();

        WebElement newItem = driver.findElement(By.xpath("//*[@id='tasks']/div[1]"));
        newItem.click();
        WebElement itemName = driver.findElement(By.xpath("//*[@id='name']"));
        itemName.sendKeys("jobOne");
        WebElement selectType = driver.findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/div[2]"));
        selectType.click();
        driver.findElement(By.xpath("//*[@id='ok-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='job_jobOne']")).getText(), "jobOne");
    }
}
