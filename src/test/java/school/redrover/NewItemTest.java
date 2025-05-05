package school.redrover;


import org.testng.Assert;
import school.redrover.page.HomePage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class NewItemTest extends BaseTest {

    private static final String ITEM_NAME = "ItemName";

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

        Assert.assertEquals(newItemPage.getErrorMessageText(), String.format("» ‘%s’ is an unsafe character", invalidCharacter));
        Assert.assertFalse(newItemPage.isOkButtonEnabled());
    }

    @Test(dataProvider = "itemTypes", dataProviderClass = TestDataProvider.class)
    public void testOKButtonIsDisabledIfCreatingProjectWithEmptyName(String itemType) {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .selectItemByName(itemType);

        Assert.assertEquals(newItemPage.getErrorMessageOnEmptyField(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(newItemPage.isOkButtonEnabled(), "Expected OK button to be disabled.");
    }

    @Test (dataProvider = "itemTypes", dataProviderClass = TestDataProvider.class)
    public void testCheckItemsTypes(String expectedItemTypeText) {

        String actualItemTypeText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .getItemTypeText(expectedItemTypeText);

        Assert.assertEquals(actualItemTypeText, expectedItemTypeText, "Error!");
    }
}
