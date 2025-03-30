package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewFolderTest extends BaseTest {

    @Test
    public void testCreateFolder() {
        getDriver().findElement(By.xpath("//a[contains(@it,'hudson')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("NewFolder");
        getDriver().findElement(By.xpath("//li[contains(@class,'Folder')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//input[@checkdependson]")).sendKeys("Folder1");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Folder1");
    }
}
