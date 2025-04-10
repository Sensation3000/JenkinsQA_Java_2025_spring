package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
}
