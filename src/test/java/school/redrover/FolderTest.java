package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.folder.FolderProjectPage;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "ProjectFolder";
    private static final String FOLDER_DISPLAY_NAME = "Folder Display Name";
    private static final String ITEM_NAME = "Item1";

    @Test(dataProvider = "projectNames", dataProviderClass = TestDataProvider.class)
    public void  testCreateWithValidName(String folderName) {

        List<String> jobs = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(folderName)
                .selectFolderAndClickOk()
                .getHeader()
                .clickLogoIcon()
                .getProjectNameList();

        Assert.assertEquals(jobs.size(), 1);
        Assert.assertEquals(jobs.get(0), folderName);
    }

    @Test
    public void testCreateWithDisplayName() {
        String actualDisplayName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .sendDisplayName(FOLDER_DISPLAY_NAME)
                .clickSave()
                .getProjectName();

        Assert.assertEquals(actualDisplayName, FOLDER_DISPLAY_NAME);
    }

    @Test(dependsOnMethods = "testCreateWithDisplayName")
    public void testDisplayNameCanBeEmpty() {
        String displayedFolderName = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_DISPLAY_NAME, new FolderProjectPage(getDriver()))
                .clickConfigure()
                .clearDisplayName()
                .clickSave()
                .getHeader()
                .clickLogoIcon()
                .getProjectName();

        Assert.assertEquals(displayedFolderName, FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testDisplayNameCanBeEmpty")
    public void testCreateFreestyleProjectInFolder() {

        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnNewItemButton()
                .sendItemName(ITEM_NAME)
                .selectFreestyleAndClickOk()
                .getHeader()
                .clickLogoIcon()
                .clickOnJobInListOfItems(FOLDER_NAME, new FolderProjectPage(getDriver()));

        Assert.assertEquals(folderProjectPage.getProjectNameList().get(0), ITEM_NAME);
        Assert.assertEquals(folderProjectPage.getProjectNameList().size(), 1);
    }

    @Test
    public void testIfCopyFromFieldAppears() {
        final String expectedText = "Copy from";

        String copyFromFieldText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .getHeader()
                .clickLogoIcon()
                .clickNewItemOnLeftSidePanel()
                .getCopyFromFieldText();

        Assert.assertEquals(copyFromFieldText, expectedText);
    }

    @Test
    public void testCreateWithDescription () {
        final String descriptionName = "folder";

        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .sendDescription(descriptionName)
                .clickSave();

        Assert.assertEquals(folderProjectPage.getProjectName(),FOLDER_NAME);
        Assert.assertEquals(folderProjectPage.getDescription(),descriptionName);
    }

    @Test(dependsOnMethods = "testCreateWithDescription")
    public void testCreateFolderInFolder() {

        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnNewItemButton()
                .sendItemName(ITEM_NAME)
                .selectFolderAndClickOk()
                .getHeader()
                .clickLogoIcon()
                .clickOnJobInListOfItems(FOLDER_NAME, new FolderProjectPage(getDriver()));

        Assert.assertEquals(folderProjectPage.getProjectNameList().size(), 1);
        Assert.assertEquals(folderProjectPage.getProjectNameList().get(0), ITEM_NAME);
        Assert.assertEquals(folderProjectPage.getItemIconTitleByJobName(ITEM_NAME), "Folder");
    }
}
