package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class Folder1Test extends BaseTest {

    @Test
    public void testIfCopyFromFieldAppears() {
        final String folderName = "My Super Cool Folder";

        WebDriver driver = getDriver();

        TestUtils.newItemCreate(this, folderName, 4);
        driver.findElement(By.linkText("New Item")).click();

        Assert.assertEquals(driver.findElement(By.className("add-item-copy")).getText(), "Copy from");

    }

    @Test
    public void testAddItemToFolder() {
        final String folderName = "First folder";
        final String itemName = "First item";

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div/section[1]/ul/li/a")).click();
        driver.findElement(By.id("name")).sendKeys(folderName);
        driver.findElement(By.className("com_cloudbees_hudson_plugins_folder_Folder")).click();
        driver.findElement(By.id("ok-button")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit")));
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[4]/div/section/ul/li/a")).click();
        driver.findElement(By.id("name")).sendKeys(itemName);
        driver.findElement(By.className("hudson_model_FreeStyleProject")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
        // driver.findElement(By.xpath("//*[@id=\"breadcrumbs\"]/li[3]/a")).click();

        Assert.assertEquals(
                "Full project name: First folder/First item",
                "Full project name: " + folderName + "/" + itemName
        );

    }
}
