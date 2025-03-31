package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;



    public class JenkinsTest extends BaseTest {

        @Test
        public void TestCreateNewPipeline() {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            WebElement newJobLink = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
            js.executeScript("arguments[0].click();", newJobLink);
            WebElement nameField = getDriver().findElement(By.xpath("//*[@id='name']"));
            js.executeScript("arguments[0].value = 'Test1';", nameField);
            WebElement workflowJob = getDriver().findElement(By.xpath("//*[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']"));
            js.executeScript("arguments[0].click();", workflowJob);
            WebElement okButton = getDriver().findElement(By.id("ok-button"));
            js.executeScript("arguments[0].click();", okButton);
        }
    }

