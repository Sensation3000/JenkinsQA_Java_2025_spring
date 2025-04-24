package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.component.HeaderComponent;
import school.redrover.page.HomePage;
import school.redrover.page.NewItemPage;

import java.util.Arrays;
import java.util.List;

public class NewJob3Test extends BaseTest {

    @Test
    public void testEmptyName() {
        String expectedError = "» This field cannot be empty, please enter a valid name";

        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel();
        newItemPage.selectFreestyleAndClickOk();
        String actualError = newItemPage.getEmptyNameMessage();

        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void testInvalidCharactersInItemName() {
        List<String> invalidNames = Arrays.asList("My $#Job!@#", "Test Job#@$", "Job#12$#@3", "My@Job#$", "Job%Test$#");

        for (String invalidName : invalidNames) {
            NewItemPage newItemPage = new HomePage(getDriver())
                    .clickNewItemOnLeftSidePanel()
                    .sendItemName(invalidName);

            String actualError = newItemPage.getItemNameInvalidMessage();
            Assert.assertTrue(actualError.contains("is an unsafe"), "Ошибка не отображается для имени: " + invalidName);
            TestUtils.gotoHomePage(this);
        }
    }

    @Test
    public void testCreateItemAndNavigateToConfigPage() {
        new HomePage(getDriver())
                .createJob()
                .enterProjectNameAndSelect("My name", "Pipeline");
        new HeaderComponent(getDriver())
                .goToHomePage();

        String actualNameProject = new HomePage(getDriver()).getNameProject();
        Assert.assertEquals(actualNameProject, "My name");
    }

    @Test
    public void testAllItemTypesArePresent() {
        new HomePage(getDriver()).createJob();

        NewItemPage newItemPage = new NewItemPage(getDriver());

        List<String> expectedTitles = Arrays.asList("Freestyle project",
                "Pipeline",
                "Multi-configuration project",
                "Folder",
                "Multibranch Pipeline",
                "Organization Folder");

        List<String> actualTitles = newItemPage.getAllProjectTypeTitles();

        Assert.assertEquals(actualTitles, expectedTitles);
    }

    @Test
    public void testNewItemCreation() {
        String projectName = TestUtils.getItemTypeName(1);

        TestUtils.createProjectWithName(getDriver(), projectName, 1);
        NewItemPage newItemPage = new HeaderComponent(getDriver())
                .goToHomePage()
                .clickNewItem();

        String actualCopyFromText = newItemPage.getCopyFromText();

        Assert.assertEquals(actualCopyFromText, "Copy from");
    }

    @Test
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

    @Ignore
    @Test
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

