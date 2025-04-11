package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class WelcomeDashboardTest extends BaseTest {

    @Test
    public void testPossibleToCreateJobFromDashboard() {
        getDriver().findElement(By.xpath("//li/a[@href='newJob']")).click();

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.visibilityOf(
                        getDriver().findElement(By.id("add-item-panel")))).isDisplayed());
    }

    @Test
    public void testCancelJobCreationFromDashboard() {
        getDriver().findElement(By.xpath("//li/a[@href='newJob']")).click();
        getDriver().navigate().back();

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.visibilityOf(
                        getDriver().findElement(By.xpath("//li/a[@href='newJob']")))).isDisplayed());
    }

    @Test
    public void testOpenManageJenkinsFromDashboard() {
        getDriver().findElement(By.linkText("Manage Jenkins")).click();

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.visibilityOf(
                        getDriver().findElement(By.xpath("//div[@class='jenkins-app-bar__content']/h1")))).isDisplayed());
    }
}
