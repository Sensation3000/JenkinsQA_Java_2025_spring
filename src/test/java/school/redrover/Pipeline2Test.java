package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class Pipeline2Test extends BaseTest {

    @Test
    public void testCreateNewPipeline() {
        final String projectName = "PipelineProjectNameTest";

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        driver.findElement(By.className("jenkins-input")).sendKeys(projectName);
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(driver.findElement(By.className("job-index-headline")).getText(), projectName);
    }

    @Test
    public void testCopyFromError() {
        final String freestyleProjectA = "FirstProject";
        final String freestyleProjectB = "SecondProject";
        final String copyFrom = "No such item";

        WebDriver driver = getDriver();
        TestUtils.newItemCreate(this, freestyleProjectA, 2);

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        driver.findElement(By.className("jenkins-input")).sendKeys(freestyleProjectB);
        driver.findElement(By.id("from")).sendKeys(copyFrom);
        driver.findElement(By.id("ok-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main-panel\"]/h1")).getText(),"Error");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main-panel\"]/p")).getText(), "No such job: " + copyFrom);
    }
}
