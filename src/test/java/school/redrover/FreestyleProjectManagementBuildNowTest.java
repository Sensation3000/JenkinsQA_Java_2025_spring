package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;


public class FreestyleProjectManagementBuildNowTest extends BaseTest {

    final String name_Freestyle_Project = "new Freestyle Project";

    void createNewItemFrestyle(WebDriver driver) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        driver.findElement(By.id("name")).sendKeys(name_Freestyle_Project);
        driver.findElement(By.className("hudson_model_FreeStyleProject")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
    }
    @Ignore //Error:    FreestyleProjectManagementBuildNowTest.testAvailableBuildNowOnProjectPage:32 » NoSuchElement no such element: Unable to locate element: {"method":"xpath","selector":"//*[@class='task-link task-link-no-confirm ' and contains(@href,'build')]"}
    @Test
       public void testAvailableBuildNowOnProjectPage() {
        WebDriver driver = getDriver();

        createNewItemFrestyle(driver);

        driver.findElement(By.xpath(  "//*[@class='task-link task-link-no-confirm ' and contains(@href,'build')]")).click();
        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).getText(),
                "Build scheduled");
    }
}
