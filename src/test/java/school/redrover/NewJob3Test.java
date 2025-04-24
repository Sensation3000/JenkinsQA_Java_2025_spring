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

    @Ignore
    @Test
    public void testCreateItemAndNavigateToConfigPage() {
        NewItemPage NewItemPage = new HomePage(getDriver())
                .createJob()
                .enterProjectNameAndSelect("My name", "Pipeline");
        TestUtils.gotoHomePage(this);

        String actualNameProject = new HomePage(getDriver()).getNameProject();
        Assert.assertEquals(actualNameProject, "My name");
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
    @Test
    public void testNewItemCreation() {
        String projectName = TestUtils.getItemTypeName(1);

        TestUtils.createProjectWithName(getDriver(), projectName, 1);
        TestUtils.gotoHomePage(this);

        TestUtils.createProject(this);
        WebElement actualTextCopyForm = getDriver().findElement(By
                .xpath("//div[@class='add-item-copy']"));
        Assert.assertEquals(actualTextCopyForm.getText().trim(), "Copy from");
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

