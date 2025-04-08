package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class DashboardTest extends BaseTest {

    @Test
    public void testDashboardEnabled(){
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(1) > a"))
                .getText(), "Dashboard");
    }
}