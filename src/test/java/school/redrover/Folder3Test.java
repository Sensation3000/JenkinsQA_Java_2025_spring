package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.UUID;

public class Folder3Test extends BaseTest {

    @Ignore // Folder3Test.testCreate:24 » Stale element not found Element: -> id: jenkins-name-icon]
    @Test
    public void testCreate() {
        String folderName = "MyFolderName";
        String folderLink = "job/" + folderName + "/";

        WebDriver driver = getDriver();

        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.id("name")).sendKeys(folderName);
        driver.findElement(By.xpath("//li[.//span[text()='Folder']]")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.id("jenkins-name-icon")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//a[@href='" + folderLink + "']")).getText(), folderName);
    }
}