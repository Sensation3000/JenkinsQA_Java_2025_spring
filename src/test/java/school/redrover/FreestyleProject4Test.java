package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.List;
import static org.testng.Assert.*;


public class FreestyleProject4Test extends BaseTest {
    private static final String JOB_NAME = "Test item";
    private static final String JOB_NAME_2 = "Second test item";

    @Test
    public void createNewFreestyleProject() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(JOB_NAME);
        getDriver().findElement(By.xpath("//span[contains(text(),'Freestyle project')]/ancestor::li")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(By.id("createItem")));
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Configure"));
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), JOB_NAME));

        assertEquals(getDriver().findElement(By.tagName("h1")).getText(), JOB_NAME);
    }

    @Test(dependsOnMethods = "createNewFreestyleProject")
    public void testAddAndSaveDescription() {
        final String description = "Job description";

        getDriver().findElement(By.linkText(JOB_NAME)).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), JOB_NAME));
        getDriver().findElement(By.xpath(
                        "//a[@href='/job/Test%20item/configure']")).click();
        getDriver().findElement(By.name("description")).sendKeys(description);
        getDriver().findElement(By.name("Submit")).click();

        assertTrue(getDriver().findElement(By.id("description")).getText().contains(description));
    }

    @Test(dependsOnMethods = "testAddAndSaveDescription")
    public void testToolTipEnableDisable() {
        WebDriver driver = getDriver();
        Actions actions = new Actions(driver);

        getDriver().findElement(By.linkText(JOB_NAME)).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), JOB_NAME));
        getDriver().findElement(By.xpath(
                "//a[@href='/job/Test%20item/configure']")).click();

        actions.moveToElement(driver.findElement(By.className("jenkins-toggle-switch__label"))).perform();
        WebElement tooltip = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.className("tippy-content")));

        assertEquals(tooltip.getText(), "Enable or disable the current project");
    }

    @Ignore
    @Test(dependsOnMethods = "createNewFreestyleProject")
    public void testCheckWarningWhenDisabled() {
        getDriver().findElement(By.linkText(JOB_NAME)).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), JOB_NAME));
        getDriver().findElement(By.xpath(
                "//a[@href='/job/Test%20item/configure']")).click();
        getDriver().findElement(By.id("toggle-switch-enable-disable-project")).click();
        getDriver().findElement(By.name("Submit")).click();

        String warning = getDriver().findElement(By.id("enable-project")).getText();

        assertTrue(warning.contains("This project is currently disabled"), "Project is not disabled");
    }

    @Test
    public void testTriggerBuildAfterOtherProjects() {
        final String pageCreateItem = "createItem";
        final String h1Tag = "h1";

        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.id(pageCreateItem)));
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName(h1Tag), "Configure"));
        TestUtils.gotoHomePage(this);
        TestUtils.createFreestyleProject(getDriver(), JOB_NAME_2);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.id(pageCreateItem)));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='triggers']/parent::section")));
        TestUtils.scrollAndClickWithJS(getDriver(), getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input[name = 'jenkins-triggers-ReverseBuildTrigger']"))));
        getDriver().findElement(By.name("_.upstreamProjects")).sendKeys(JOB_NAME);
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(h1Tag), JOB_NAME_2));
        TestUtils.gotoHomePage(this);
        TestUtils.moveAndClickWithJS(getDriver(),
                getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a/span[text() = '%s']/../button".formatted(JOB_NAME)))));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='jenkins-dropdown__item__icon']/parent::*[contains(., '%s')]".formatted("Build Now")))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#buildQueue table td.pane"), JOB_NAME_2));
        getDriver().findElement(By.linkText(JOB_NAME_2)).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(h1Tag), JOB_NAME_2));
        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(" #jenkins-build-history a[title='Success']")));

        final String buildStatusText = getDriver().findElement(By.id("jenkins-build-history")).getText();
        final List<WebElement> builds = getDriver().findElements(By.cssSelector("div[page-entry-id]"));

        Assert.assertEquals(builds.size(), 1);
        Assert.assertTrue(buildStatusText.contains("Today"));
        Assert.assertTrue(buildStatusText.contains("#1"));
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

    @Test
    public void testAddBuildSteps() {
        boolean present = true;

        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);

        for (int i = 1; present; i++) {
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

            getDriver().findElement(By.tagName("textarea")).click();
        }

        new Actions(getDriver()).scrollToElement(getDriver().findElement(
                        By.xpath("//*[@id='main-panel']/form/div[1]/section[6]/div[3]/div[2]/button")))
                        .perform();

        assertEquals(getDriver().findElement(
                        By.xpath("//*[@id='main-panel']/form/div[1]/section[5]/div[3]/div[7]/div/div[1]"))
                        .getText(),
                "Set build status to \"pending\" on GitHub commit");
    }

    @Test
    public void testCreateFreestyleProjectWithNoneSCM() {
        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//label[text()='None']"))).perform();
        getDriver().findElement(By.name("Submit")).click();
        WebElement result = getDriver().findElement(
                By.cssSelector("#main-panel > div.jenkins-app-bar > div.jenkins-app-bar__content.jenkins-build-caption > h1"));
        Assert.assertEquals(result.getText(), JOB_NAME);
    }

    @Test
    public void testCheckDiscardOldBuilds() {
        int expectedClicks = 8;
        int logLimit = 5;

        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);
        getDriver().findElement(By.xpath("//label[contains(text(),'Discard old builds')]")).click();
        getDriver().findElement(By.name("_.numToKeepStr")).sendKeys(String.valueOf(logLimit));
        getDriver().findElement(By.name("Submit")).click();

        WebElement buildButton = getDriver().findElement(
                By.xpath("//a[@href='/job/Test%20item/build?delay=0sec']"));

        for (int i = 0; i < expectedClicks; i++) {
            buildButton.click();
            getWait10().until(ExpectedConditions.numberOfElementsToBe(
                    By.className("app-builds-container__item"),
                    Math.min(i + 1, logLimit)));
        }

        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.className("app-builds-container")));
        List<WebElement> entries = getDriver().findElements(By.className("app-builds-container__item"));

        assertEquals(entries.size(), logLimit);
    }

    @Test(dependsOnMethods = "testToolTipEnableDisable")
    public void deleteFreestyleProject() {
        getDriver().findElement(By.linkText(JOB_NAME)).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), JOB_NAME));
        getDriver().findElement(By.xpath("//a[contains(@data-url, 'doDelete')]")).click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.className("jenkins-dialog")));
        getDriver().findElement(By.cssSelector("button[data-id='ok']")).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Welcome to Jenkins!"));

        assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Welcome to Jenkins!");
    }
}
