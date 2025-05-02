package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class NewItemPage2Test extends BaseTest {
    private Actions actions;
    HomePage homePage;
    String projectName;

    @BeforeMethod
    void setUp() {
        homePage = new HomePage(getDriver());

        if (getDriver() != null) {
            actions = new Actions(getDriver());
        } else {
            throw new IllegalStateException("WebDriver is null. Cannot initialize Actions.");
        }
    }

    private void openDropdownMenuForJob(String itemName) {
        String buttonSelector = String.format("button[data-href*='%s']", itemName);
        WebElement dropdownChevron = getDriver().findElement(By.cssSelector(buttonSelector));
        TestUtils.moveAndClickWithJS(getDriver(), dropdownChevron);
    }

    private void createNewJob(String name, String itemTypeName) {
        getDriver().findElement(By.id("name")).sendKeys(name);
        WebElement itemType = getDriver().findElement(By.xpath(String.format("//span[text()='%s']", itemTypeName)));
        TestUtils.scrollAndClickWithJS(getDriver(), itemType);
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.id("ok-button")));
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.name("Submit")));
    }

    @Test
    public void createNewFolderProject() {
        projectName = TestUtils.generateRandomAlphanumeric();

        TestUtils.newItemCreate(this, projectName, 4);

        Assert.assertEquals(homePage.getProjectName(), projectName);
    }

    @Test
    public void testIfPageIsAccessibleFromHomePage() {
        NewItemPage newItemPage = new NewItemPage(getDriver());

        homePage.clickNewItemOnLeftSidePanel();

        Assert.assertTrue(newItemPage.getNewItemPageURL().contains("/view/all/newJob"));
        Assert.assertEquals(newItemPage.getNewItemPageHeaderText(),"New Item");
    }

    @Test
    public void testInputPositiveValidation() {
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric() + "_";

        String inputValue = homePage.clickNewItemOnLeftSidePanel()
                                    .sendItemName(randomAlphaNumericValue)
                                    .getInputValue();

        Assert.assertEquals(inputValue, randomAlphaNumericValue);
    }

    @Test
    public void testIfAvailableJobsAreDisplayedOnThePage() {
        List<String> expectedLabels = List.of(
                "Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline",
                "Organization Folder"
        );

        Assert.assertEquals(homePage.clickNewItemOnLeftSidePanel().getAllItemsTypesLabels(),
                            expectedLabels
        );
    }

    @Test(dataProvider = "itemDescriptions", dataProviderClass = TestDataProvider.class)
    public void testItemsDescriptions(List<String> expectedItemDescriptions) {
        List<String> actualItemDescriptions = homePage.clickNewItemOnLeftSidePanel().getJobsDescriptions();

        Assert.assertEquals(actualItemDescriptions, expectedItemDescriptions);
    }

    @Test(dataProvider = "itemTypes", dataProviderClass = TestDataProvider.class)
    public void testIfSelectedItemIsHighlighted(String itemTypeName) {
        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel().clickOnJobItem(itemTypeName);

        Assert.assertTrue(newItemPage.isListItemHighlighted(itemTypeName));
    }

    @Test(dataProvider = "itemTypes", dataProviderClass = TestDataProvider.class)
    public void testOkButtonWhenFieldIsEmpty(String itemTypeName) {
        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel().clickOnJobItem(itemTypeName);

        Assert.assertFalse(newItemPage.isOkButtonEnabled());
    }

    @Test
    public void testIfErrorMessageIsDisplayedWhenFieldIsEmpty() {
        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel().selectFreestyle();

        Assert.assertEquals(
                         newItemPage.getErrorMessageOnEmptyField(),
                "» This field cannot be empty, please enter a valid name"
        );
    }

    @Test(dependsOnMethods = "createNewFolderProject")
    public void testIfCopyFromOptionIsDisplayed() {
        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel();

        Assert.assertEquals(newItemPage.getCopyFromFieldText(), "Copy from");
        Assert.assertTrue(newItemPage.isCopyFromOptionInputDisplayed());
    }

    @Test
    public void testIfCopyFromOptionInputIsNotDisplayed() {
        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel();

        Assert.assertFalse(newItemPage.isCopyFromOptionInputDisplayed());
    }

    @Test(dependsOnMethods = "createNewFolderProject")
    public void testAutocompleteOption() {
        String actualProjectName = homePage.clickNewItemOnLeftSidePanel()
                                           .sendItemName(TestUtils.generateRandomAlphanumeric())
                                           .enterValueToCopyFromInput(projectName)
                                           .getDropdownItemText();

        Assert.assertEquals(actualProjectName, projectName);
    }

    @Ignore
    @Test(dependsOnMethods = "createNewFolderProject")
    public void testIfNoItemsMessageIsDisplayed() {
        String noItemsMessage = homePage.clickNewItemOnLeftSidePanel()
                                          .sendItemName(TestUtils.generateRandomAlphanumeric())
                                          .enterValueToCopyFromInput(TestUtils.generateRandomAlphanumeric() + "_")
                                          .getDropdownItemText();

        Assert.assertEquals(noItemsMessage, "No items");
    }

    @Test(dependsOnMethods = "createNewFolderProject")
    public void testCopyFromOptionWhenCreatingNewJob() {
        String headingText =
                homePage.clickNewItemOnLeftSidePanel()
                        .sendItemName(TestUtils.generateRandomAlphanumeric())
                        .enterValueToCopyFromInput(projectName)
                        .redirectToMultiConfigurationConfigurePage()
                        .getHeadingText();

        Assert.assertEquals(headingText, "General");
    }

    @Test(dependsOnMethods = "createNewFolderProject")
    public void testIfUserRedirectedToErrorPage() {
        String errorPageHeading =
                homePage.clickNewItemOnLeftSidePanel()
                        .sendItemName(TestUtils.generateRandomAlphanumeric())
                        .enterValueToCopyFromInput(TestUtils.generateRandomAlphanumeric())
                        .redirectToErrorPage()
                        .getTitle();

        Assert.assertEquals(errorPageHeading, "Error");
    }

    @Test
    public void testIfOriginalItemConfigurationIsCopied() {
        projectName = TestUtils.generateRandomAlphanumeric();

        MultiConfigurationConfigurePage multiConfigurationConfigurePage =
                homePage.clickNewItemOnLeftSidePanel()
                        .sendItemName(projectName)
                        .selectMultiConfigurationAndClickOk()
                        .scrollToEnvironmentSectionWithJS()
                        .checkEnvironmentCheckboxesAndClickOnSaveButton()
                        .getHeader()
                        .goToHomePage()
                        .clickNewItemOnLeftSidePanel()
                        .sendItemName(TestUtils.generateRandomAlphanumeric())
                        .enterValueToCopyFromInput(projectName)
                        .redirectToMultiConfigurationConfigurePage()
                        .scrollToEnvironmentSectionWithJS();

        Assert.assertTrue(multiConfigurationConfigurePage.verifyIfAllEnvironmentCheckboxesAreSelected());
    }

    @Test(dependsOnMethods = "createNewFolderProject")
    public void testJobCreationWithinFolder() {
        String actualProjectName = homePage.clickOnNewItemLinkWithChevron()
                        .sendItemName(projectName)
                        .selectFolderAndClickOkWithJS()
                        .clickSave()
                        .getProjectName();

        Assert.assertEquals(actualProjectName, projectName);
    }

    @Test(dataProvider = "itemTypes", dataProviderClass = TestDataProvider.class)
    public void testIf(String itemTypeName) {
        String firstFolderProjectName = TestUtils.generateRandomAlphanumeric();
        String secondFolderProjectName = TestUtils.generateRandomAlphanumeric();
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric();

        TestUtils.newItemCreate(this, firstFolderProjectName, 4);

        String firstFolderProjectLink = String.format("a[href*='%s'].jenkins-table__link", firstFolderProjectName);
        actions.moveToElement(getDriver().findElement(By.cssSelector(firstFolderProjectLink))).perform();

        openDropdownMenuForJob(firstFolderProjectName);
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector(".jenkins-dropdown a[href$='/newJob'")));

        createNewJob(randomAlphaNumericValue, itemTypeName);
        TestUtils.gotoHomePage(getDriver());

        TestUtils.newItemCreate(this, secondFolderProjectName, 4);

        String secondFolderProjectLink  = String.format("a[href*='%s'].jenkins-table__link", secondFolderProjectName);
        actions.moveToElement(getDriver().findElement(By.cssSelector(secondFolderProjectLink))).perform();

        openDropdownMenuForJob(secondFolderProjectName);
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector(".jenkins-dropdown a[href$='/newJob'")));

        createNewJob(randomAlphaNumericValue, "Freestyle project");

        Assert.assertEquals(getDriver().findElement(By.className("page-headline")).getText(), randomAlphaNumericValue);
    }
}
