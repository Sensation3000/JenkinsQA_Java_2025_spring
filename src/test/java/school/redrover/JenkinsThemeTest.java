package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class JenkinsThemeTest extends BaseTest {

        @Test
        public void testDarkTheme() throws InterruptedException {
            getDriver().findElement(By.xpath(
                    "//*[@id=\"page-header\"]/div[3]/a[1]/span")).click();

            getWait5().
                    until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                            "//*[@id=\"tasks\"]/div[5]/span/a"))).click();

            getDriver().findElement(By.xpath("//label[span[text() = 'Dark']]")).click();
            getDriver().findElement(By.cssSelector("button.jenkins-button.apply-button")).click();

            Assert.assertEquals(getWait5().
                    until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                            "//*[@id=\"notification-bar\"]"))).getText(),
                            "Saved");
        }
}
