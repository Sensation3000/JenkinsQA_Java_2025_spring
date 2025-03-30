package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class TrialTest extends BaseTest {
    @Test
    public void checkTheHeadings() {
        WebDriver driver = getDriver();

        WebElement titleElement = driver.findElement( By.xpath ( "//h2[contains(text(),'Start building')]" ));
        Assert.assertEquals( titleElement.getText(), "Start building your software project");
    }
}
