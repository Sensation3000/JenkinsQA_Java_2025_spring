package school.redrover;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import static org.testng.AssertJUnit.assertTrue;

public class NewJobTest extends BaseTest {
    private static final String jobName = "Test item";

    @Test
    public void testBuildJob() {
        TestUtils.createFreestyleProject(getDriver(), jobName);
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[2]/span/button")).click();
        TestUtils.gotoHomePage(this);

        By jobButton = By.xpath("//td/a/span[text() = '%s']/../button".formatted(jobName));
        WebElement button = getWait5().until(ExpectedConditions.elementToBeClickable(jobButton));
        TestUtils.moveAndClickWithJS(getDriver(), button);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='jenkins-dropdown__item__icon']/parent::*[contains(., '%s')]"
                        .formatted("Build Now")))).click();
        getDriver().findElement(By.linkText(jobName)).click();
        getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='lastBuild/']"))).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'console')]")).click();

        String expectedText = "Finished: SUCCESS";
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.id("out"), expectedText));
        String actualText = getDriver().findElement(By.id("out")).getText();

        assertTrue("В Console Output отсутствует запись об успешной сборке", actualText.contains(expectedText));
    }

    @Test
    public void testDeleteBuild () {
        testBuildJob();
        getDriver().findElement(
                By.xpath("//a[@href='/job/Test%20item/lastBuild/confirmDelete']")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/Test%20item/changes']")).click();
        String infoBuilds = getWait5().
                until(ExpectedConditions.visibilityOfElementLocated(By.id("main-panel"))).getText();

        assertTrue("Build was not deleted successfully", infoBuilds.contains("No builds"));
    }
}