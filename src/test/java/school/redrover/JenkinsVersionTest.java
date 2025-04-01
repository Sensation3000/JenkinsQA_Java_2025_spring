package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class JenkinsVersionTest extends BaseTest {

    @Test
    public void testJenkinsVersionProperlyDisplayed() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//div[@id='tasks']/div[3]")).click();
        driver.findElement(By.xpath("//a[@href='about']/dl/dt")).click();
        String actualVersion = driver.findElement(By.xpath("//p[@class='app-about-version']")).getText();

        Assert.assertEquals(actualVersion, "Version 2.492.2");
    }
}
