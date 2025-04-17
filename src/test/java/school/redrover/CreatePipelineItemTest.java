package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreatePipelineItemTest extends BaseTest {

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

}