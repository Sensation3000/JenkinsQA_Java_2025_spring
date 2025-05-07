package school.redrover;


import org.testng.Assert;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

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
                .sendItemName(ITEM_NAME)
                .selectFreestyleAndClickOk()
                .getHeader()
                .clickLogoIcon()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME)
                .selectFreestyle();

        Assert.assertEquals(newItemPage.getItemNameInvalidMessage(), "» A job already exists with the name ‘%s’".formatted(ITEM_NAME));
        Assert.assertEquals(newItemPage.getItemNameInvalidMessageColor(), RED_COLOR_ERROR);
    }

    @Test(dataProvider = "itemDescriptions", dataProviderClass = TestDataProvider.class)
    public void testItemsDescriptions(List<String> expectedItemDescriptions) {
        List<String> actualItemDescriptions = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .getJobsDescriptions();

        Assert.assertEquals(actualItemDescriptions, expectedItemDescriptions);
    }

    @Test(dataProvider = "itemTypes", dataProviderClass = TestDataProvider.class)
    public void testIfSelectedItemIsHighlighted(String itemTypeName) {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .clickOnJobItem(itemTypeName);

        Assert.assertTrue(newItemPage.isListItemHighlighted(itemTypeName));
    }

    @Test
    public void testIfCopyFromOptionIsDisplayed() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME)
                .selectFolderAndClickOk()
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
                .sendItemName(ITEM_NAME)
                .enterValueToCopyFromInput(ITEM_NAME_NEXT)
                .getDropdownItemText();

        Assert.assertEquals(actualProjectName, ITEM_NAME_NEXT);
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
}
