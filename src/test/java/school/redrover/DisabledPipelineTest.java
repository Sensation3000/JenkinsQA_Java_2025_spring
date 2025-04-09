package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class DisabledPipelineTest extends BaseTest {

    @Test
    public void testShownWarningMassage () {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='newJob']")).click();
        driver.findElement(By.name("name")).sendKeys("Pipeline_1");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.id("toggle-switch-enable-disable-project")).click();
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(driver.findElement(By.id("enable-project")).getText(),
                "This project is currently disabled\nEnable");
    }
}
