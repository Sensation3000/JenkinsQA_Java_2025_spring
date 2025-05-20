package school.redrover;


import org.testng.Assert;
import org.testng.annotations.Ignore;
import school.redrover.page.HomePage;
import school.redrover.page.folder.FolderConfigurationPage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.Arrays;
import java.util.List;


public class NewItemTest extends BaseTest {

    private static final String ITEM_NAME = "ItemName";
    private static final String ITEM_NAME_NEXT = "ItemName2";
    private static final String RED_COLOR_ERROR = "rgba(230, 0, 31, 1)";

    @Test
    public void testCheckNewItemPageHeader() {
        final String expectedHeaderText = "New Item";

        String actualHeaderText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .getNewItemPageHeaderText();

        Assert.assertEquals(actualHeaderText, expectedHeaderText);
    }

    @Test(dataProvider = "provideInvalidCharacters", dataProviderClass = TestDataProvider.class)
    public void testNameWithInvalidCharactersError(String invalidCharacter) {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME.concat(invalidCharacter))
                .selectFreestyle();

        Assert.assertEquals(newItemPage.getItemNameInvalidMessage(), String.format("» ‘%s’ is an unsafe character", invalidCharacter));
        Assert.assertFalse(newItemPage.isOkButtonEnabled());
    }

    @Test(dataProvider = "itemTypes", dataProviderClass = TestDataProvider.class)
    public void testCreatingWithEmptyName(String itemType) {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .selectItemByName(itemType);

        Assert.assertEquals(newItemPage.getErrorMessageOnEmptyField(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(newItemPage.isOkButtonEnabled(), "Expected OK button to be disabled.");
    }

    @Test
    public void testCheckNameDotError() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(".")
                .selectFreestyle();

        Assert.assertEquals(newItemPage.getItemNameInvalidMessage(), "» “.” is not an allowed name");
        Assert.assertEquals(newItemPage.getItemNameInvalidMessageColor(), RED_COLOR_ERROR);
        Assert.assertFalse(newItemPage.isOkButtonEnabled());
    }

    @Test
    public void testCheckItemNameWithDotEndError() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME.concat("."))
                .selectFreestyle();

        Assert.assertEquals(newItemPage.getItemNameInvalidMessage(), "» A name cannot end with ‘.’");
        Assert.assertEquals(newItemPage.getItemNameInvalidMessageColor(), RED_COLOR_ERROR);
        Assert.assertFalse(newItemPage.isOkButtonEnabled());
    }

    @Test
    public void testDoubleItemNameError() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(ITEM_NAME, FreestyleConfigurationPage.class)
                .getHeader()
                .clickLogoIcon()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME)
                .selectFreestyle();

        Assert.assertEquals(newItemPage.getItemNameInvalidMessage(), "» A job already exists with the name ‘%s’".formatted(ITEM_NAME));
        Assert.assertEquals(newItemPage.getItemNameInvalidMessageColor(), RED_COLOR_ERROR);
    }

    @Test
    public void testItemsTypeList() {
        final List<String> items = Arrays.asList
                ("Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        List<String> listOfJobs = new HomePage(getDriver())
                .clickCreateJob()
                .getItemTypesTextList();

        Assert.assertNotEquals(listOfJobs.size(), 0);
        Assert.assertTrue(items.containsAll(listOfJobs));
    }

    @Test
    public void testItemsTypeDescriptionExist() {
        final List<String> itemsDisc = Arrays.asList
                ("Classic, general-purpose job type that checks out from up to one SCM, executes build steps serially, followed by post-build steps like archiving artifacts and sending email notifications.",
                        "Orchestrates long-running activities that can span multiple build agents. Suitable for building pipelines (formerly known as workflows) and/or organizing complex activities that do not easily fit in free-style job type.",
                        "Suitable for projects that need a large number of different configurations, such as testing on multiple environments, platform-specific builds, etc.",
                        "Creates a container that stores nested items in it. Useful for grouping things together. Unlike view, which is just a filter, a folder creates a separate namespace, so you can have multiple things of the same name as long as they are in different folders.",
                        "Creates a set of Pipeline projects according to detected branches in one SCM repository.",
                        "Creates a set of multibranch project subfolders by scanning for repositories.");

        List<String> listOfJobsDisc = new HomePage(getDriver())
                .clickCreateJob()
                .getJobsDescriptions();

        Assert.assertTrue(listOfJobsDisc.containsAll(itemsDisc));
    }

    @Test
    public void testIfCopyFromOptionIsDisplayed() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(ITEM_NAME, FolderConfigurationPage.class)
                .getHeader()
                .clickLogoIcon()
                .clickNewItemOnLeftSidePanel();

        Assert.assertEquals(newItemPage.getCopyFromFieldText(), "Copy from");
        Assert.assertTrue(newItemPage.isCopyFromOptionInputDisplayed());
    }

    @Test
    public void testIfCopyFromOptionInputIsNotDisplayed() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel();

        Assert.assertFalse(newItemPage.isCopyFromOptionInputDisplayed());
    }

    @Test(dependsOnMethods = "testIfCopyFromOptionIsDisplayed")
    public void testAutocompleteOption() {
        String actualProjectName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME_NEXT)
                .enterValueToCopyFromInput(ITEM_NAME)
                .getDropdownItemText();

        Assert.assertEquals(actualProjectName, ITEM_NAME);
    }

    @Test(dependsOnMethods = "testIfCopyFromOptionIsDisplayed")
    public void testIfNoItemsMessageIsDisplayed() {
        String noItemsMessage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME)
                .enterValueToCopyFromInput(ITEM_NAME_NEXT.concat("a"))
                .getDropdownItemText();

        Assert.assertEquals(noItemsMessage, "No items");
    }

    @Test(dependsOnMethods = "testIfCopyFromOptionIsDisplayed")
    public void testIfUserRedirectedToErrorPage() {
        String errorPageHeading = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME)
                .enterValueToCopyFromInput(ITEM_NAME_NEXT.concat("a"))
                .clickOkButtonWithError()
                .getTitle();

        Assert.assertEquals(errorPageHeading, "Error");
    }

    @Test
    public void testNewItemPageAvailableFromDashboard() {
        final String PAGE_HEADER_TEXT = "New Item";
        final String NEW_ITEM_PAGE_URL = "http://localhost:8080/view/all/newJob";

        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel();

        Assert.assertEquals(newItemPage.getNewItemPageHeaderText(), PAGE_HEADER_TEXT);
        Assert.assertEquals(newItemPage.getNewItemPageURL(), NEW_ITEM_PAGE_URL);
    }

    @Ignore
    @Test(dataProvider = "safeCharacters", dataProviderClass = TestDataProvider.class)
    public void testCreateItemNameWithSafeCharacters(String safeCharacter) {
        boolean isUnsafeCharacterMessageDisplayed = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(safeCharacter)
                .isUnsafeCharacterMessageDisplayed();

        Assert.assertFalse(isUnsafeCharacterMessageDisplayed);
    }
}
