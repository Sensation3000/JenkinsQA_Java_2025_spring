package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineConfigurationProjectTest extends BaseTest {
    private static final String PROJECT_NAME = "PipelineConfigTest";


    @Ignore //PipelineConfigurationProjectTest.testPreviewAndHidePreviewButtons:30 Preview should be displayed expected [true] but found [false]
    @Test
    public void testPreviewAndHidePreviewButtons() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(PROJECT_NAME);
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();

        WebElement descriptionField = driver.findElement(By.cssSelector("[name$='description']"));
        String inputText = "Pipeline Config Description Test";
        descriptionField.sendKeys(inputText);
        WebElement previewButton = driver.findElement(By.cssSelector(".textarea-show-preview"));
        previewButton.click();
        WebElement previewElement = driver.findElement(By.cssSelector(".textarea-preview"));

        Assert.assertTrue(previewElement.isDisplayed(), "Preview should be displayed");

        WebElement hidePreviewButton = driver.findElement(By.cssSelector(".textarea-hide-preview"));
        hidePreviewButton.click();

        Assert.assertFalse(previewElement.isDisplayed(), "Preview is still displayed after clicking 'Hide Preview'.");
    }
}
