package school.redrover;

import com.sun.source.tree.AssertTree;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.HomePage;
import school.redrover.page.freestyle.FreestyleProjectPage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class FreestyleProjectConfigurationBuildTriggersTest extends BaseTest {

    private static final String PROJECT_NAME = "New project";
    private static final String NON_EXISTENT_PROJECT_NAME = "qwerty";
    private static final String AUTH_TOKEN = "sometoken8ad01cf431d742977b8cc82";
    private static final String EXPECTED_TRIGGER_INFO_TEXT = """
        Use the following URL to trigger build remotely: JENKINS_URL/job/New%20project/build?token=TOKEN_NAME or /buildWithParameters?token=TOKEN_NAME
        Optionally append &cause=Cause+Text to provide text that will be included in the recorded build cause.
        """.trim();
    private static final String EXPECTED_SCHEDULE = "H 14 * * 1-5";
    private static final String UNEXPECTED_SCHEDULE = "H";
    private static final String EXPECTED_TOOLTIP_TEXT = "Help for feature: ";
    private static final String GIT_HUB_PROJECT_LINK = "https://github.com/RedRoverSchool/JenkinsQA_Java_2025_spring";
    private static final String GIR_HUB = "GitHub";
    
    @Test
    public void testTriggersSectionHeaderAndHelperIcons() {

        FreestyleConfigurationPage page = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToBuildTriggers();

        Assert.assertEquals(page.getSectionNameTriggers(), "Triggers");
        Assert.assertEquals(page.countHelperIconsTriggersSection(), 8);
        Assert.assertTrue(page.isTriggerBuildsRemotelyCheckboxDisplayed());
        Assert.assertTrue(page.isTriggerBuildsRemotelyCheckboxEnabled());
        Assert.assertTrue(page.isBuildAfterProjectsCheckboxDisplayed());
        Assert.assertTrue(page.isBuildAfterProjectsCheckboxEnabled());
        Assert.assertTrue(page.isBuildPeriodicallyCheckboxDisplayed());
        Assert.assertTrue(page.isBuildPeriodicallyCheckboxEnabled());
        Assert.assertTrue(page.isGithubHookTriggerCheckboxDisplayed());
        Assert.assertTrue(page.isGithubHookTriggerCheckboxEnabled());
        Assert.assertTrue(page.isPollSCMCheckboxDisplayed());
        Assert.assertTrue(page.isPollSCMCheckboxEnabled());
    }

    @Test
    public void testRemoteTriggerOptionDisplaysTokenField() {

        FreestyleConfigurationPage freestyleConfigurationPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToBuildTriggers()
                .checkTriggerBuildsRemotelyCheckbox()
                .enterAuthToken(AUTH_TOKEN)
                .clickSaveButton()
                .clickConfigure()
                .scrollToBuildTriggers();

        Assert.assertEquals(freestyleConfigurationPage.getAuthenticationTokenLabelText(), "Authentication Token");
        Assert.assertEquals(freestyleConfigurationPage.getAuthTokenDomValue(), AUTH_TOKEN);
        Assert.assertEquals(freestyleConfigurationPage.getTriggerInfoText(), EXPECTED_TRIGGER_INFO_TEXT);
    }

    @Test
    public void testBuildAfterOtherProjectsAreBuiltOptionDisplaysField() {

        FreestyleConfigurationPage freestyleConfigurationPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToIgnorePostCommitHooksCheckbox()
                .checkBuildAfterProjectsCheckbox()
                .setProjectToWatch(PROJECT_NAME)
                .clickAllReverseBuildTriggerLabels()
                .clickSaveButton()
                .clickConfigure()
                .scrollToBuildTriggers();

        Assert.assertEquals(freestyleConfigurationPage.getCurrentProjectName(), PROJECT_NAME+", ");
        Assert.assertTrue(freestyleConfigurationPage.isLastRadioButtonSelected());
    }

    @Test
    public void testBuildPeriodicallyScheduleFieldIsDisplayed() {

        String actualSchedule = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToBuildTriggers()
                .setBuildPeriodicallyCheckbox()
                .sendScheduleText(EXPECTED_SCHEDULE)
                .clickSaveButton()
                .clickConfigure()
                .scrollToBuildTriggers()
                .sendScheduleActualText();

        Assert.assertEquals(actualSchedule, EXPECTED_SCHEDULE);
    }

    @Test
    public void shouldEnableGitHubHookTriggerForFreestyleProject() {

        FreestyleConfigurationPage freestyleConfigurationPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToBuildTriggers()
                .checkGithubHookCheckbox()
                .clickSaveButton()
                .clickConfigure()
                .scrollToBuildTriggers();

        Assert.assertTrue(freestyleConfigurationPage.isGithubHookCheckboxSelected());
    }

    @Test
    public void testPollSCMCheckboxIsDisplayed() {

        FreestyleConfigurationPage freestyleConfigurationPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToIgnorePostCommitHooksCheckbox()
                .checkPollSCMCheckbox()
                .sendScheduleTextForPollSCM(EXPECTED_SCHEDULE)
                .checkIgnorePostCommitHooksCheckbox()
                .clickSaveButton()
                .clickConfigure()
                .scrollToBuildTriggers();

        Assert.assertEquals(freestyleConfigurationPage.sendScheduleTextForThrottleBuilds(), EXPECTED_SCHEDULE);
        Assert.assertTrue(freestyleConfigurationPage.isPollSCMCheckboxSelected());
    }

    @Test
    public void validateBuildTriggersInputProjectsToWatch() {

        boolean isErrorMessageAppears = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToIgnorePostCommitHooksCheckbox()
                .checkBuildAfterProjectsCheckbox()
                .setWrongProjectToWatch(NON_EXISTENT_PROJECT_NAME)
                .clickOnDropdownToClose()
                .isNoSuchProjectErrorVisible();

        Assert.assertTrue(isErrorMessageAppears);
    }

    @Test
    public void validateBuildTriggersBuildPeriodicallyScheduleInput() {

        boolean isErrorMessageAppears = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToIgnorePostCommitHooksCheckbox()
                .setBuildPeriodicallyCheckbox()
                .sendScheduleText(UNEXPECTED_SCHEDULE)
                .checkPollSCMCheckbox()
                .isScheduleSpecErrorVisible();

        Assert.assertTrue(isErrorMessageAppears);
    }

    @Test
    public void validateBuildTriggersPollSCMScheduleInput() {

        boolean isErrorMessageAppears = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToIgnorePostCommitHooksCheckbox()
                .checkPollSCMCheckbox()
                .sendScheduleTextForPollSCM(UNEXPECTED_SCHEDULE)
                .setBuildPeriodicallyCheckbox()
                .isScheduleSpecErrorVisible();

        Assert.assertTrue(isErrorMessageAppears);
    }

    @Test(dependsOnMethods = "testTriggersSectionHeaderAndHelperIcons")
    public void testAvailableBuildNowOnProjectPage() {
        boolean isTextBuildScheduled = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickLeftSideMenuBuildNow()
                .isTextBuildScheduled();

        assertTrue(isTextBuildScheduled);
    }

    @Test(dependsOnMethods = "testTriggersSectionHeaderAndHelperIcons")
    public void testAvailableBuildNowOnbreadcrumbs() {
        boolean isTextBuildScheduled = new HomePage(getDriver())
                .clickScheduleBuild()
                .isTextBuildScheduled();

        assertTrue(isTextBuildScheduled);
    }

    @Test(dependsOnMethods = "testTriggersSectionHeaderAndHelperIcons")
    public void testAvailableSuccesResult() {
        boolean isFinishedSuccess = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickLeftSideMenuBuildNow()
                .clickStatus()
                .clickLastBuild()
                .clickConsoleOutput()
                .isFinishedSuccess();

        assertTrue(isFinishedSuccess);
    }

    @DataProvider(name = "tooltipFeatures")
    public Object[][] provideTooltipFeatures() {
        return new Object[][]{
                {"Trigger builds remotely (e.g., from scripts)"},
                {"Build after other projects are built"},
                {"Build periodically"},
                {"GitHub hook trigger for GITScm polling"},
                {"Poll SCM"}
        };
    }

    @Test(dataProvider = "tooltipFeatures")
    public void testTooltipsAppearForBuildTriggers(String featureName) {

        FreestyleConfigurationPage freestyleConfigurationPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToBuildTriggers()
                .hoverHelpIcon(featureName);

        Assert.assertTrue(freestyleConfigurationPage.isTooltipVisibleWithText(EXPECTED_TOOLTIP_TEXT+featureName));
    }

    @Test
    public void testAddGitHubProject() {
        List<String> leftMenuList = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .checkGitHubProjectCheckbox()
                .sentGitHubProjectURL(GIT_HUB_PROJECT_LINK)
                .clickSaveButton()
                .getLeftSideMenuNameList();

        Assert.assertTrue(leftMenuList.contains(GIR_HUB));
    }
}
