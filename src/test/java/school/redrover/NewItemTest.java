package school.redrover;


import org.testng.Assert;
import school.redrover.page.HomePage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class NewItemTest extends BaseTest {

    private static final String ITEM_NAME = "ItemName";
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
}
