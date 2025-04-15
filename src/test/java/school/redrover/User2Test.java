package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class User2Test extends BaseTest {

    @Test
    public void testUserItemOnManageJenkinsPage() {
        final String expectedPath = "/manage/securityRealm/";
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a[href='/manage']")).click();
        driver.findElement(By.cssSelector("a[href='securityRealm/']")).click();

        getWait5().until(ExpectedConditions.urlContains(expectedPath));
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedPath),
                "Current URL doesn't contain expected path: " + expectedPath);
    }
}
