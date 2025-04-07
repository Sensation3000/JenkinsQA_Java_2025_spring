package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class PipelineDisableEnableTest  extends BaseTest {
    public void createPipeline() {
        String name = "Pipeline";

        getDriver().findElement(By.linkText("New Item"))
                .click();
        getDriver().findElement(By.xpath("//input[@id=\"name\"]"))
                .sendKeys(name);
        getDriver().findElement(By.xpath("//*[text()='Pipeline']"))
                .click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']"))
                .click();
    }
    @Test
    public void disablePipelineTest() {

        final String expectedText = "This project is currently disabled\nEnable";

        createPipeline();
        getDriver().findElement(By.xpath("//span[@title=\"Enable or disable the current project\"]"))
                .click();
        getDriver().findElement(By.xpath("//button[@name=\"Submit\"]"))
                .click();
        String actualText = getDriver().findElement(By.xpath("//form[@id=\"enable-project\"]"))
                .getText();
        Assert.assertEquals(actualText, expectedText);
    }
    @Test
    public void enablePipelineTest() {
        createPipeline();
        getDriver().findElement(By.xpath("//span[@title=\"Enable or disable the current project\"]"))
                .click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='Submit']")))
                .click();

        WebElement enableButton;
        for (int attempts = 0; attempts < 6; attempts++) {
            try {
                enableButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
                enableButton.click();
                break;
            } catch (StaleElementReferenceException e) {

            }
        }
        WebElement disableButton = null;

        for (int attempts = 0; attempts < 3; attempts++) {
            try {
                disableButton = getDriver().findElement(By.xpath("//button[@name=\"Submit\"]"));
                break;
            } catch (StaleElementReferenceException e) {

            }

        }
        Assert.assertTrue(disableButton.isDisplayed());
    }
}
