package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class Folder4Test extends BaseTest {

    @Test
    public void testCreatingAFolder() throws InterruptedException {
        WebDriver driver = getDriver();
        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id='tasks']/div[4]/span/a")).click();
        driver.findElement(By.xpath("//*[@id='main-panel']/div[2]/div/section/ul/li/a")).click();
        driver.findElement(By.id("name")).sendKeys("New_Folder");
        driver.findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        driver.findElement(By.id("ok-button")).click();
        Thread.sleep(500);
        String h2Text = driver.findElement(By.xpath("//*[@id='general']/span")).getText();
        Assert.assertEquals(h2Text, "General");
    }
}
