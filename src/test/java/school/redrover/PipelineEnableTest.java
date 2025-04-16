package school.redrover;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import school.redrover.common.BaseTest;

public class PipelineEnableTest extends BaseTest {

    @Test
    public void checkDefaultStateTest() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")))
            .click();

        driver.findElement(By.id("name")).sendKeys("Pipeline project test");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();

        WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toggle-switch-enable-disable-project")));
        String status = toggle.getText();

        Assert.assertEquals(status, "Enabled");
    }

    @Test
    public void changeStateNewPipelineTest() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")))
            .click();

        driver.findElement(By.id("name")).sendKeys("Pipeline project test");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        driver.findElement(By.id("ok-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toggle-switch-enable-disable-project"))).click();

        String status = driver.findElement(By.className("jenkins-toggle-switch__label__unchecked-title")).getText();

        Assert.assertEquals(status, "Disabled");

    }

}
