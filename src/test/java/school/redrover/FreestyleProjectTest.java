package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.component.HeaderComponent;
import school.redrover.page.error.ErrorPage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.freestyle.FreestyleProjectPage;
import school.redrover.page.HomePage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FreestyleProjectTest extends BaseTest {

    private static final String PROJECT_NAME = "Freestyle Project";
    private static final String UPDATED_PROJECT_NAME = "NEW Freestyle NAME";
    private static final String PROJECT_DESCRIPTION = "This is a NEW freestyleProject description";
    private static final String SECOND_PROJECT_NAME = "Second Freestyle Project";
    private static final String GITHUB_PROJECT_URL = "https://github.com/RedRoverSchool/JenkinsQA_Java_2025_spring";
    private static final String MENU_GITHUB_OPTION = "GitHub";
    private static final String TIME_PERIOD = "Minute";

    @Test
    public void testCreateFreestyleProject() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .clickSaveButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectWithNoneSCM")
    public void testAccessProjectManagementPageFromDashboard() {
        String currentProjectName = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getProjectName();

        Assert.assertEquals(currentProjectName, PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testDisableProject() {
        String warningProjectIsDisabled = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .waitUntilTextNameProjectToBePresentInH1(PROJECT_NAME)
                .clickConfigure()
                .clickEnableDisableToggle()
                .clickSaveButton()
                .getDisabledWarningMessageText();

        Assert.assertEquals(warningProjectIsDisabled, "This project is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testWarningMessageIsDisplayedAfterDisableProject() {
        List<WebElement> warningMessageList = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getWarningMessageList(PROJECT_NAME);

        Assert.assertEquals(warningMessageList.size(), 1);
    }

    @Test(dependsOnMethods = "testWarningMessageIsDisplayedAfterDisableProject")
    public void testEnableProject() {
        String projectIsEnabled = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .waitUntilTextNameProjectToBePresentInH1(PROJECT_NAME)
                .clickEnableButton()
                .clickConfigure()
                .getProjectStatus();

        Assert.assertEquals(projectIsEnabled, "Enabled");
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testWarningMessageDisappearsAfterEnableProject() {
        List<WebElement> warningMessageList = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getWarningMessageList(PROJECT_NAME);

        Assert.assertEquals(warningMessageList.size(), 0);
    }

    @Test(dependsOnMethods = "testWarningMessageDisappearsAfterEnableProject")
    public void testFreestyleProjectAddGitHubURL() {
        List<String> freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .waitUntilTextNameProjectToBePresentInH1(PROJECT_NAME)
                .clickConfigure()
                .checkGitHubProjectCheckbox()
                .sentGitHubProjectURL(GITHUB_PROJECT_URL)
                .clickSaveButton()
                .getLeftSideMenuNameList();

        Assert.assertTrue(freestyleProjectPage.contains(MENU_GITHUB_OPTION));
    }

    @Test(dependsOnMethods = "testFreestyleProjectAddGitHubURL")
    public void testRemoveGitHubProject() {
        List<String> leftMenuList = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .waitUntilTextNameProjectToBePresentInH1(PROJECT_NAME)
                .clickConfigure()
                .checkGitHubProjectCheckbox()
                .clickSaveButton()
                .getLeftSideMenuNameList();

        Assert.assertFalse(leftMenuList.contains("GitHub"));
    }

    @Test(dependsOnMethods = "testRemoveGitHubProject")
    public void testDiscardOldBuilds() {
        int buildLogLimit = 5;

        List<WebElement> entries = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickDiscardOldBuilds(5)
                .clickSaveButton()
                .clickBuildNowButton(8)
                .getListOfBuilds();

        assertEquals(entries.size(), buildLogLimit);
    }

    @Test(dependsOnMethods = "testDiscardOldBuilds")
    public void testCreateDuplicate() {
        String errorMessage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectFreestyle()
                .getItemNameInvalidMessage();

        Assert.assertEquals(errorMessage, "» A job already exists with the name ‘%s’".formatted(PROJECT_NAME));
    }

    @Test(dependsOnMethods = "testCreateDuplicate")
    public void testEditDescription() {
        String freestyleProjectDescriptionText = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickEditDescriptionButton()
                .sendDescription(PROJECT_DESCRIPTION)
                .clickSave()
                .getDescription();

        Assert.assertEquals(freestyleProjectDescriptionText, PROJECT_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testEditDescription")
    public void testPreviewDescriptionOption() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickEditDescriptionButton()
                .clickPreviewDescription();

        Assert.assertTrue(freestyleProjectPage.isPreviewDescriptionBlockDisplayed());
        Assert.assertEquals(freestyleProjectPage.getTextFromPreviewDescriptionBlock(), PROJECT_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testPreviewDescriptionOption")
    public void testHidePreviewDescriptionOption() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickEditDescriptionButton()
                .clickPreviewDescription()
                .clickHidePreviewDescription();

        Assert.assertFalse(freestyleProjectPage.isHidePreviewLinkAvailable());
        Assert.assertFalse(freestyleProjectPage.isPreviewDescriptionBlockDisplayed());
    }

    @Test(dependsOnMethods = "testHidePreviewDescriptionOption")
    public void testDescriptionCanBeEmpty() {
        String freestyleProjectDescriptionText = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickEditDescriptionButton()
                .deleteDescription()
                .clickSave()
                .getDescription();

        Assert.assertEquals(freestyleProjectDescriptionText, "");
    }

    @Test(dependsOnMethods = "testDescriptionCanBeEmpty")
    public void testRenameFreestyleProject() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickLeftSideMenuRename()
                .sendName(UPDATED_PROJECT_NAME)
                .clickRename();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), UPDATED_PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testDeleteFreestyleProject() {
        List<String> projectNameList = new HomePage(getDriver())
                .clickOnJobInListOfItems(UPDATED_PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickLeftSideMenuDelete()
                .clickPopUpYesDeleteProject()
                .getHeader()
                .clickLogoIcon()
                .getProjectNameList();

        Assert.assertEquals(projectNameList.size(), 0);
    }

    @Test
    public void testAddBuildSteps() {
        List<String> projectNameList = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(PROJECT_NAME, FreestyleConfigurationPage.class)
                .addBuildSteps(7)
                .addBuildSteps(2)
                .addBuildSteps(1)
                .clickApply()
                .getChunkHeaderList();

        assertEquals(projectNameList.size(), 3);
    }

    @Test
    public void testCreateWithDescription() {
        String freestyleProjectDescriptionText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .addDescription(PROJECT_DESCRIPTION)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(freestyleProjectDescriptionText, PROJECT_DESCRIPTION);
    }

    @Test
    public void testCreateWithConfig() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .addDescription(PROJECT_DESCRIPTION)
                .clickThrottleBuilds()
                .selectTimePeriod(TIME_PERIOD)
                .clickSaveButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), PROJECT_NAME);
        Assert.assertEquals(freestyleProjectPage.getDescription(), PROJECT_DESCRIPTION);
    }

    @Test (dependsOnMethods = "testCreateWithConfig")
    public void testSuccessfulCopyWithConfig () {

        FreestyleConfigurationPage freestyleConfigurationPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(SECOND_PROJECT_NAME)
                .selectFreestyle()
                .sendTextCopyForm(PROJECT_NAME)
                .clickOkButton();

        Assert.assertEquals(freestyleConfigurationPage.getDescriptionText(), PROJECT_DESCRIPTION);
        Assert.assertTrue(freestyleConfigurationPage.getSelectThrottleBuilds());
        Assert.assertEquals(freestyleConfigurationPage.getTimePeriod(), TIME_PERIOD);
    }

    @Test(dependsOnMethods = "testSuccessfulCopyWithConfig")
    public void testUnsuccessfulCopy() {
        final String nonexistentName = "999";

        ErrorPage errorPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(UPDATED_PROJECT_NAME)
                .selectFreestyle()
                .sendTextCopyForm(nonexistentName)
                .clickOkButtonWithError();

        Assert.assertEquals(errorPage.getTitle(),"Error");
        Assert.assertEquals(errorPage.getErrorText(), "No such job: %s".formatted(nonexistentName));
    }

    @Test
    public void testTriggerBuildAfterOtherProjects() {
        new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleClickOkAndWaitCreateItemFormIsClose();

        new HeaderComponent(getDriver())
                .goToHomePage()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(SECOND_PROJECT_NAME)
                .selectFreestyleClickOkAndWaitCreateItemFormIsClose()
                .clickToReverseBuildTriggerAndSetUpStreamProject(PROJECT_NAME)
                .clickSaveButton()
                .waitUntilTextNameProjectToBePresentInH1(SECOND_PROJECT_NAME);

        final List<String> builds = new HeaderComponent(getDriver())
                .goToHomePage()
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleConfigurationPage(getDriver()))
                .clickBuildNow()
                .waitJobStarted()
                .getBuildList();

        final String buildStatusText = new FreestyleConfigurationPage(getDriver()).getBuildStatusText();

        Assert.assertEquals(builds.size(), 1);
        Assert.assertTrue(buildStatusText.contains("Today"));
        Assert.assertTrue(buildStatusText.contains("#1"));
    }

    @Test(dependsOnMethods = "testAddBuildSteps")
    public void testAddPostBuildActions() {
        List<String> postBuildNameList = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .addPostBuildActions(1)
                .addPostBuildActions(5)
                .addPostBuildActions(1)
                .addPostBuildActions(11)
                .clickApply()
                .getChunkHeaderList();

        assertEquals(postBuildNameList.size(), 6);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectWithNoneSCM")
    public void testAddBuildStepsANDPostBuildActions() {
        List<String> postBuildNameList = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .addPostBuildActions(1)
                .addPostBuildActions(9)
                .addPostBuildActions(11)
                .addBuildSteps(1)
                .addBuildSteps(7)
                .addPostBuildActions(1)
                .clickApply()
                .getChunkHeaderList();

        assertEquals(postBuildNameList.size(), 5);
    }

    @Test
    public void testCreateFreestyleProjectWithNoneSCM() {
        String projectName = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .selectNoneSCM()
                .clickSaveButton()
                .getProjectName();

        Assert.assertEquals(projectName, PROJECT_NAME);
    }

    @Test
    public void testBuildPeriodically() {
        final String everyMinuteSchedule = "* * * * *";

        List<String> buildList = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToBuildTriggers()
                .setBuildPeriodicallyCheckbox()
                .sendScheduleText(everyMinuteSchedule)
                .clickSaveButton()
                .waitForBuildToAppear(70);

        Assert.assertEquals(buildList.size(), 1);
        Assert.assertTrue(buildList.get(0).contains("#1\n%s".formatted(LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a")))));
    }

    @Test(dependsOnMethods = "testAddBuildStepsANDPostBuildActions")
    public void testNumberActualVisibleHelpButtons(){
        int numberHelpButtons = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .numberHelpTooltips();

        assertEquals(numberHelpButtons, 28);
    }

    @Test
    public void testVerifyDropDownMenuWithLeftSideMenuWithoutStatus() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName("New Freestyle Project")
                .selectFreestyleAndClickOk()
                .addDescription("Freestyle Project Description")
                .clickSaveButton()
                .clickProjectBreadcrumbsDropDownMenu();

        Assert.assertEquals(
                freestyleProjectPage.getDropDownMenuItemsText(),
                freestyleProjectPage.getLeftSideMenuWithoutStatus());
    }
}