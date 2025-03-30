package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GroupCodeCraftTest extends BaseTest {

    @Test
    public void testCreatePipeline() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tasks']/div[1]/span/a"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input"))).sendKeys("NewPipeline");

        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok-button"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("job-index-headline"))).getText();

        Assert.assertEquals(title, "NewPipeline", "Pipeline title is not correct");
    }

    @Test
    public void testAboutJenkins(){
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/span/a/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[4]/a/dl/dt")).click();
        String appName = driver.findElement(By.className("app-about-heading")).getText();
        String appVersion = driver.findElement(By.className("app-about-version")).getText();

        Assert.assertEquals(appName, "Jenkins");
        Assert.assertEquals(appVersion, "Version 2.492.2");
    }

    @Test
    public void testNameBuildHistory() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a.task-link[href='/view/all/builds']")).click();
        String buildText = driver.findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(buildText, "Build History of Jenkins");
    }

    @Test
    public void testShowProperties() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[3]/div/button[1]")).click();

        WebElement vendor = driver.findElement(By.xpath("//*[.='java.specification.vendor']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", vendor);
        String javaVendor = driver.findElement(By.xpath("//*[.='java.specification.vendor']/following-sibling::td/div[2]")).getText();

        Assert.assertEquals(javaVendor, "Oracle Corporation");
    }
}
