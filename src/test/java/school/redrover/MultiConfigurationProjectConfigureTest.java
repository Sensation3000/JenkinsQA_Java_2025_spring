package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MultiConfigurationProjectConfigureTest extends BaseTest {
    private static final String projectName = "MultiConfigurationProject";

    @Test
    public void testToolTipEnableDisable() {
        WebDriver driver = getDriver();
        Actions actions = new Actions(driver);

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(projectName);
        driver.findElement(By.className("hudson_matrix_MatrixProject")).click();
        driver.findElement(By.id("ok-button")).click();

        actions.moveToElement(driver.findElement(By.className("jenkins-toggle-switch__label"))).perform();
        WebElement tooltip = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.className("tippy-content")));

        assertEquals(tooltip.getText(), "Enable or disable the current project");
    }
    @Test
    public void testCheckWarningDisableProject() {
        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(projectName);
        driver.findElement(By.className("hudson_matrix_MatrixProject")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.id("toggle-switch-enable-disable-project")).click();
        driver.findElement(By.name("Submit")).click();

        String warning = driver.findElement(By.id("enable-project")).getText();

        assertTrue(warning.contains("This project is currently disabled"), "Project is not disabled");
    }
}
