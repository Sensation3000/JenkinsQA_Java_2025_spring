package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class HomePageTest extends BaseTest {

    @Test
    public void testSetUpADistributedBuild() {
        WebDriver driver = getDriver();

        WebElement setUpElement = driver.findElement(By.cssSelector("#main-panel > div:nth-child(3) > div > section:nth-child(4) > h2"));
        Assert.assertEquals(setUpElement.getText(), "Set up a distributed build");
    }
}
