package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MainWindowTest extends BaseTest {
    @Test
    public void testMainWindowText() {
        WebDriver driver = getDriver();
        WebElement mainText = driver.findElement
                (By.xpath("//h2[text()=\"Start building your software project\"]"));
        Assert.assertEquals(mainText.getText(), "Start building your software project");
    }
}
