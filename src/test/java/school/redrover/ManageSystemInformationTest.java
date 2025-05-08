package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;


public class ManageSystemInformationTest extends BaseTest {


    @Test
    public void testSystemPropertiesHiddenClassAfterClick() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        WebElement systemInformation = getDriver().findElement(By.xpath("//a[@href='systemInfo']"));
        TestUtils.scrollAndClickWithJS(getDriver(), systemInformation);

        WebElement showValuesBtn = getDriver().findElement(By.xpath("//button[normalize-space()='Show values'][1]"));
        showValuesBtn.click();

        WebElement hiddenContent = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@class='app-hidden-info-hide']")));

        String classAttr = hiddenContent.getDomAttribute("class");

        Assert.assertEquals(classAttr, "app-hidden-info-hide");
    }

    @Test
    public void testEnvironmentVariablesHiddenClassAfterClick() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        WebElement systemInformation = getDriver().findElement(By.xpath("//a[@href='systemInfo']"));
        TestUtils.scrollAndClickWithJS(getDriver(), systemInformation);


        getDriver().findElement(By.xpath("//a[normalize-space()='Environment Variables']")).click();
        WebElement showValuesBtn = getDriver().findElement(By.xpath("(//button[normalize-space()='Show values'])[2]"));

        showValuesBtn.click();

        WebElement hiddenContent = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@class='app-hidden-info-hide']")));

        String classAttr = hiddenContent.getDomAttribute("class");

        Assert.assertEquals(classAttr, "app-hidden-info-hide");

    }
}