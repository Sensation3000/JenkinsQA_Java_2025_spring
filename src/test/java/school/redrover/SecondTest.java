package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SecondTest extends BaseTest {
    @Test(description = "easy test")
    public void testTooltipWhenNewItemWasAddedWOName() {
        getDriver().findElement(By.xpath("//a[@href=\"/view/all/newJob\"]")).click();
        getDriver().findElement(By.xpath("//input[@name=\"name\"]")).sendKeys("");
        String result = getDriver().findElement(By.id("itemname-required")).getText();

        Assert.assertEquals(result,"» This field cannot be empty, please enter a valid name", "Tooltip is not displayed");

    }
}
