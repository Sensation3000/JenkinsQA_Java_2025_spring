package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FreestyleProject4Test extends BaseTest {
    private static final String jobName = "Test item";

    @Test
    public void testToolTipEnableDisable() {
        WebDriver driver = getDriver();
        Actions actions = new Actions(driver);
        TestUtils.createFreestyleProject(driver, jobName);

        actions.moveToElement(driver.findElement(By.className("jenkins-toggle-switch__label"))).perform();
        WebElement tooltip = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.className("tippy-content")));

        assertEquals(tooltip.getText(), "Enable or disable the current project");
    }

    @Test
    public void testCheckWarningWhenDisabled() {
        WebDriver driver = getDriver();
        TestUtils.createFreestyleProject(driver, jobName);
        driver.findElement(By.id("toggle-switch-enable-disable-project")).click();
        driver.findElement(By.name("Submit")).click();

        String warning = driver.findElement(By.id("enable-project")).getText();

        assertTrue(warning.contains("This project is currently disabled"), "Project is not disabled");
    }

    @Ignore //WebDriver unknown error: unhandled inspector error: {"code":-32000,"message":"Node with given id does not belong to the document"}
    @Test
    public void testTriggerBuildAfterOtherProjects(){
        final String projectNameFP1 = "FreeStyleProject1";
        final String projectNameFP2 = "FreeStyleProject2";

        TestUtils.createFreestyleProject(getDriver(), projectNameFP1);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#breadcrumbBar a[href='/']"))).click();
        TestUtils.createFreestyleProject(getDriver(), projectNameFP2);
        TestUtils.scrollAndClickWithJS(getDriver(), getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input[name = 'jenkins-triggers-ReverseBuildTrigger']"))));
        getDriver().findElement(By.name("_.upstreamProjects")).sendKeys(projectNameFP1);
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#breadcrumbBar a[href='/']"))).click();

        TestUtils.moveAndClickWithJS(getDriver(),
                getWait5().until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//td/a/span[text() = '%s']/../button".formatted(projectNameFP1)))));
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='jenkins-dropdown__item__icon']/parent::*[contains(., '%s')]".formatted("Build Now")))).click();
        getDriver().findElement(By.linkText(projectNameFP2)).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins-build-history")));
        getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-href='/job/" + projectNameFP2 + "/1/']"))).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'console')]")).click();

        assertTrue(getDriver().findElement((By.id("out"))).getText().contains("Finished: SUCCESS"));
    }

    @Test
    public void testCheckDescriptionIsSaved() {
        final String description = "Job description";

        TestUtils.createFreestyleProject(getDriver(), jobName);
        getDriver().findElement(By.name("description")).sendKeys(description);
        getDriver().findElement(By.name("Submit")).click();

        assertEquals(getDriver().findElement(By.id("description")).getText(), description);
    }
}
