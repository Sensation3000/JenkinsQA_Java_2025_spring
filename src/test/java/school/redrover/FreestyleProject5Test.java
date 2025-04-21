package school.redrover;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class FreestyleProject5Test extends BaseTest {
    final String NEW_ITEM_NAME = "NewItemTest";
    final String RENAME_ITEM_NAME = "RenameItemTest";
    WebDriver driver = getDriver();

    @Test
    public void testCreateFreestyleProject() {
        String currentName = new HomePage(getDriver()).
                clickNewItemOnLeftSidePanel().
                sendItemName(NEW_ITEM_NAME).
                selectFreestyleAndClickOk().
                clickSaveButton().getProjectName();

        Assert.assertEquals(currentName, NEW_ITEM_NAME);
    }

    @Test (dependsOnMethods = "testCreateFreestyleProject")
    public void testRenameFreestyleProject() {
        String renameName = new HomePage(getDriver()).
                clickOnJobInListOfItemsOnHP(NEW_ITEM_NAME).
                clickLeftSideMenuRename().
                sendName(RENAME_ITEM_NAME).
                clickRename().getProjectName();

        Assert.assertEquals(renameName, RENAME_ITEM_NAME);
    }
}
