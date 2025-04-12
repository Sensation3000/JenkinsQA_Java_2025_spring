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
import java.util.List;

public class PipelineConfigurePage2Test extends BaseTest {

    @Test(testName = "TC_03.001.01.2 > Verify Enable/Disable Switch Visibility on Configure Page")
    public void testEnableDisableSwitchVisibility() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline item");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();

        Assert.assertTrue(driver.findElement(By.className("jenkins-toggle-switch__label")).isDisplayed());
    }

    @Test(testName = "TC_03.001.02.2 > Verify Disabling a Project")
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

    @Test(testName = "TC_03.001.03.2 > Verify Enabling a Project")
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

    @Test(testName = "TC_03.001.04.2 > Verify 'Build Now' button state when project is disabled")
    public void testVerifyPipelineBuildNowButtonDisabled() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline item");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();

        WebElement switchButton = driver.findElement(By.cssSelector("label.jenkins-toggle-switch__label"));
        moveAndClickWithSelenium(driver, switchButton);
        driver.findElement(By.name("Submit")).click();

        WebElement dashboardLink = getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[@href='/' and @class='model-link']")));
        moveAndClickWithSelenium(driver, dashboardLink);

        WebElement pipelineItem = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[contains(text(), 'Test Pipeline item')]")));
        moveAndWaitWithSelenium(driver, pipelineItem);

        WebElement dropdownMenu = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//tr[@id='job_Test Pipeline item']//button[contains(@class, 'jenkins-menu-dropdown-chevron')]")));
        moveAndClickWithSelenium(driver,dropdownMenu);

        List<WebElement> buildNowButtons = driver.findElements(By
                .xpath("//button[contains(., 'Build Now')]"));

        Assert.assertTrue(buildNowButtons.isEmpty());
    }

    @Test (testName = "TC_03.001.05.2 > Verify 'Build Now' Button State When Project is Enabled")
    public void testVerifyBuildNowButtonWhenEnabled() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys("Test Pipeline item");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        WebElement dashboardLink = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//a[@href='/' and @class='model-link']")));
        moveAndClickWithSelenium(driver, dashboardLink);

        WebElement pipelineItem = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[contains(text(), 'Test Pipeline item')]")));
        moveAndWaitWithSelenium(driver, pipelineItem);

        WebElement dropdownMenu = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//tr[@id='job_Test Pipeline item']//button[contains(@class, 'jenkins-menu-dropdown-chevron')]")));
        moveAndClickWithJS(driver,dropdownMenu);

        WebElement buildNowOption = getWait10().until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//button[normalize-space()='Build Now']")));

        Assert.assertTrue(buildNowOption.isDisplayed());
    }

    public static void moveAndClickWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        js.executeScript("arguments[0].click();", element);
    }

    public static void moveAndClickWithSelenium(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
          action.moveToElement(element)
                .click()
                .perform();
    }

    public static void moveAndWaitWithSelenium(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
          action.moveToElement(element)
                .perform();
    }
}
