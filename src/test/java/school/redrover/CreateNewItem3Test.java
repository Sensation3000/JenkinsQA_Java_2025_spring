package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class CreateNewItem3Test extends BaseTest {

    @Test
    public void testOkButtonWhenFieldIsEmpty() {
        Actions actions = new Actions(getDriver());

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        List<WebElement> newItemTypes = getDriver().findElements(By.cssSelector("input[type=radio]"));

        for (int i = 0; i < newItemTypes.size(); i++) {
            actions.scrollByAmount(0, 300).perform();
            getDriver().findElements(By.xpath("//li[@role='radio']")).get(i).click();

            Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
        }
    }
}
