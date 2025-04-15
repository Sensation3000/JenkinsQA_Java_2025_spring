package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;
import school.redrover.common.TestUtils;

public class FreestyleProjectConfigurationDescriptionTest extends BaseTest {

    @Test
    public void testAddValidDescriptionDuringFreestyleProjectCreation() {
        final String DESCRIPTION_TEXT = "Valid Freestyle Project Description";

        getDriver().findElement(
                By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(
                getDriver().findElement(By.id("name")))).sendKeys("Freestyle Project");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait5().until(ExpectedConditions.visibilityOf(
                getDriver().findElement(By.name("description")))).sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.name("Apply")).click();

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.visibilityOf(
                        getDriver().findElement(By.id("notification-bar")))).isDisplayed());
        Assert.assertEquals(
                getDriver().findElement(By.name("description")).getDomProperty("value"), DESCRIPTION_TEXT);
    }

    @Test
    public void testDescriptionCanBeEmpty() {
        WebDriver driver = getDriver();
        final String PROJECT_NAME = "FreestyleProject";

        driver.findElement(
                By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(
                driver.findElement(By.id("name")))).sendKeys(PROJECT_NAME);
        driver.findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        driver.findElement(By.id("ok-button")).click();
        TestUtils.openHomePage(this);
        TestUtils.openJobByName(driver, PROJECT_NAME);
        driver.findElement(By.xpath("//a[@href='/job/" + PROJECT_NAME + "/configure']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//textarea[@name='description']")))).clear();
        driver.findElement(By.name("Apply")).click();

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.visibilityOf(
                        getDriver().findElement(By.id("notification-bar")))).isDisplayed());
        Assert.assertTrue(
                driver.findElement(By.xpath("//textarea[@name='description']")).getText().isBlank());
    }
}
