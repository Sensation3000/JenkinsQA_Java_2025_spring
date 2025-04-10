package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class Dashboard2Test extends BaseTest {

    @Test
    public void testPossibleToCreateJobFromDashboard() {
        getDriver().findElement(By.xpath("//li/a[@href='newJob']")).click();

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.visibilityOf(
                        getDriver().findElement(By.id("add-item-panel")))).isDisplayed());

    }
}
