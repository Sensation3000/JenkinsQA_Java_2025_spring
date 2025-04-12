package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItem4Test extends BaseTest {

    @Test
    public void testCreateFolder(){
     final String folderName = "Test folder";

     getDriver().findElement(By.linkText("New Item")).click();
     getDriver().findElement(By.name("name")).sendKeys(folderName);
     getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
     getDriver().findElement(By.id("ok-button")).click();
     getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("general")));
     getDriver().findElement(By.id("jenkins-home-link")).click();
     String actualFolderName = getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']")).getText();

     Assert.assertEquals(actualFolderName, folderName);
    }
}
