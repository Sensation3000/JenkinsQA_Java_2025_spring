package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class Folder2Test extends BaseTest {

    @Test
    public void testCreate() {
        String folderName = UUID.randomUUID().toString();
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