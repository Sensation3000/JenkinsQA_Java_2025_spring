package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItem15Test extends BaseTest {
    @Test
    public void testAddItem () throws InterruptedException {
        WebDriver driver = getDriver();

        WebElement newItem = driver.findElement(By.xpath("//*[@id='tasks']/div[1]"));
        newItem.click();
        WebElement itemName = driver.findElement(By.xpath("//*[@id='name']"));
        itemName.sendKeys("jobOne");
        WebElement selectType = driver.findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/div[2]"));
        selectType.click();
        driver.findElement(By.xpath("//*[@id='ok-button']")).click();
        Thread.sleep(1500);

        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/job/jobOne/configure");
    }
}
