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
import school.redrover.common.TestUtils;

import java.util.List;

public class PipelineConfigurePage2Test extends BaseTest {

    @Test
    public void testEnableDisableSwitchVisibility() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline item");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();

        Assert.assertTrue(driver.findElement(By.className("jenkins-toggle-switch__label")).isDisplayed());
    }

    @Test
    public void testDisableItem() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline item");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.className("jenkins-toggle-switch__label")).click();
        driver.findElement(By.name("Submit")).click();

        Assert.assertTrue(driver.findElement(By.id("enable-project")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("enable-project")).getText().contains("This project is currently disabled"));
    }

    @Test
    public void testEnableItem() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline item");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.className("jenkins-toggle-switch__label")).click();
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.xpath("//span[text()='Configure']/..")).click();
        driver.findElement(By.className("jenkins-toggle-switch__label")).click();

        Assert.assertTrue(driver.findElement(By.className("jenkins-toggle-switch__label")).isEnabled());
    }

    @Test
    public void testVerifyPipelineBuildNowButtonDisabled() {
        WebDriver driver = getDriver();
        final String jobName = "Test Pipeline item";

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline item");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();

        WebElement switchButton = getWait5()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='toggle-switch-enable-disable-project']")));
                moveAndClickWithSelenium(driver, switchButton);

                driver.findElement(By.name("Submit")).click();

        ((JavascriptExecutor)driver).executeScript("window.location.href='/';"); //back to homepage

        By jobButton = By.xpath("//td/a/span[text() = '%s']/../button".formatted(jobName));
        WebElement button = getWait5().until(ExpectedConditions.elementToBeClickable(jobButton));
        TestUtils.moveAndClickWithJS(driver, button);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//tr[@id='job_Test Pipeline item']//button[contains(@class, 'jenkins-menu-dropdown-chevron')]")));

        List<WebElement> buildNowButtons = driver.findElements(By
                .xpath("//button[contains(., 'Build Now')]"));

        Assert.assertTrue(buildNowButtons.isEmpty());
    }

    @Test
    public void testVerifyBuildNowButtonWhenEnabled() {
        WebDriver driver = getDriver();
        final String jobName = "Test Pipeline item";

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(jobName);
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        ((JavascriptExecutor)driver).executeScript("window.location.href='/';"); //back to homepage

        By jobButton = By.xpath("//td/a/span[text() = '%s']/../button".formatted(jobName));
        WebElement button = getWait5().until(ExpectedConditions.elementToBeClickable(jobButton));
        TestUtils.moveAndClickWithJS(driver, button);

        WebElement buildNowOption = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//button[normalize-space()='Build Now']")));
        Assert.assertTrue(buildNowOption.isDisplayed());
    }

    public static void moveAndClickWithSelenium(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element)
                .click()
                .perform();
    }
}
