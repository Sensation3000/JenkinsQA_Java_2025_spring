package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;

public class GroupClubRedroverTest extends BaseTest {
    SoftAssert softAssert;

    @Test
    public void verifySuccessfulCreationNewJob() {
        softAssert = new SoftAssert();
        WebDriver driver = getDriver();
        String jobName = "freestyle";

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='newJob']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(jobName);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hudson_model_FreeStyleProject"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("ok-button"))).click();
        softAssert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("general"))).getText(),
                "General");

         driver.findElement(By.name("Apply")).click();
         softAssert.assertTrue(
                 driver.findElement(By.id("notification-bar")).isEnabled());

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
        softAssert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job-index-headline"))).getText(),
                jobName);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/']"))).click();
        softAssert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-table__link"))).getText(), jobName);

        softAssert.assertAll();
    }
}
