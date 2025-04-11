package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.Arrays;
import java.util.List;

public class JobCreationTest extends BaseTest {

    final String NEW_JOB = "//a[@href='newJob']";
    final String ITEM_NAME_INPUT = "//input[@class='jenkins-input']";

    @Test
    public void testEmptyName() {

        getDriver().findElement(By.xpath(NEW_JOB)).click();
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        WebElement validationMessage = getDriver().findElement(By.id("itemname-required"));

        Assert.assertEquals(validationMessage.getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testInvalidCharactersInItemName() {
        List<String> invalidNames = Arrays.asList("My $#Job!@#", "Test Job#@$", "Job#12$#@3", "My@Job#$", "Job%Test$#");

        for (String invalidName : invalidNames) {
            getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NEW_JOB))).click();
            WebElement itemNameInput = getDriver().findElement(By
                    .xpath(ITEM_NAME_INPUT));
            itemNameInput.clear();
            itemNameInput.sendKeys(invalidName + Keys.ENTER);

            WebElement errorMessage = getWait5().until(ExpectedConditions
                    .visibilityOfElementLocated(By.id("itemname-invalid")));
            Assert.assertTrue(errorMessage.isDisplayed(), "Ошибка не отображается для имени: " + invalidName);
            TestUtils.gotoHomePage(this);
        }
    }

    @Ignore
    @Test
    public void testCreateItemAndNavigateToConfigPage() {
        getDriver().findElement(By.xpath(NEW_JOB)).click();

        WebElement itemNameInput = getDriver().findElement(By
                .xpath(ITEM_NAME_INPUT));
        itemNameInput.sendKeys("new_project_1");
        getDriver().findElement(By.xpath("//span[@class][text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        TestUtils.gotoHomePage(this);
        WebElement projectName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//a[@href='job/new_project_1/']")));

        Assert.assertEquals(projectName.getText(), "new_project_1");
    }

    @Test
    public void testAllItemTypesArePresent() {
        TestUtils.createProject(this);

        List<String> expectedTitles = Arrays.asList(
                "Freestyle project",
                "Pipeline",
                "Multi-configuration project",
                "Folder",
                "Multibranch Pipeline",
                "Organization Folder");

        List<WebElement> actualTitles = Arrays.asList(
                getDriver().findElement(By.xpath("//span[@class][text()='Freestyle project']")),
                getDriver().findElement(By.xpath("//span[@class][text()='Pipeline']")),
                getDriver().findElement(By.xpath("//span[@class][text()='Multi-configuration project']")),
                getDriver().findElement(By.xpath("//span[@class][text()='Folder']")),
                getDriver().findElement(By.xpath("//span[@class][text()='Multibranch Pipeline']")),
                getDriver().findElement(By.xpath("//span[@class][text()='Organization Folder']"))
        );

        for (int i = 0; i < expectedTitles.size(); i++) {
            String expectedText = expectedTitles.get(i);
            String actualText = actualTitles.get(i).getText();
            Assert.assertEquals(actualText, expectedText);
        }
    }

    @Ignore
    @Test(description = "TC_01.003.20")
    public void testNewItemCreation() {
        String projectName = TestUtils.getItemTypeName(1);

        TestUtils.createProjectWithName(getDriver(), projectName, 1);
        TestUtils.gotoHomePage(this);

        TestUtils.createProject(this);
        WebElement actualTextCopyForm = getDriver().findElement(By
                .xpath("//div[@class='add-item-copy']"));
        Assert.assertEquals(actualTextCopyForm.getText().trim(), "Copy from");
    }
    @Test(description = "TC_01.003.21")
    public void testNewItemCopyFromAutocomplete() {
        String projectName = TestUtils.getItemTypeName(1);

        TestUtils.createProjectWithName(getDriver(), projectName, 1);
        TestUtils.gotoHomePage(this);

        TestUtils.createProject(this);
        WebElement actualTextCopyForm = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("from")));
        actualTextCopyForm.sendKeys("Freestyle");

        WebElement autocompleteSuggestion = getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("tippy-7")));
        Assert.assertNotNull(autocompleteSuggestion, "Autocomplete suggestion not found.");
    }
    @Test(description = "TC_01.003.22")
    public void testCopyFromNonExistingItem() {
        String projectName = TestUtils.getItemTypeName(2);

        TestUtils.createProjectWithName(getDriver(), projectName, 2);
        TestUtils.gotoHomePage(this);

        TestUtils.createProject(this);
        WebElement actualTextCopyForm = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("from")));
        actualTextCopyForm.sendKeys("NonExistingItem");
        WebElement actualText = getWait5()
                .until(ExpectedConditions.presenceOfElementLocated(By.id("tippy-7")));
        Assert.assertEquals(actualText.getText(),"No items");
    }
}

