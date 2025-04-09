package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AccountSettingsTest extends BaseTest {

    @Test
    public void testOpenAccountSettings() {
        getDriver().findElement(By.xpath("//a[@href='/user/admin']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//*[@id='main-panel']/div[3]")).getText(), "Jenkins User ID: admin");
    }

    @Test
    public void testEditUserNameAndDescription() {
        final String userFullName = "admin";
        final String userDescription = "Admin User Description";

        WebDriver driver = getDriver();

        WebElement button = driver.findElement(By.cssSelector("button.jenkins-menu-dropdown-chevron"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();

        getWait5().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='/user/admin/account']"))).click();

        WebElement userFullNameField = driver.findElement(By.name("_.fullName"));
        userFullNameField.clear();
        userFullNameField.sendKeys(userFullName);

        WebElement userDescriptionField = driver.findElement(By.name("_.description"));
        userDescriptionField.clear();
        userDescriptionField.sendKeys(userDescription);
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(driver.findElement(
                By.xpath("//*[@id='main-panel']/div[1]/div[1]/h1")).getText(), userFullName);
        Assert.assertEquals(driver.findElement(
                By.id("description")).getText(), userDescription);
    }
}
