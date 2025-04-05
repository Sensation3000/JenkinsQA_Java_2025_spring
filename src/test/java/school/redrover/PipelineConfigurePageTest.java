package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineConfigurePageTest extends BaseTest {

    @Test
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
}
