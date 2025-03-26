package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineTest extends BaseTest {

    @Test
    public void testPipeline() {

        getDriver().findElement(By.xpath("//a[@href ='newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys("NewPipeline");
        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector(".jenkins-submit-button")).click();
        getDriver().findElement(By.xpath("//img[@id='jenkins-name-icon']")).click();
        String actualJobName = getDriver().findElement(By.xpath("//table[@id='projectstatus']/tbody/tr/td/a/span")).getText();

        Assert.assertEquals(actualJobName, "NewPipeline");;
    }
}
