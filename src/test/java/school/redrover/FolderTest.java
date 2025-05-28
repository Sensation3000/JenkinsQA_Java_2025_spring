package school.redrover;

import org.testng.Assert;
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

    private static final String FOLDER_NAME_1 = "ProjectFolder1";
    private static final String FOLDER_NAME_2 = "ProjectFolder2";
    private static final String SUB_FOLDER_NAME = "SubFolder";
    private static final String HEALTH_METRICS = "Health metrics";
    private static final String DESCRIPTION = "Test description";

    @Test
    public void testEmptyDescriptionBoxUsingApplyButton() {
        String descriptionText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(FOLDER_NAME_1, FolderConfigurationPage.class)
                .clickApplyForSavedNotification();

        Assert.assertEquals(descriptionText, "Saved");
    }

    @Test(dependsOnMethods = "testEmptyDescriptionBoxUsingApplyButton")
    public void testFolderIsEmpty() {
        String folderStatus = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new FolderProjectPage(getDriver()))
                .getFolderStatus();

        Assert.assertEquals(folderStatus, "This folder is empty");
    }

    @Test(dependsOnMethods = "testFolderIsEmpty")
    public void testIfCopyFromFieldAppears() {
        final String expectedText = "Copy from";

        String copyFromFieldText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .getCopyFromFieldText();

        Assert.assertEquals(copyFromFieldText, expectedText);
    }

    @Test(dependsOnMethods = "testIfCopyFromFieldAppears")
    public void testQuestionMarkIcon() {
        boolean isButtonExist = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new FolderProjectPage(getDriver()))
                .clickConfigure()
                .isQuestionMarkIconEnabled();

        Assert.assertTrue(isButtonExist);
    }

    @Test(dependsOnMethods = "testQuestionMarkIcon")
    public void testAddDescriptionButton() {
        String descriptionText = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1,new FolderConfigurationPage(getDriver()))
                .clickAddDescriptionButton()
                .fillInDescriptionBox(DESCRIPTION)
                .clickSave()
                .getDescriptionSecondLine();

        Assert.assertEquals(descriptionText, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testAddDescriptionButton")
    public void testCreateNewFolder() {
        String currentName = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new FolderProjectPage(getDriver()))
                .getProjectName();

        Assert.assertEquals(currentName, FOLDER_NAME_1);
    }

    @Test(dependsOnMethods = "testCreateNewFolder")
    public void testAvailabilityHealthMetrics() {
        List<String> getAllHealthMetricsTitles= new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new FolderProjectPage(getDriver()))
                .clickConfigure()
                .clickHealthMetrics()
                .getAllHealthMetricsTitles();

        Assert.assertEquals(getAllHealthMetricsTitles, List.of(HEALTH_METRICS, HEALTH_METRICS));
    }

    @Test(dependsOnMethods = "testAvailabilityHealthMetrics")
    public void testCreatingSubFolderInFolderUsingNewItem() {
        String subFolder = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new SideMenuInFolderComponent(getDriver()))
                .clickItemOnSidePanel("New Item",new NewItemPage(getDriver()))
                .createNewItem(SUB_FOLDER_NAME, FolderConfigurationPage.class)
                .clickSave()
                .getHeader()
                .clickLogoIcon()
                .clickOnJobInListOfItems(FOLDER_NAME_1, new FolderProjectPage(getDriver()))
                .getSubFolderName();

        Assert.assertEquals(subFolder, SUB_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreatingSubFolderInFolderUsingNewItem")
    public void testLeavePageWithoutSavings() {
        boolean isAlertPresent = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new SideMenuInFolderComponent(getDriver()))
                .clickItemOnSidePanel("Configure", new FolderConfigurationPage(getDriver()))
                .clearDisplayName()
                .sendDisplayName(FOLDER_NAME_2 +"GGG")
                .getHeader()
                .goToHomePage()
                .isAlertPresent();

        Assert.assertFalse(isAlertPresent);
    }

    @Test(dependsOnMethods = "testLeavePageWithoutSavings")
    public void testSaveButtonExist() {
        boolean isSaveButton = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new SideMenuInFolderComponent(getDriver()))
                .clickItemOnSidePanel("Configure", new FolderConfigurationPage(getDriver()))
                .isSaveButtonEnabled();

        Assert.assertTrue(isSaveButton);
    }

    @Test(dependsOnMethods = "testSaveButtonExist")
    public void testApplyButtonExist() {
        boolean isApplyButton = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new SideMenuInFolderComponent(getDriver()))
                .clickItemOnSidePanel("Configure", new FolderConfigurationPage(getDriver()))
                .isApplyButtonEnabled();

        Assert.assertTrue(isApplyButton);
    }

    @Test(dependsOnMethods = "testApplyButtonExist")
    public void testCreateWithDescription () {
        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new FolderProjectPage(getDriver()))
                .clickConfigure()
                .sendDescription(DESCRIPTION)
                .clickSave();

        Assert.assertEquals(folderProjectPage.getProjectName(), FOLDER_NAME_1);
        Assert.assertEquals(folderProjectPage.getDescription(),DESCRIPTION);
    }

    @Test(dependsOnMethods = "testCreateWithDescription")
    public void testRenameFolder() {
        String folderName = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new FolderProjectPage(getDriver()))
                .clickRenameOnLeftSidePanel(FOLDER_NAME_1)
                .sendNewName(FOLDER_NAME_2)
                .clickRenameButton()
                .getProjectName();

        Assert.assertEquals(folderName, FOLDER_NAME_2);
    }

    @Test(dependsOnMethods = "testRenameFolder")
    public void testCreateFolderInFolderUsingCreateNewJob() {
        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_2, new FolderProjectPage(getDriver()));

        Assert.assertEquals(folderProjectPage.getProjectNameList().size(), 1);
        Assert.assertEquals(folderProjectPage.getProjectNameList().get(0), SUB_FOLDER_NAME);
        Assert.assertEquals(folderProjectPage.getItemIconTitleByJobName(SUB_FOLDER_NAME), "Folder");
    }

    @Test(dependsOnMethods = "testCreateFolderInFolderUsingCreateNewJob")
    public void testCreateWithDisplayName() {
        String actualDisplayName = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_2, new FolderProjectPage(getDriver()))
                .clickConfigure()
                .sendDisplayName(FOLDER_NAME_1)
                .clickSave()
                .getProjectName();

        Assert.assertEquals(actualDisplayName, FOLDER_NAME_1);
    }

    @Test(dependsOnMethods = "testCreateWithDisplayName")
    public void testDisplayNameCanBeEmpty() {
        String displayedFolderName = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_1, new FolderProjectPage(getDriver()))
                .clickConfigure()
                .clearDisplayName()
                .clickSave()
                .getProjectName();

        Assert.assertEquals(displayedFolderName, FOLDER_NAME_2);
    }

    @Test(dependsOnMethods = "testDisplayNameCanBeEmpty")
    public void testCreateFreestyleProjectInFolderUsingCreateNewJob() {
        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(FOLDER_NAME_2, new FolderProjectPage(getDriver()));

        Assert.assertEquals(folderProjectPage.getProjectNameList().get(0), SUB_FOLDER_NAME);
        Assert.assertEquals(folderProjectPage.getProjectNameList().size(), 1);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectInFolderUsingCreateNewJob")
    public void testIfTwoDifferentFoldersCanHoldItemsWithTheSameNames() {
        String folderProjectName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(FOLDER_NAME_1, FolderConfigurationPage.class)
                .getHeader()
                .clickLogoIcon()
                .clickOnNewItemLinkWithChevron(FOLDER_NAME_1)
                .createNewItem(FOLDER_NAME_2, FolderConfigurationPage.class)
                .clickSave()
                .getProjectName();

        Assert.assertEquals(folderProjectName, FOLDER_NAME_2);
    }

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
}