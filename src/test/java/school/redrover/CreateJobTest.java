package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class CreateJobTest extends BaseTest {
    @Test
    public void testNotCreateNewJobWithoutName() throws InterruptedException {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        List<WebElement> itemList = getDriver().findElements(By.cssSelector("ul.j-item-options li"));
        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        for (WebElement item: itemList) {
            item.click();
            Assert.assertTrue(errorMessage.isDisplayed() && !okButton.isEnabled());
        }
    }
}
