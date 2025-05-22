package school.redrover;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import school.redrover.component.SideMenuInFolderComponent;
import school.redrover.page.HomePage;
import school.redrover.page.folder.FolderConfigurationPage;
import school.redrover.page.folder.FolderProjectPage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "ProjectFolder";
    private static final String FOLDER_SECOND_NAME = "ProjectFolder2";
    private static final String FOLDER_DISPLAY_NAME = "Folder Display Name";
    private static final String HEALTH_METRICS = "Health metrics";
    private static final String ITEM_NAME = "Test Folder";
    private static final String DESCRIPTION = "Test description";
    private static final String SUB_FOLDER_NAME = "SubFolder";
    private static final String RENAMED_FOLDER_NAME = "Renamed folder";

    @Test(dataProvider = "projectNames", dataProviderClass = TestDataProvider.class)
    public void  testCreateWithValidName(String folderName) {
        List<String> jobs = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(folderName, FolderConfigurationPage.class)
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
                .createNewItem(FOLDER_NAME, FolderConfigurationPage.class)
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
    public void testCreateFreestyleProjectInFolderUsingCreateNewJob() {
        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnCreateJobButton()
                .createNewItem(ITEM_NAME, FolderConfigurationPage.class)
                .getHeader()
                .clickLogoIcon()
                .clickOnJobInListOfItems(FOLDER_NAME, new FolderProjectPage(getDriver()));

        Assert.assertEquals(folderProjectPage.getProjectNameList().get(0), ITEM_NAME);
        Assert.assertEquals(folderProjectPage.getProjectNameList().size(), 1);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectInFolderUsingCreateNewJob")
    public void testIfTwoDifferentFoldersCanHoldItemsWithTheSameNames() {
        String folderProjectName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(FOLDER_SECOND_NAME, FolderConfigurationPage.class)
                .getHeader()
                .clickLogoIcon()
                .clickOnNewItemLinkWithChevron(FOLDER_SECOND_NAME)
                .createNewItem(ITEM_NAME, FolderConfigurationPage.class)
                .clickSave()
                .getProjectName();

        Assert.assertEquals(folderProjectName, ITEM_NAME);
    }

    @Test
    public void testIfCopyFromFieldAppears() {
        final String expectedText = "Copy from";

        String copyFromFieldText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(FOLDER_NAME, FolderConfigurationPage.class)
                .getHeader()
                .clickLogoIcon()
                .clickNewItemOnLeftSidePanel()
                .getCopyFromFieldText();

        Assert.assertEquals(copyFromFieldText, expectedText);
    }

    @Test
    public void testCreateWithDescription () {
        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(FOLDER_NAME, FolderConfigurationPage.class)
                .sendDescription(DESCRIPTION)
                .clickSave();

        Assert.assertEquals(folderProjectPage.getProjectName(),FOLDER_NAME);
        Assert.assertEquals(folderProjectPage.getDescription(),DESCRIPTION);
    }

    @Test(dependsOnMethods = "testCreateWithDescription")
    public void testRenameFolder() {
        String folderName = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickRenameOnLeftSidePanel(FOLDER_NAME)
                .sendNewName(RENAMED_FOLDER_NAME)
                .clickRenameButton()
                .getProjectName();

        Assert.assertEquals(folderName, RENAMED_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testRenameFolder")
    public void testCreateFolderInFolderUsingCreateNewJob() {
        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(RENAMED_FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnCreateJobButton()
                .createNewItem(ITEM_NAME, FolderConfigurationPage.class)
                .getHeader()
                .clickLogoIcon()
                .clickOnJobInListOfItems(RENAMED_FOLDER_NAME, new FolderProjectPage(getDriver()));

        Assert.assertEquals(folderProjectPage.getProjectNameList().size(), 1);
        Assert.assertEquals(folderProjectPage.getProjectNameList().get(0), ITEM_NAME);
        Assert.assertEquals(folderProjectPage.getItemIconTitleByJobName(ITEM_NAME), "Folder");
    }

    @Test(dependsOnMethods = "testCreateNewFolder")
    public void testCreatingSubFolderInFolderUsingNewItem() {
        String subFolder = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME, new SideMenuInFolderComponent(getDriver()))
                .clickItemOnSidePanel("New Item",new NewItemPage(getDriver()))
                .sendItemName(SUB_FOLDER_NAME)
                .selectFolderAndClickOk()
                .clickSave()
                .getHeader()
                .clickLogoIcon()
                .clickOnJobInListOfItems(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .getSubFolderName();

        Assert.assertEquals(subFolder, SUB_FOLDER_NAME);
    }

    @Test
    public void  testAvailabilityHealthMetrics(){
        FolderConfigurationPage folderConfigurationPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(ITEM_NAME, FolderConfigurationPage.class)
                .clickHealthMetrics();

        List<String> titlesHealthMetrics = List.of(
                folderConfigurationPage.getTitleHealthMetrics(),
                folderConfigurationPage.getTextDropdownHealthMetrics());

        for (String titleHealthMetrics : titlesHealthMetrics) {

            Assert.assertEquals(titleHealthMetrics, HEALTH_METRICS);
        }
    }

    @Test
    public void testFolderIsEmpty() {
        String folderStatus = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(FOLDER_NAME, FolderConfigurationPage.class)
                .clickSave()
                .getFolderStatus();

        Assert.assertEquals(folderStatus, "This folder is empty");
    }

    @Test
    public void testQuestionMarkIcon() {
        boolean isButtonExist = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(FOLDER_NAME, FolderConfigurationPage.class)
                .isQuestionMarkIconEnabled();

        Assert.assertTrue(isButtonExist);
    }

    @Test(dependsOnMethods = "testQuestionMarkIcon")
    public void testAddDescriptionButton() {
        String descriptionText = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME,new FolderConfigurationPage(getDriver()))
                .clickAddDescriptionButton()
                .fillInDescriptionBox(DESCRIPTION)
                .clickSave()
                .getDescriptionSecondLine();

       Assert.assertEquals(descriptionText, DESCRIPTION);
    }
    
    @Test
    public void testEmptyDescriptionBoxUsingApplyButton() {
        String descriptionText = new HomePage(getDriver())
                .getSideMenuInHomePage()
                .clickItemOnSidePanelInHomePage("New Item",new NewItemPage(getDriver()))
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .clickApplyForSavedNotification();

        Assert.assertEquals(descriptionText, "Saved");
    }

    @Test
    public void testCreateNewFolder() {
        String currentName = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(FOLDER_NAME, FolderConfigurationPage.class)
                .clickSave()
                .getProjectName();

        Assert.assertEquals(currentName, FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateNewFolder")
        public void testLeavePageWithoutSavings() {

        boolean isAlertPresent = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME, new SideMenuInFolderComponent(getDriver()))
                .clickItemOnSidePanel("Configure", new FolderConfigurationPage(getDriver()))
                .clearDisplayName()
                .sendDisplayName(FOLDER_SECOND_NAME+"GGG")
                .getHeader()
                .goToHomePage()
                .isAlertPresent();

        Assert.assertFalse(isAlertPresent);
    }

    @Test(dependsOnMethods = "testCreateNewFolder")
    public void testSaveButtonExist() {
        boolean isSaveButton = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME, new SideMenuInFolderComponent(getDriver()))
                .clickItemOnSidePanel("Configure", new FolderConfigurationPage(getDriver()))
                .isSaveButtonEnabled();

        Assert.assertTrue(isSaveButton);
    }

    @Test(dependsOnMethods = "testSaveButtonExist")
    public void testApplyButtonExist() {
        boolean isApplyButton = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME, new SideMenuInFolderComponent(getDriver()))
                .clickItemOnSidePanel("Configure", new FolderConfigurationPage(getDriver()))
                .isApplyButtonEnabled();

        Assert.assertTrue(isApplyButton);
    }
}
