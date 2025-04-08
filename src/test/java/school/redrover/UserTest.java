package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class UserTest extends BaseTest {

    @Test
    public void testEditDescriptionField() {
        final String descriptionText = "User Description";

        WebDriver driver = getDriver();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='login page-header__hyperlinks']/a[@class='model-link']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/user/admin/account']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.name("_.description"))).sendKeys(descriptionText);
        driver.findElement(By.name("Submit")).click();

        String actualDescription = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description"))).getText();

        Assert.assertEquals(actualDescription, descriptionText);
    }
}
