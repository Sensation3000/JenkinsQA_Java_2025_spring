package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
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
        String PROJECT_NAME = "Freestyle Project";

        TestUtils.createFreestyleProject(driver, PROJECT_NAME);
        driver.navigate().to("http://localhost:8080/job/" + PROJECT_NAME + "/configure");
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
