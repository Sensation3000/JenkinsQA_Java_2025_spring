package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class NewFolderTest extends BaseTest {

    @Test
    public void testFolderName() {

        String folderText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName("Test")
                .selectFolderAndClickOk()
                .clickSave()
                .getProjectName();

        Assert.assertEquals(folderText, "Test");
    }

    @Test (dependsOnMethods = "testFolderName")
    public void FolderIsEmptyTest() {

        String folderStatus = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName("Test1")
                .selectFolderAndClickOk()
                .clickSave()
                .getFolderStatus();

        Assert.assertEquals(folderStatus, "This folder is empty");
    }
}