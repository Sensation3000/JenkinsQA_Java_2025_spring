package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineConfigurePageTest extends BaseTest {

    @Test
    public void shouldDisableSwitchButton() {
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

    @Test
    public void shouldEnableSwitchButton() {
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

    @Test
    public void shouldShowTooltipMessageOnHover() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//span[text()='New Item']/..")).click();
        driver.findElement(By.id("name")).sendKeys("Test item");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();

        new Actions(driver)
                .moveToElement(driver.findElement(By.className("jenkins-toggle-switch__label")))
                .perform();

        WebElement tooltip = getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("tippy-content")));

        Assert.assertTrue(tooltip.isDisplayed());
        Assert.assertEquals(tooltip.getText(), "Enable or disable the current project");
    }

    @Test
    public void shouldShowWarningMessageWhenItemDisabled() {
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

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldNotShowWarningMessageWhenItemEnabled() {
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

        driver.findElement(By.id("enable-project"));
        Assert.assertTrue(driver.findElements(By.id("enable-project")).isEmpty());
    }
}
