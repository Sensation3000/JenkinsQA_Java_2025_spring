package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

@Ignore
public class JenkinsThemeTest extends BaseTest {

        @Test
        public void testDarkTheme() {
            getDriver().findElement(By.xpath("//*[@id=\"page-header\"]/div[3]/a[1]/span")).click();
            getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[5]/span/a")).click();
            getDriver().findElement(By.xpath("//label[span[text() = 'Dark']]")).click();
            getDriver().findElement(By.cssSelector("button.jenkins-button.apply-button")).click();

            Assert.assertEquals(getDriver().findElement(By.xpath(
                    "//*[@id=\"notification-bar\"]")).getText(),
                    "Saved");
        }
}