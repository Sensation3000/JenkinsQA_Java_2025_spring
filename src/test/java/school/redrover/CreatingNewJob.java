package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class CreatingNewJob extends BaseTest {
    SoftAssert softAssert;

    @Test
    public void verifySuccessfulCreationNewJob() {
        softAssert = new SoftAssert();
        WebDriver driver = getDriver();
        String jobName = "freestyle";

        TestUtils.waitUntilVisible5(this, By.cssSelector("a[href='newJob']")).click();
        TestUtils.waitUntilVisible5(this, By.id("name")).sendKeys(jobName);
        TestUtils.waitUntilVisible5(this, By.cssSelector(".hudson_model_FreeStyleProject")).click();
        TestUtils.waitUntilVisible5(this, By.id("ok-button")).click();

        softAssert.assertEquals(
                TestUtils.waitUntilVisible10(this, By.id("general")).getText(),
                "General");

        TestUtils.waitUntilVisible5(this, By.name("Apply")).click();
        softAssert.assertTrue(
                driver.findElement(By.id("notification-bar")).isEnabled());

        TestUtils.waitUntilVisible5(this, By.name("Submit")).click();
        softAssert.assertEquals(
                TestUtils.waitUntilVisible5(this, By.cssSelector(".job-index-headline")).getText(),
                jobName);

        TestUtils.waitUntilVisible5(this, By.cssSelector("a[href='/']")).click();
        softAssert.assertEquals(
                TestUtils.waitUntilVisible10(this, By.cssSelector(".jenkins-table__link")).getText(),
                jobName);

        softAssert.assertAll();
    }
}
