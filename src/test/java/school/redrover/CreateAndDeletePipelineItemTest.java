package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateAndDeletePipelineItemTest extends BaseTest {
    @Ignore
    //Error:    CreateAndDeletePipelineItemTest.testCreateAndDeletePipelineItem:29 » StaleElementReference stale element reference: stale element not found
    @Test
    public void testCreateAndDeletePipelineItem() {
        WebDriver driver = getDriver();

        //Click on New Item
        driver.findElement(By.xpath("//a[@href='newJob']")).click();

        //input text, choose radio and click ОК
        driver.findElement(By.id("name")).sendKeys("somePipeline");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
        Assert.assertEquals(driver.findElement(By.className("job-index-headline")).getText(), "somePipeline");

        //delete Item
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-url*='doDelete']")));
        driver.findElement(By.cssSelector("a[data-url*='doDelete']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-id='ok']")));
        driver.findElement(By.cssSelector("button[data-id='ok']")).click();
        Assert.assertNotEquals(driver.findElement(By.id("jenkins")).getText(), "somePipeline");

    }

    @Test
    public void getTextOnCreateEmptyNamePipelineItem() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='newJob']")).click();

        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        Assert.assertEquals(driver.findElement(By.id("itemname-required")).getText(), "» This field cannot be empty, please enter a valid name");

    }

    @Test
    public void isDisabledButtonOKOnCreateNamePipelineItem() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='newJob']")).click();

        driver.findElement(By.id("name")).sendKeys("somePipeline");
        Assert.assertTrue(driver.findElement(By.id("ok-button")).isDisplayed());

    }

    @Test
    public void isEnabledProjectOnCreatePipelineItem() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='newJob']")).click();

        driver.findElement(By.id("name")).sendKeys("somePipeline");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        Assert.assertEquals(driver.findElement(By.className("jenkins-toggle-switch__label__checked-title")).getText(), "Enabled");

    }

    @Ignore
    @Test
    public void isShownDialogOnDeletePipelineItem() {
        WebDriver driver = getDriver();
        final String itemName = "somePipeline";

        driver.findElement(By.xpath("//a[@href='newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(itemName);
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.className("job-index-headline")).click();


        Assert.assertTrue(driver.findElement(By.xpath("//body[@id='jenkins']/dialog[@class='jenkins-dialog']")).isDisplayed());
    }

}