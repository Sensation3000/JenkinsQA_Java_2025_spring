package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.time.Duration;

public class PipelineNameTest extends BaseTest {
    @Test
    public void testCheckNameOfNewPipeline() {
        WebDriver driver = getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']")))
                .click();
        driver.findElement(By.id("name")).sendKeys("newPipe");
        driver.findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("description")).sendKeys("autoDecription");
        new Actions(driver)
                .moveToElement(driver.findElement(By.id("cb11")))
                .click()
                .perform();
        driver.findElement(By.name("Submit")).click();
        Assert.assertEquals( driver.findElement(By.xpath("//h1[contains(text(),'newPipe')]"))
                        .getText(),
                "newPipe");
    }
}
