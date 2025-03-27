package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static java.sql.DriverManager.getDriver;

public class SetupBuildTest extends BaseTest {
    @Test(description = "Build")
    public void testTooltipWhenNewItemWasAddedWOName() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[@href=\"/view/all/newJob\"]")).click();
        getDriver().findElement(By.xpath("//input[@name=\"name\"]")).sendKeys("", Keys.TAB);
        Thread.sleep(1000);
        String result = getDriver().findElement(By.id("itemname-required")).getText();

        Assert.assertEquals(result,"» This field cannot be empty, please enter a valid name", "Tooltip is not displayed");

    }
}
