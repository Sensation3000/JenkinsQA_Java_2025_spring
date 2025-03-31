package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class GoToTabBuildHistoryTest extends BaseTest {

    @Test
    public void testGoToTabBuildHistory() {
        WebDriver driver = getDriver();
        getDriver().findElement(By.xpath("(//div[contains(@id,'tasks')]//a)[2]")).click();

        String resultClickTabBuildHistory = driver.findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(resultClickTabBuildHistory, "Build History of Jenkins");
    }
}
