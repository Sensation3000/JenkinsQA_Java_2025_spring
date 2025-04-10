package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
}
