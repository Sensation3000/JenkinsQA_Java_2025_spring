package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;

import java.util.List;

public class GroupClubRedroverTest extends BaseTest {
    SoftAssert softAssert;

    @Test
    public void verifySuccessfulCreationNewJob() {
        softAssert = new SoftAssert();
        WebDriver driver = getDriver();
        String jobName = "freestyle";

        driver.findElement(By.cssSelector("a[href='newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(jobName);
        driver.findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        driver.findElement(By.id("ok-button")).click();
        softAssert.assertEquals(
                driver.findElement(By.id("general")).getText(), "General");

        driver.findElement(By.name("Apply")).click();
        softAssert.assertTrue(
                driver.findElement(By.id("notification-bar")).isDisplayed());

        driver.findElement(By.name("Submit")).click();
        softAssert.assertEquals(
                driver.findElement(By.cssSelector(".job-index-headline")).getText(), jobName);

        driver.findElement(By.cssSelector("a[href='/']")).click();
        List<WebElement> jobsOnDashbord = driver.findElements(By.cssSelector(".jenkins-table__link"));
        softAssert.assertTrue(
                jobsOnDashbord.stream().anyMatch(job -> job.getText().contains(jobName)),
                "New Job " + jobName + " not found in Dashbord"
        );

        softAssert.assertAll();
    }
}
