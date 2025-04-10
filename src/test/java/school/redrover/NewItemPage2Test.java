package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.List;

public class NewItemPage2Test extends BaseTest {

    private Actions actions;
    private final By newJobsLocator = By.xpath("//a[@href='/view/all/newJob']");

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

    private int getScrollValue() {
        List<WebElement> newItemTypes = getDriver().findElements(By.xpath("//li[@role='radio']"));
        Dimension size = newItemTypes.get(0).getSize();

        return (size.getHeight() - 12 * 2) * (newItemTypes.size() - 1);
    }

    private void clickOnNewItemLink() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(newJobsLocator))
                .click();
    }

    @Test
    public void testIfPageIsAccessibleFromHomePage() {
        clickOnNewItemLink();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("/view/all/newJob"));
        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("#add-item-panel > h1")).getText(),
                "New Item");
    }

    @Test
    public void testInputPositiveValidation() {
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric() + "_";
        clickOnNewItemLink();

        WebElement inputField = getDriver().findElement(By.id("name"));
        inputField.sendKeys(randomAlphaNumericValue);
        WebElement divEl = inputField.getShadowRoot().findElement(By.cssSelector("div"));

        Assert.assertTrue(getDriver().findElement(By.id("itemname-invalid")).getText().isEmpty());
        Assert.assertEquals(randomAlphaNumericValue, divEl.getText());
    }

    @Test
    public void testIfAvailableJobsAreDisplayedOnThePage() {
        clickOnNewItemLink();

        List<WebElement> jobsLabels = getDriver().findElements(By.className("label"));
        List<String> expectedLabels = List.of(
                "Freestyle project", "Pipeline", "Multi-configuration project",
                "Folder", "Multibranch Pipeline", "Organization Folder");

        for (int i = 0; i < expectedLabels.size(); i++) {
            Assert.assertEquals(jobsLabels.get(i).getText(), expectedLabels.get(i));
        }
    }

    @Test
    public void testOkButtonWhenFieldIsEmpty() {
        clickOnNewItemLink();

        List<WebElement> newItemTypes = getNewItemTypes();
        int expectedScrollValue = getScrollValue();

        for (int i = 0; i < newItemTypes.size(); i++) {
            newItemTypes.get(i).click();
            actions.scrollByAmount(0, expectedScrollValue).perform();

            Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
        }
    }

    @Test
    public void testIfErrorMessageIsDisplayedWhenFieldIsEmpty() {
        clickOnNewItemLink();

        List<WebElement> newItemTypes = getNewItemTypes();
        int expectedScrollValue = getScrollValue();

        for (int i = 0; i < newItemTypes.size(); i++) {
            newItemTypes.get(i).click();
            actions.scrollByAmount(0, expectedScrollValue).perform();

            Assert.assertEquals(
                    getDriver().findElement(By.cssSelector(".input-validation-message")).getText(),
                    "» This field cannot be empty, please enter a valid name"
            );
        }
    }
}
