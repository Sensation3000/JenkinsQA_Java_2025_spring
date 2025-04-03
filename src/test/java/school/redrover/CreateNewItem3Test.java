package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class CreateNewItem3Test extends BaseTest {

    Actions actions;

    @BeforeMethod
    void setUp() {
        if (getDriver() != null) {
            actions = new Actions(getDriver());
        } else {
            throw new IllegalStateException("WebDriver is null. Cannot initialize Actions.");
        }
    }

    private List<WebElement> getNewItemTypes() {
        return getDriver().findElements(By.xpath("//li[@role='radio']"));
    }

    @Test
    public void testOkButtonWhenFieldIsEmpty() {
        List<WebElement> newItemTypes = getNewItemTypes();
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        for (int i = 0; i < newItemTypes.size(); i++) {
            actions.scrollByAmount(0, 300).perform();
            newItemTypes.get(i).click();

            Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
        }
    }

    @Test
    public void testIfErrorMessageIsDisplayedWhenFieldIsEmpty() {
        List<WebElement> newItemTypes = getNewItemTypes();
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        for (int i = 0; i < newItemTypes.size(); i++) {
            actions.scrollByAmount(0, 300).perform();
            newItemTypes.get(i).click();

            Assert.assertEquals(
                    getDriver().findElement(By.cssSelector(".input-validation-message")).getText(),
                    "» This field cannot be empty, please enter a valid name"
            );
        }
    }
}
