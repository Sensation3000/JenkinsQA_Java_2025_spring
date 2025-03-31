package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreationItemsTest extends BaseTest {
    @Test
    public void createFolderTest () throws InterruptedException {WebDriver driver = getDriver();
        String folderName = "NewFolder";
        driver.findElement(By.linkText("Create a job")).click();
        driver.findElement(By.cssSelector("[name='name']")).sendKeys(folderName);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Folder']"))).click().perform();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),folderName,"Folder names should be the same");
        Assert.assertEquals(driver.findElement(By.xpath("//h1/*")).getDomAttribute("tooltip"),"Folder","Names of tooltip should be the same");
    }
}
