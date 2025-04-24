package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

import java.util.List;

public class FreestyleProjectConfigurationBuildTriggersTest extends BaseTest {
    private final String PROJECT_NAME = "New project";

    @Test
    public void testCheckBuildTriggersSection() {

        WebDriver driver = getDriver();

        TestUtils.createFreestyleProject(getDriver(), PROJECT_NAME);

        TestUtils.scrollToItemWithJS(driver,
                driver.findElement(By.id("source-code-management")));

        //Title
        Assert.assertEquals(driver
                .findElement(By.id("triggers")).getText(),
                "Triggers");

        //Subtitle
        Assert.assertEquals(driver
                .findElement(By.xpath("//div[@class='jenkins-section__description' and contains(text(), 'Set up automated')]")).getText(),
                "Set up automated actions that start your build based on specific events, like code changes or scheduled times.");

        //Trigger builds remotely (e.g., from scripts)
        Assert.assertTrue(driver.findElement(By.id("cb13")).isDisplayed(), "Checkbox for remote trigger is not displayed");
        Assert.assertTrue(driver.findElement(By.id("cb13")).isEnabled(), "Checkbox for remote trigger is not enabled");

        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(text(), 'Trigger builds remotely')]")).getText(), "Trigger builds remotely (e.g., from scripts)", "Text next to checkbox is incorrect");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='jenkins-help-button']")).isDisplayed(), "Help icon is not displayed");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[3]/div[3]/div[1]/div/a")).getDomAttribute("title"), "Help for feature: Trigger builds remotely (e.g., from scripts)", "Help icon title is incorrect");


        //Build after other projects are built
        Assert.assertTrue(driver.findElement(By.id("cb14")).isDisplayed(), "Checkbox for build after other projects are built is not displayed");
        Assert.assertTrue(driver.findElement(By.id("cb14")).isEnabled(), "Checkbox for build after other projects are built is not enabled");

        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(text(), 'Build after other projects are built')]")).getText(), "Build after other projects are built", "Text next to checkbox is incorrect");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='jenkins-help-button']")).isDisplayed(), "Help icon is not displayed");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[3]/div[4]/div[1]/div/a")).getDomAttribute("title"), "Help for feature: Build after other projects are built", "Help icon title is incorrect");


        //Build periodically
        Assert.assertTrue(driver.findElement(By.id("cb15")).isDisplayed(), "Checkbox for build periodically is not displayed");
        Assert.assertTrue(driver.findElement(By.id("cb15")).isEnabled(), "Checkbox for build periodically is not enabled");

        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(text(), 'Build periodically')]")).getText(), "Build periodically", "Text next to checkbox is incorrect");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='jenkins-help-button']")).isDisplayed(), "Help icon is not displayed");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[3]/div[5]/div[1]/div/a")).getDomAttribute("title"), "Help for feature: Build periodically", "Help icon title is incorrect");

        //GitHub hook trigger for GITScm polling
        Assert.assertTrue(driver.findElement(By.id("cb16")).isDisplayed(), "Checkbox for GitHub hook trigger for GITScm polling is not displayed");
        Assert.assertTrue(driver.findElement(By.id("cb16")).isEnabled(), "Checkbox for GitHub hook trigger for GITScm polling is not enabled");

        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(text(), 'GitHub hook trigger for GITScm polling')]")).getText(), "GitHub hook trigger for GITScm polling", "Text next to checkbox is incorrect");

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='jenkins-help-button']")).isDisplayed(), "Help icon is not displayed");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[3]/div[6]/div[1]/div/a")).getDomAttribute("title"), "Help for feature: GitHub hook trigger for GITScm polling", "Help icon title is incorrect");
    }



    @Test()
    public void testRemoteTriggerOptionDisplaysTokenField() {
        WebDriver driver = getDriver();
        TestUtils.createFreestyleProject(getDriver(), PROJECT_NAME);

        TestUtils.scrollToItemWithJS(driver,
                driver.findElement(By.id("source-code-management")));

        //Click on the checkbox
        driver.findElement(By.xpath("//label[contains(text(), 'Trigger builds remotely')]")).click();

        //Wait for the token field to be displayed and enter the token
        driver.findElement(By.xpath("//input[@name='authToken']")).sendKeys("Authentication Token");
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        //Configure
        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[5]/span/a")).click();
        TestUtils.scrollToItemWithJS(driver,
                driver.findElement(By.id("source-code-management")));

        //Assertions
        Assert.assertEquals(
                driver.findElement(By.xpath("//div[text()='Authentication Token']")).getText(),
                "Authentication Token"
        );
        Assert.assertEquals(
                driver.findElement(By.xpath("//input[@name='authToken']")).getDomAttribute("value"),
                "Authentication Token"
        );
        Assert.assertEquals(
                driver.findElement(By.xpath("//*[@id='main-panel']/form/div[1]/section[3]/div[3]/div[4]/div/div[2]"))
                        .getText()
                        .trim(),
                """
                Use the following URL to trigger build remotely: JENKINS_URL/job/New%20project/build?token=TOKEN_NAME or /buildWithParameters?token=TOKEN_NAME
                Optionally append &cause=Cause+Text to provide text that will be included in the recorded build cause.
                """.trim(),
                "Текст не совпадает с ожидаемым!"
        );
    }


    @Test
    public void testBuildAfterOtherProjectsAreBuiltOptionDisplaysField() {
        WebDriver driver = getDriver();

        TestUtils.createFreestyleProject(getDriver(), PROJECT_NAME);

        TestUtils.scrollToItemWithJS(driver,
                driver.findElement(By.id("source-code-management")));

        // Enable “Build after other projects are built”
        driver.findElement(By.xpath("//label[contains(text(), 'Build after other projects are built')]")).click();

        //Wait for the "Projects to watch" field to be displayed and ent
        driver.findElement(By.xpath("//input[@name='_.upstreamProjects']")).sendKeys("New Project");

        //Wait for the "Projects to watch" field to be displayed and enter the project name
        getWait5().until(ExpectedConditions.attributeToBe(By.xpath("//input[@name='_.upstreamProjects']"), "aria-expanded", "true"));
        driver.findElement(By.xpath("//input[@name='_.upstreamProjects']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        //Click on the "Trigger only if build is stable" checkbox and others
        List<WebElement> labels = driver.findElements(By.xpath("//input[@name='ReverseBuildTrigger.threshold']/following-sibling::label"));
        for (WebElement label : labels) {
            label.click();
        }

        //Click Submit and save the project
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        //Configure
        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[5]/span/a")).click();
        TestUtils.scrollToItemWithJS(driver,
                driver.findElement(By.id("source-code-management")));

        //Assertions
        Assert.assertEquals(
                driver.findElement(By.xpath("//input[@name='_.upstreamProjects']"))
                        .getDomAttribute("value"),
                "New project, ",
                "Value attribute doesn't match expected text"
        );

        List<WebElement> radios = driver.findElements(By.name("ReverseBuildTrigger.threshold"));
        Assert.assertEquals(
                radios.get(radios.size() - 1).getDomAttribute("checked"),
                "true",
                "The last radio button should be selected"
        );
    }



    @Test
    public void testBuildPeriodicallyScheduleFieldIsDisplayed() {

        final String Schedule = "H 14 * * 1-5";

        //Actions
        String actualSchedule = new HomePage(getDriver())
                .createJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToTriggersItem()
                .checkBuildPeriodicallyCheckbox()
                .sendScheduleText(Schedule)
                .clickSaveButton()
                .clickConfigure()
                .scrollToTriggersItem()
                .sendScheduleActualText();


        //Assertions
        Assert.assertEquals(actualSchedule, Schedule, "Schedule text doesn't match expected text");
    }
}
