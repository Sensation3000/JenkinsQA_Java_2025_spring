package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;


public class FolderTest2 extends BaseTest {

    @Test
    public void testCreateFolder() {
        final String FOLDER_NAME = "Folder Test Name";
        final String DISPLAYED_NAME = "This is displayed name";
        final String DESCRIPTION = "This is folder description";

        String currentDisplayedName = (new HomePage(getDriver())
        .clickNewItemOnLeftSidePanel()
        .sendItemName(FOLDER_NAME)
        .selectFolderAndClickOk()
        .sendDisplayName(DISPLAYED_NAME)
        .sendDescription(DESCRIPTION)
        .clickSaveButton()
        .clickLogo())
        .getCurrentDisplayedName();

        Assert.assertEquals(currentDisplayedName, DISPLAYED_NAME);
    }
}
