package school.redrover;

import org.openqa.selenium.By;
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
        getDriver().findElement(By.xpath("//button[@name='Submit']"))
                .click();

        WebElement enableButton = getWait5()
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"enable-project\"]/button")));
        enableButton.click();


        WebElement disableButton = getWait10().
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"disable-project\"]/button")));
        Assert.assertTrue(disableButton.isDisplayed());
    }
}
