package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.List;

import static org.testng.Assert.*;

public class FreestyleProject4Test extends BaseTest {
    private static final String JOB_NAME = "Test item";

    @Test
    public void testToolTipEnableDisable() {
        WebDriver driver = getDriver();
        Actions actions = new Actions(driver);
        TestUtils.createFreestyleProject(driver, JOB_NAME);

        actions.moveToElement(driver.findElement(By.className("jenkins-toggle-switch__label"))).perform();
        WebElement tooltip = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.className("tippy-content")));

        assertEquals(tooltip.getText(), "Enable or disable the current project");
    }

    @Test
    public void testCheckWarningWhenDisabled() {
        WebDriver driver = getDriver();
        TestUtils.createFreestyleProject(driver, JOB_NAME);
        driver.findElement(By.id("toggle-switch-enable-disable-project")).click();
        driver.findElement(By.name("Submit")).click();

        String warning = driver.findElement(By.id("enable-project")).getText();

        assertTrue(warning.contains("This project is currently disabled"), "Project is not disabled");
    }

    @Test
    public void testTriggerBuildAfterOtherProjects(){
        final String jobName2 = "Test item2";

        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h1"), "Configure"));
        getDriver().findElement(By.cssSelector("#breadcrumbBar a[href='/']")).click();
        TestUtils.createFreestyleProject(getDriver(), jobName2);
        TestUtils.scrollAndClickWithJS(getDriver(), getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input[name = 'jenkins-triggers-ReverseBuildTrigger']"))));
        getDriver().findElement(By.name("_.upstreamProjects")).sendKeys(JOB_NAME);
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h1"), jobName2));
        getDriver().findElement(By.cssSelector("#breadcrumbBar a[href='/']")).click();
        TestUtils.moveAndClickWithJS(getDriver(),
                getWait5().until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//td/a/span[text() = '%s']/../button".formatted(JOB_NAME)))));
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='jenkins-dropdown__item__icon']/parent::*[contains(., '%s')]".formatted("Build Now")))).click();
        getDriver().findElement(By.linkText(jobName2)).click();
        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("jenkins-build-history")));
        getWait10().until(ExpectedConditions.textToBe(By.xpath("//span[@class='app-builds-container__heading']"), "Today"));

        final String buildStatusText = getDriver().findElement(By.id("jenkins-build-history")).getText();
        final List<WebElement> builds = getDriver().findElements(By.cssSelector("div[page-entry-id]"));

        Assert.assertEquals(builds.size(), 1);
        Assert.assertTrue(buildStatusText.contains("Today"));
        Assert.assertTrue(buildStatusText.contains("#1"));
    }

    @Test
    public void testCheckDescriptionIsSaved() {
        final String description = "Job description";

        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);
        getDriver().findElement(By.name("description")).sendKeys(description);
        getDriver().findElement(By.name("Submit")).click();

        assertEquals(getDriver().findElement(By.id("description")).getText(), description);
    }

    @Test
    public void testAccessBuildSteps() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys("projectName");
        getDriver().findElement(
                By.xpath("//span[contains(text(),'Freestyle project')]/ancestor::li")).click();
        getDriver().findElement(By.id("ok-button")).click();

        assertTrue(getDriver().findElement(
                By.xpath("//*[@id='main-panel']/form/div[1]/section[5]/div[3]/div[2]/button"))
                .isEnabled());
    }

    @Test(invocationCount = 50)
    public void testAddBuildSteps() {
        boolean present = true;

        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);

        for (int i = 1; present ; i++) {
            new Actions(getDriver()).scrollToElement(getDriver().findElement(
                    By.xpath("//*[@id='main-panel']/form/div[1]/section[6]/div[3]/div[2]/button")))
                    .perform();

            getDriver().findElement(
                    By.xpath(
                    "//*[@id='main-panel']/form/div[1]/section[5]/div[3]/div[" + (i + 1) + "]/button"))
                    .click();

            try {
                getDriver().findElement(
                        By.xpath("//*[@id='tippy-5']/div/div/div/div[2]/button[" + (i + 1) + "]"));
            } catch (NoSuchElementException e) {
                present = false;
            }
            getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id='tippy-5']/div/div/div/div[2]/button[" + i + "]"))).click();

            getDriver().findElement(By.tagName("textarea")).sendKeys(i + " OK ");
        }

        assertEquals(getDriver().findElement(
                By.xpath("//*[@id='main-panel']/form/div[1]/section[5]/div[3]/div[7]/div/div[1]"))
                .getText(),
                "Set build status to \"pending\" on GitHub commit");
    }
}