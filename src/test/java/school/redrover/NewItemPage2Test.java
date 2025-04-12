package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.time.Duration;
import java.util.List;

public class NewItemPage2Test extends BaseTest {

    private Actions actions;
    private WebDriverWait wait15;
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

    @Test(dataProvider = "itemTypes")
    public void testItemsDescriptions(String itemTypeName, String expectedItemDescription) {
        clickOnNewItemLink();

        WebElement itemType = getDriver().findElement(By.xpath(String.format("//span[text()='%s']", itemTypeName)));
        String itemDescriptionText = itemType.findElement(By.xpath("./../../div")).getText();

        Assert.assertEquals(itemDescriptionText, expectedItemDescription);
    }

    @DataProvider(name = "itemTypes")
    public Object[][] itemTypes() {
        return new Object[][]{
                {"Freestyle project", "Classic, general-purpose job type that checks out from up to one SCM, executes build steps serially, followed by post-build steps like archiving artifacts and sending email notifications."},
                {"Pipeline", "Orchestrates long-running activities that can span multiple build agents. Suitable for building pipelines (formerly known as workflows) and/or organizing complex activities that do not easily fit in free-style job type."},
                {"Multi-configuration project", "Suitable for projects that need a large number of different configurations, such as testing on multiple environments, platform-specific builds, etc."},
                {"Folder", "Creates a container that stores nested items in it. Useful for grouping things together. Unlike view, which is just a filter, a folder creates a separate namespace, so you can have multiple things of the same name as long as they are in different folders."},
                {"Multibranch Pipeline", "Creates a set of Pipeline projects according to detected branches in one SCM repository."},
                {"Organization Folder", "Creates a set of multibranch project subfolders by scanning for repositories."}
        };
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

    @Test
    public void testIfCopyFromOptionIsDisplayed() {
        wait15 = new WebDriverWait(getDriver(), Duration.ofSeconds(16));
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric();

        TestUtils.createProjectWithName(getDriver(), randomAlphaNumericValue, 1);
        wait15.until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins-home-link"))).click();
        clickOnNewItemLink();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("p.jenkins-form-label")).getText(),
                "If you want to create a new item from other existing, you can use this option:"
        );
        Assert.assertTrue(getDriver().findElement(By.id("from")).isDisplayed());
    }

    @Test
    public void testIfCopyFromOptionIsNotDisplayed() {
        clickOnNewItemLink();

        Assert.assertTrue(getDriver().findElements(By.id("from")).isEmpty());
    }
}
