package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AccountSettingsTest extends BaseTest {

    @Test
    public void testOpenAccountSettings() {
        getDriver().findElement(By.xpath("//a[@href='/user/admin']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//*[@id='main-panel']/div[3]")).getText(), "Jenkins User ID: admin");
    }
}
