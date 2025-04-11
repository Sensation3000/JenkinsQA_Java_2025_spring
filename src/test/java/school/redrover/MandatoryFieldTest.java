package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MandatoryFieldTest extends BaseTest {

    @Test
    public void testVerifyMandatoryField() throws InterruptedException {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='newJob']")).click();
        driver.findElement(By.id("ok-button")).click();

        String errorText = getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='input-validation-message']")))
                .getText();

        Assert.assertEquals(errorText, "» This field cannot be empty, please enter a valid name");
    }
}
