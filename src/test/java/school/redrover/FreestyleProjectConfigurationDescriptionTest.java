package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class FreestyleProjectConfigurationDescriptionTest extends BaseTest {
    private final String PROJECT_NAME = "FreestyleProject";
    private final String DESCRIPTION_TEXT = "Valid Freestyle Project Description";

    @Ignore
    @Test
    public void testAddValidDescriptionDuringFreestyleProjectCreation() {
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

    @Test
    public void testPreviewDescriptionOption() {
        TestUtils.createFreestyleProject(getDriver(), PROJECT_NAME);
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("general"))));
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.className("textarea-show-preview")).click();

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.visibilityOf(
                        getDriver().findElement(By.className("textarea-preview")))).isDisplayed(),
                "Preview Description area is not displayed.");
        Assert.assertEquals(
                getDriver().findElement(By.className("textarea-preview")).getText(), DESCRIPTION_TEXT,
                "Text in Preview Description doesn't match text in Description field");
    }

    @Test
    public void testHidePreviewDescriptionOption() {
        TestUtils.createFreestyleProject(getDriver(), PROJECT_NAME);
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("general"))));
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.className("textarea-show-preview")).click();
        getDriver().findElement(By.className("textarea-hide-preview")).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.invisibilityOfElementWithText(
                By.className("textarea-hide-preview"), "Hide preview")));
        Assert.assertFalse(
                getDriver().findElement(By.className("textarea-preview")).isDisplayed());
    }
}
