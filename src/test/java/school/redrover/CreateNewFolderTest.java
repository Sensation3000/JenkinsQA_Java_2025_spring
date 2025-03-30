package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class CreateNewFolderTest extends BaseTest {

    @Test
    public void testCreateFolder() {
        getDriver().findElement(By.xpath("//a[contains(@it,'hudson')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("NewFolder");
        getDriver().findElement(By.xpath("//li[contains(@class,'Folder')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//input[@checkdependson]")).sendKeys("Folder1");
        getDriver().findElement(By.name("Submit")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText();

        Assert.assertEquals(actualText, "Folder1");
    }
}
