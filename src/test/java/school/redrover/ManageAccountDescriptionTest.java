package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ManageAccountDescriptionTest extends BaseTest {

    @Test
    public void testOpenAccountSettings() {
        getDriver().findElement(By.xpath("//a[@href='/user/admin']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//*[@id='main-panel']/div[3]")).getText(), "Jenkins User ID: admin");
    }

    @Test
    public void testEditUserNameAndDescription() {
        final String userDescription = "Admin User Description";

        WebDriver driver = getDriver();

        WebElement button = driver.findElement(By.cssSelector("button.jenkins-menu-dropdown-chevron"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().perform();

        getWait5().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='/user/admin/account']"))).click();

        WebElement userDescriptionField = driver.findElement(By.name("_.description"));
        userDescriptionField.clear();
        userDescriptionField.sendKeys(userDescription);
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(driver.findElement(
                By.id("description")).getText(), userDescription);
    }

    @Ignore //Error:    ManageAccountDescriptionTest.testChangeUserDescription:58 » StaleElementReference stale element reference: stale element not found
    @Test
    public void testChangeUserDescription() {
        final String userDescription = "Updated user description";

        getDriver().findElement(By.xpath("//a[@href='/user/admin']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.id("description-link")))).click();
        getWait5().until(ExpectedConditions.visibilityOf(
                getDriver().findElement(By.name("description")))).clear();
        getDriver().findElement(By.name("description")).sendKeys(userDescription);
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.name("Submit")))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOf(
                getDriver().findElement(By.id("description")))).getText(), userDescription);
    }
}
