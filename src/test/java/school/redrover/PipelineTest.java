package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineTest extends BaseTest {
    private static final String PROJECT_NAME = "New Pipeline";

    @Test
    public void testPipeline() {
            getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
            getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
            getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
            getDriver().findElement(By.id("ok-button")).click();
            getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

            Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'jenkins-app-bar__content jenkins-build-caption']/h1")).getText(), PROJECT_NAME);
    }
}
