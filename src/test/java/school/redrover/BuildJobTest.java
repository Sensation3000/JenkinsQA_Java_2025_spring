package school.redrover;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import static org.testng.AssertJUnit.assertTrue;

public class BuildJobTest extends BaseTest {
    @Ignore //BuildJobTest.testBuildJob:20 » StaleElementReference stale element reference: stale element not found
    @Test
    public void testBuildJob() {
        final String jobName = "Test item";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(jobName);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/']"))).click();

        By jobButton = By.xpath("//td/a/span[text() = '%s']/../button".formatted(jobName));
        WebElement button = getWait5().until(ExpectedConditions.elementToBeClickable(jobButton));
        TestUtils.moveAndClickWithJS(getDriver(), button);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='jenkins-dropdown__item__icon']/parent::*[contains(., '%s')]"
                        .formatted("Build Now")))).click();
        getDriver().findElement(By.linkText(jobName)).click();
        getDriver().navigate().refresh();
        getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='lastBuild/']"))).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'console')]")).click();

        String expectedText = "Finished: SUCCESS";
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.id("out"), expectedText));
        String actualText = getDriver().findElement(By.id("out")).getText();

        assertTrue("В Console Output отсутствует запись об успешной сборке", actualText.contains(expectedText));
    }
    @Ignore
    @Test
    public void testDeleteBuild () {
        testBuildJob();
        getDriver().findElement(By.xpath("//a[@href='/job/Test%20item/lastBuild/confirmDelete']")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/Test%20item/changes']")).click();
        String infoBuilds = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("main-panel"))).getText();

        assertTrue("Build was not deleted successfully", infoBuilds.contains("No builds"));
    }
}