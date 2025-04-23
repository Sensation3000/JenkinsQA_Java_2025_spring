package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItem7Test extends BaseTest {

    @Test
    public void testCreatePipeline() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement((By.name("name"))).sendKeys("Pipeline1");
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement((By.id("ok-button"))).click();
        getDriver().findElement((By.name("description"))).sendKeys("Beta");
        getDriver().findElement(
                By.xpath("//label[text()='Do not allow concurrent builds']")).click();
        getDriver().findElement(By.xpath("//label[text()='Abort previous builds']")).click();
        getDriver().findElement(By.name("Submit")).click();
        WebElement result = getDriver().findElement(
                By.cssSelector("#main-panel > div.jenkins-app-bar > div.jenkins-app-bar__content.jenkins-build-caption"));
        Assert.assertEquals(result.getText(), "Pipeline1");
    }
}

