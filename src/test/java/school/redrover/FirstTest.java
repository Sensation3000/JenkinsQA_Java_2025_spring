package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FirstTest extends BaseTest {

    @Test
    public void testWelcome() {
        WebDriver driver = getDriver();

        WebElement titleElement = driver.findElement(By.cssSelector(".empty-state-block h1"));
        Assert.assertEquals(titleElement.getText(), "Welcome to Jenkins!");
    }
}
