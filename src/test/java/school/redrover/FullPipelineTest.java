package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;


public class FullPipelineTest extends BaseTest {
    @Test
    public void testCreateNewPipeline() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline");
        driver.findElement(By.xpath("//li[contains(@class, 'org_jenkinsci_plugins_workflow_job_WorkflowJob')]")).click();
        driver.findElement(By.id("ok-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        WebElement pipelineTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class, 'job-index-headline')]")));
        Assert.assertEquals(pipelineTitle.getText(), "Test Pipeline");
    }

    @Test
    public void testCreatedPipelineIsDisplayedOnTheDashboard() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline");
        driver.findElement(By.xpath("//li[contains(@class, 'org_jenkinsci_plugins_workflow_job_WorkflowJob')]")).click();
        driver.findElement(By.id("ok-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        ((JavascriptExecutor)driver).executeScript("window.location.href='/';");

        WebElement pipelineTitle = driver.findElement(By.xpath("//a[contains(@class, 'jenkins-table__link')]//span"));
        Assert.assertEquals(pipelineTitle.getText(), "Test Pipeline");
    }

    @Test
    public void testDeletePipeline() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline");
        driver.findElement(By.xpath("//li[contains(@class, 'org_jenkinsci_plugins_workflow_job_WorkflowJob')]")).click();
        driver.findElement(By.id("ok-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-title=\"Delete Pipeline\"]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();

        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".empty-state-block h1")));
        Assert.assertEquals("Welcome to Jenkins!", titleElement.getText());
    }
}
