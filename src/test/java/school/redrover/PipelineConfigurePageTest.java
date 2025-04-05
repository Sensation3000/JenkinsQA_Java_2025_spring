package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineConfigurePageTest extends BaseTest {

    @Test(testName = "TC_03.001.01 > Disable item on the Configure page")
    public void disableItem() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//span[text()='New Item']/..")).click();
        driver.findElement(By.id("name")).sendKeys("Test item");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.className("jenkins-toggle-switch__label")).click();
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.xpath("//span[text()='Configure']/..")).click();

        Assert.assertTrue(driver.findElement(By.className("jenkins-toggle-switch__label__unchecked-title")).isDisplayed());
    }

    @Test(testName = "TC_03.001.02 > Enable item on the Configure page")
    public void enableTest() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//span[text()='New Item']/..")).click();
        driver.findElement(By.id("name")).sendKeys("Test item");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.className("jenkins-toggle-switch__label")).click();
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.xpath("//span[text()='Configure']/..")).click();
        driver.findElement(By.className("jenkins-toggle-switch__label")).click();
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.xpath("//span[text()='Configure']/..")).click();

        Assert.assertTrue(driver.findElement(By.className("jenkins-toggle-switch__label__checked-title")).isDisplayed());
    }

    @Test(testName = "TC_03.001.03 > The tooltip appears on mouse hover")
    public void hoverTooltip() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//span[text()='New Item']/..")).click();
        driver.findElement(By.id("name")).sendKeys("Test item");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        new Actions(driver)
                .moveToElement(driver.findElement(By.className("jenkins-toggle-switch__label")))
                .perform();

        Assert.assertTrue(driver.findElement(By.className("tippy-content")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.className("tippy-content")).getText(), "Enable or disable the current project");
    }

    @Test(testName = "TC_03.001.04 > The warning message is shown on the Project page")
    public void checkWarningMessageAppears() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//span[text()='New Item']/..")).click();
        driver.findElement(By.id("name")).sendKeys("Test item");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.className("jenkins-toggle-switch__label")).click();
        driver.findElement(By.name("Submit")).click();

        Assert.assertTrue(driver.findElement(By.id("enable-project")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("enable-project")).getText().contains("This project is currently disabled"));
    }
}
