package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineDisableTest  extends BaseTest {
       @Test
    public void disablePipelineTest() {

        final String name = "Pipeline";
        final String expectedText = "This project is currently disabled\nEnable";

        getDriver().findElement(By.linkText("New Item"))
                .click();
        getDriver().findElement(By.xpath("//input[@id=\"name\"]"))
                .sendKeys(name);
        getDriver().findElement(By.xpath("//*[text()='Pipeline']"))
                .click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']"))
                .click();

        getDriver().findElement(By.xpath("//span[@title=\"Enable or disable the current project\"]"))
                .click();
        getDriver().findElement(By.xpath("//button[@name=\"Submit\"]"))
                .click();
        String actualText = getDriver().findElement(By.xpath("//form[@id=\"enable-project\"]"))
                .getText();
        Assert.assertEquals(actualText, expectedText);
    }
}
