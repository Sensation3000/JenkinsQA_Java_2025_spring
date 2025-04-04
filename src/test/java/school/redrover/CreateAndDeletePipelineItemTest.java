package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateAndDeletePipelineItemTest extends BaseTest {

    @Test
    public void testCreateAndDeletePipelineItem() {
        WebDriver driver = getDriver();

        //Click on New Item
        WebElement addItem = driver.findElement(By.cssSelector("#tasks > div:nth-child(1) > span > a"));
        addItem.click();

        //input text, choose radio and click ОК
        driver.findElement(By.id("name")).sendKeys("somePipeline");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        //delete Item
        driver.findElement(new By.ByXPath("//*[@id=\"tasks\"]/div[5]/span/a")).click();
        driver.findElement(new By.ByXPath("//*[@id=\"jenkins\"]/dialog[2]/div[3]/button[1]")).click();
    }

    @Test
    public void getTextOnCreateEmptyNamePipelineItem() {
        WebDriver driver = getDriver();

        WebElement addItem = driver.findElement(By.cssSelector("#tasks > div:nth-child(1) > span > a"));
        addItem.click();

        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        Assert.assertEquals(driver.findElement(By.id("itemname-required")).getText(), "» This field cannot be empty, please enter a valid name");

    }

    @Test
    public void isDisabledButtonOKOnCreateNamePipelineItem() {
        WebDriver driver = getDriver();

        WebElement addItem = driver.findElement(By.cssSelector("#tasks > div:nth-child(1) > span > a"));
        addItem.click();

        driver.findElement(By.id("name")).sendKeys("somePipeline");
        Assert.assertTrue(driver.findElement(By.id("ok-button")).isDisplayed());

    }

    @Test
    public void isEnabledProjectOnCreatePipelineItem() {
        WebDriver driver = getDriver();

        WebElement addItem = driver.findElement(By.cssSelector("#tasks > div:nth-child(1) > span > a"));
        addItem.click();

        driver.findElement(By.id("name")).sendKeys("somePipeline");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        Assert.assertEquals(driver.findElement(By.className("jenkins-toggle-switch__label__checked-title")).getText(), "Enabled");

    }

    @Test
    public void isShownDialogOnDeletePipelineItem() {
        WebDriver driver = getDriver();

        WebElement addItem = driver.findElement(By.cssSelector("#tasks > div:nth-child(1) > span > a"));
        addItem.click();

        driver.findElement(By.id("name")).sendKeys("somePipeline");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        driver.findElement(new By.ByXPath("//*[@id=\"tasks\"]/div[5]/span/a")).click();
        Assert.assertEquals(driver.findElement(By.className("jenkins-dialog__title")).getText(), "Delete Pipeline");
    }

}

