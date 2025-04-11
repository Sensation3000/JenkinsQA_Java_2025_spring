package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class FreestyleProjectConfigurationBuildTriggersTest extends BaseTest {

    @Test
    public void testCheckBuildTriggersSection() {

        WebDriver driver = getDriver();
        final String projectName = "New project";
        TestUtils.createFreestyleProject(getDriver(), projectName);

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

}
