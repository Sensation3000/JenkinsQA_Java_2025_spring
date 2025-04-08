package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

public class BuildTest extends BaseTest {
    private WebDriverWait wait60;

    private WebDriverWait getWait60() {
        if (wait60 == null) {
            wait60 = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        }

        return wait60;
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(Method method) {
        if (getWait60() != null) {
            wait60 = null;
        }
    }

    @Test
    public void testBuildPeriodically() {
        final String projectName = "Build Periodically";
        final String everyMinuteSchedule = "* * * * *";

        TestUtils.createFreestyleProject(getDriver(), projectName);
        TestUtils.scrollAndClickWithJS(getDriver(), getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input[name = 'hudson-triggers-TimerTrigger']"))));
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.spec"))).sendKeys(everyMinuteSchedule);
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h1"), projectName));

        final long startTime = System.currentTimeMillis();
        getWait60().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("jenkins-build-history")));
        final long endTime = System.currentTimeMillis();

        final String buildOneText = getDriver().findElement(By.id("jenkins-build-history")).getText();
        final List<WebElement> builds = getDriver().findElements(By.cssSelector("div[page-entry-id]"));

        Assert.assertEquals(builds.size(), 1);
        Assert.assertTrue(buildOneText.contains("Today"));
        Assert.assertTrue(buildOneText.contains("#1"));
        Assert.assertTrue(startTime - endTime <= 60_000);
    }
}
