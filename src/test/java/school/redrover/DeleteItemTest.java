package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class DeleteItemTest extends BaseTest {

    @Test
    public void testDeletePipeline() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By newJobLink = By.xpath("//a[@href='/view/all/newJob']");
        By nameField = By.id("name");
        By pipelineType = By.xpath("//li[contains(@class, 'org_jenkinsci_plugins_workflow_job_WorkflowJob')]");
        By okButton = By.id("ok-button");
        By submitButton = By.name("Submit");
        By deletePipelineLink = By.xpath("//a[@data-title=\"Delete Pipeline\"]");
        By confirmDeleteButton = By.xpath("//button[@data-id='ok']");
        By welcomeTitle = By.cssSelector(".empty-state-block h1");

        driver.findElement(newJobLink).click();
        driver.findElement(nameField).sendKeys("Test Name");
        driver.findElement(pipelineType).click();
        driver.findElement(okButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        driver.findElement(submitButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(deletePipelineLink));
        driver.findElement(deletePipelineLink).click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        driver.findElement(confirmDeleteButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeTitle));
        WebElement titleElement = driver.findElement(welcomeTitle);
        Assert.assertEquals("Welcome to Jenkins!", titleElement.getText());

    }
}
