package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;


    public class JenkinsTest extends BaseTest {
        @Test
        public void TestCreateNewPipeline()  {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));
            getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
            getDriver().findElement(
                    By.xpath("//*[@id='name']")).sendKeys("Test1");
            getDriver().findElement(
                    By.xpath("//*[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
            getDriver().findElement(By.id("ok-button")).click();
            getDriver().findElement(
                    By.xpath("//*[@name='description']")).sendKeys("First Test");
            getDriver().findElement(By.cssSelector("a[href='/']")).click();
            WebElement addDescription = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='description-link']")));
            assertNotNull(addDescription);
            assertTrue(addDescription.isDisplayed());
        }
    }

