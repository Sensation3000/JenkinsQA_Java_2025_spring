package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineTest extends BaseTest {
    private static final String PROJECT_NAME = "New Pipeline";
    private static final String PIPELINE_DESCRIPTION = "Pipeline Project Name" ;

    @Test
    public void testPipeline() {
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'jenkins-app-bar__content jenkins-build-caption']/h1")).getText(), PROJECT_NAME);
    }

    @Test
    public void testDescriptionPipeline() {
        getDriver().findElement(By.xpath("//a[@href='editDescription']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(PIPELINE_DESCRIPTION);
        getDriver().findElement(By.xpath("//div[@id = 'description']//button[@name='Submit']")).click();
        String savedDescription = getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]")).getText();

        Assert.assertEquals(savedDescription, PIPELINE_DESCRIPTION);
    }
}
