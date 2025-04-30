package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.error.ErrorPage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class NewItemPage2Test extends BaseTest {
    private Actions actions;
    HomePage homePage;

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
    public void testIfPageIsAccessibleFromHomePage() {
        NewItemPage newItemPage = new NewItemPage(getDriver());

        homePage.clickNewItemOnLeftSidePanel();

        Assert.assertTrue(newItemPage.getNewItemPageURL().contains("/view/all/newJob"));
        Assert.assertEquals(newItemPage.getNewItemPageHeaderText(),"New Item");
    }

    @Test
    public void testInputPositiveValidation() {
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric() + "_";

        homePage.clickNewItemOnLeftSidePanel()
                .sendItemName(randomAlphaNumericValue);

        Assert.assertEquals(randomAlphaNumericValue, new NewItemPage(getDriver()).getInputValue());
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

    @Test
    public void testIfCopyFromOptionIsDisplayed() {
        TestUtils.newItemCreate(this, TestUtils.generateRandomAlphanumeric(), TestUtils.generateRandomNumberWithin1And6());

        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel();

        Assert.assertEquals(newItemPage.getCopyFromFieldText(), "Copy from");
        Assert.assertTrue(newItemPage.isCopyFromOptionInputDisplayed());
    }

    @Test
    public void testIfCopyFromOptionInputIsNotDisplayed() {
        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel();

        Assert.assertFalse(newItemPage.isCopyFromOptionInputDisplayed());
    }

    @Test
    public void testAutocompleteOption() {
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric();
        TestUtils.newItemCreate(this, randomAlphaNumericValue, TestUtils.generateRandomNumberWithin1And6());

        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel()
                                          .enterValueToCopyFromInput(randomAlphaNumericValue);

        Assert.assertEquals(newItemPage.getDropdownItemText(), randomAlphaNumericValue);
    }

    @Test
    public void testIfNoItemsMessageIsDisplayed() {
        TestUtils.newItemCreate(this, TestUtils.generateRandomAlphanumeric(), TestUtils.generateRandomNumberWithin1And6());

        NewItemPage newItemPage = homePage.clickNewItemOnLeftSidePanel()
                                          .enterValueToCopyFromInput(TestUtils.generateRandomAlphanumeric() + "_");

        Assert.assertEquals(newItemPage.getDropdownItemText(), "No items");
    }

    @Test
    public void testCopyFromOptionWhenCreatingNewJob() {
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric();

        TestUtils.newItemCreate(this, randomAlphaNumericValue, 3);

        MultiConfigurationConfigurePage multiConfigurationConfigurePage =
                homePage.clickNewItemOnLeftSidePanel()
                        .enterValueToCopyFromInput(randomAlphaNumericValue)
                        .redirectToMultiConfigurationConfigurePage();

        Assert.assertEquals(multiConfigurationConfigurePage.getHeadingText(), "General");
    }

    @Ignore//NewItemPage2Test.testIfUserRedirectedToErrorPage:180 » Timeout Expected condition failed: waiting for element to be clickable: By.cssSelector: .jenkins-dropdown.jenkins-dropdown--compact https://github.com/RedRoverSchool/JenkinsQA_Java_2025_spring/actions/runs/14733047885/job/41351971771?pr=1586
    @Test
    public void testIfUserRedirectedToErrorPage() {
        TestUtils.newItemCreate(this, TestUtils.generateRandomAlphanumeric(), TestUtils.generateRandomNumberWithin1And6());

        ErrorPage errorPage =
                homePage.clickNewItemOnLeftSidePanel()
                        .enterValueToCopyFromInput(TestUtils.generateRandomAlphanumeric())
                        .redirectToErrorPage();

        Assert.assertEquals(errorPage.getTitle(), "Error");
    }

    @Test
    public void testIfOriginalItemConfigurationIsCopied() {
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric();

        MultiConfigurationConfigurePage multiConfigurationConfigurePage =
                homePage.clickNewItemOnLeftSidePanel()
                        .sendItemName(randomAlphaNumericValue)
                        .selectMultiConfigurationAndClickOk()
                        .scrollToEnvironmentSectionWithJS()
                        .checkEnvironmentCheckboxesAndClickOnSaveButton()
                        .getHeader()
                        .goToHomePage()
                        .clickNewItemOnLeftSidePanel()
                        .enterValueToCopyFromInput(randomAlphaNumericValue)
                        .redirectToMultiConfigurationConfigurePage()
                        .scrollToEnvironmentSectionWithJS();

        Assert.assertTrue(multiConfigurationConfigurePage.verifyIfAllEnvironmentCheckboxesAreSelected());
    }

    @Test(dataProvider = "itemTypes", dataProviderClass = TestDataProvider.class)
    public void testJobCreationWithinFolder(String itemTypeName) {
        String randomAlphaNumericValue = TestUtils.generateRandomAlphanumeric();

        TestUtils.newItemCreate(this, TestUtils.generateRandomAlphanumeric(), 4);

        WebElement jobTableLink = getDriver().findElement(By.cssSelector("a[href*='job'].jenkins-table__link"));
        String projectName = jobTableLink.getText();
        actions.moveToElement(jobTableLink).perform();

        TestUtils.moveAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector(".jenkins-table__link .jenkins-menu-dropdown-chevron")));
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector(".jenkins-dropdown a[href$='/newJob'")));

        createNewJob(randomAlphaNumericValue, itemTypeName);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link")));
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector(String.format("a[href='/job/%s/']", projectName))));

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("a.jenkins-table__link span")).getText(),
                randomAlphaNumericValue
        );
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
