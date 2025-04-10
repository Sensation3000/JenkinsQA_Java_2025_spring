package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.time.Duration;


public class FreestyleProjectManagementBuildNowTest extends BaseTest {

    final String name_Freestyle_Project = "new Freestyle Project";

    void createNewItemFrestyle(WebDriver driver) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        driver.findElement(By.id("name")).sendKeys(name_Freestyle_Project);
        driver.findElement(By.className("hudson_model_FreeStyleProject")).click();

        getWait5()
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                By.id("ok-button")))
                .click();
        
        driver.findElement(By.name("Submit")).click();
    }

    @Test
    public void testAvailableBuildNowOnProjectPage() {
        WebDriver driver = getDriver();

        createNewItemFrestyle(driver);

        getWait5()
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                By.xpath(  "//*[@class='task-link task-link-no-confirm ' and contains(@href,'build')]")))
                .click();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).getText(),
                "Build scheduled");
    }
    @Test
    public void testAvailableBuildNowOnbreadcrumbs() {
        WebDriver driver = getDriver();

        createNewItemFrestyle(driver);

        TestUtils.gotoHomePage(this);

        getWait5()
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                By.xpath("//*[contains(@href,'build') and contains(@class,'build')]")))
                .click();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).getText(),
                "Build scheduled");
    }


     @Test
    public void testAvailableBuildNowOnDropDownList() {
        WebDriver driver = getDriver();

        createNewItemFrestyle(driver);

        TestUtils.gotoHomePage(this);

        new Actions(driver)
                .moveToElement(driver.findElement(By.xpath("//a[contains(@class,'jenkins-table__link model-link inside')]")))
                .pause(Duration.ofMillis(500))
                .perform();

        getWait10()
                .until(ExpectedConditions
                        .elementToBeClickable (
                                By.xpath("//button[contains(@data-href,'job') and @class='jenkins-menu-dropdown-chevron'][1]")))
                .click();

        driver.findElement(By.xpath("//button[contains(@class,'jenkins-dropdown__item ')][1]")).click();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).getText(),
                "Build Now: Done.");
    }
}
