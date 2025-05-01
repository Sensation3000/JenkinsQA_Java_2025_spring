package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import java.util.ArrayList;
import java.util.List;

public class NewItemCreate3Test extends BaseTest {

    private static final String NEW_ITEM_NAME = "New free-style project";

    public void goToNewItemPage() {
        getDriver().findElement(By.linkText("New Item")).click();
    }

    @Test
    public void testCreateNewItemWithoutName() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();

        Assert.assertEquals(
                (getDriver().findElement(By.id("itemname-required")).getText()),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }

    @Test
    public void testCreateNewItemNameWithIllegalCharacters() {
        String el;
        goToNewItemPage();

        List<String> specialChars = new ArrayList<>(List.of("@", "<", "#", "&", "?", "!", "/"));
        for (String element : specialChars) {
            el = element;
            String messageError = "» ‘" + el + "’ is an unsafe character";
            getDriver().findElement(By.id("name")).sendKeys(element);
            String text = getWait5()
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid")))
                    .getText();
            Assert.assertEquals(messageError, text);
            getDriver().findElement(By.id("name")).clear();
        }
    }

    @Ignore
    @Test
    public void testCreateItemNameWithAppropriateCharacters() {
        goToNewItemPage();

        List<String> names = new ArrayList<>(List.of("ABCH", "avbcj", "пренш", "НЫГШ", "125487", "_"));
        for (String element : names) {
            getDriver().findElement(By.id("name")).sendKeys(element);
            Assert.assertFalse(getDriver().findElement(By.id("itemname-required")).isDisplayed());
            getDriver().findElement(By.id("name")).clear();
        }
    }

    @Test
    public void testCreateNewItemWithCorrectName() {
        final String expectedName = "New free-style project";
        String projectName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(NEW_ITEM_NAME)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .waitUntilTextNameProjectToBePresentInH1(NEW_ITEM_NAME)
                .getProjectName();

        Assert.assertEquals(projectName, expectedName);
    }

    @Test(dependsOnMethods = "testCreateNewItemWithCorrectName")
    public void testCreateNewItemWithExistingName() {
        goToNewItemPage();
        getDriver().findElement(By.id("name")).sendKeys(NEW_ITEM_NAME);
        TestUtils.scrollToItemWithJS(getDriver(), getDriver().findElement(By.id("ok-button")));
        WebElement el = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.id("itemname-invalid")));
        getWait10().until(ExpectedConditions.visibilityOf(el));

        Assert.assertEquals(el.getText(), "» A job already exists with the name ‘New free-style project’");
    }
}
