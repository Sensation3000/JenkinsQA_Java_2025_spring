package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ManageAccountDescription2Test extends BaseTest {

    @Ignore
    @Test
    public void navigateToSettingPage() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("(//*[@class='icon-md'])[3]")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Account']")).getText(), "Account");
    }
}
