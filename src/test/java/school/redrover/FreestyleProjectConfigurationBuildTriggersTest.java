package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.HomePage;
import school.redrover.page.freestyle.FreestyleProjectPage;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class FreestyleProjectConfigurationBuildTriggersTest extends BaseTest {

    private static final String PROJECT_NAME = "Project Name";
    private static final String NON_EXISTENT_PROJECT_NAME = "Non Existent Project Name";
    private static final String AUTH_TOKEN = "Test-Token";
    private static final String EXPECTED_SCHEDULE = "H 14 * * 1-5";
    private static final String UNEXPECTED_SCHEDULE = "H";
    private static final String EXPECTED_TOOLTIP_TEXT = "Help for feature: ";
    private static final String GIT_HUB_PROJECT_LINK = "https://github.com/RedRoverSchool/JenkinsQA_Java_2025_spring";
    private static final String GIT_HUB = "GitHub";
    private static FreestyleConfigurationPage freestyleConfigurationPage;

    @Test
    public void testCreateFreestyleProjectAndOpenBuildTriggersSection() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .scrollToBuildTriggers();

        Assert.assertEquals(freestyleConfigurationPage.getSectionNameTriggers(), "Triggers");
        Assert.assertEquals(freestyleConfigurationPage.countHelperIconsTriggersSection(), 8);
    }


    @Test(dependsOnMethods = "testCreateFreestyleProjectAndOpenBuildTriggersSection")
    public void testTriggerBuildsRemotelyCheckboxIsVisibleAndEnabled() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToBuildTriggers();
        Assert.assertTrue(freestyleConfigurationPage.isTriggerBuildsRemotelyCheckboxDisplayed());
        Assert.assertTrue(freestyleConfigurationPage.isTriggerBuildsRemotelyCheckboxEnabled());
    }

    @Test(dependsOnMethods = "testTooltipsAppearForBuildTriggers")
    public void testBuildAfterProjectsCheckboxIsVisibleAndEnabled() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToBuildTriggers();
        Assert.assertTrue(freestyleConfigurationPage.isBuildAfterProjectsCheckboxDisplayed());
        Assert.assertTrue(freestyleConfigurationPage.isBuildAfterProjectsCheckboxEnabled());
    }

    @Test(dependsOnMethods = "testTooltipsAppearForBuildTriggers")
    public void testBuildPeriodicallyCheckboxIsVisibleAndEnabled() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToBuildTriggers();
        Assert.assertTrue(freestyleConfigurationPage.isBuildPeriodicallyCheckboxDisplayed());
        Assert.assertTrue(freestyleConfigurationPage.isBuildPeriodicallyCheckboxEnabled());
    }

    @Test(dependsOnMethods = "testTooltipsAppearForBuildTriggers")
    public void testGithubHookTriggerCheckboxIsVisibleAndEnabled() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToBuildTriggers();
        Assert.assertTrue(freestyleConfigurationPage.isGithubHookTriggerCheckboxDisplayed());
        Assert.assertTrue(freestyleConfigurationPage.isGithubHookTriggerCheckboxEnabled());
    }

    @Test(dependsOnMethods = "testTooltipsAppearForBuildTriggers")
    public void testPollSCMCheckboxIsVisibleAndEnabled() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToBuildTriggers();
        Assert.assertTrue(freestyleConfigurationPage.isPollSCMCheckboxDisplayed());
        Assert.assertTrue(freestyleConfigurationPage.isPollSCMCheckboxEnabled());
    }

    @Test(dependsOnMethods = "testPollSCMCheckboxIsVisibleAndEnabled")
    public void testRemoteTriggerOptionDisplaysTokenField() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToBuildTriggers()
                .checkTriggerBuildsRemotelyCheckbox()
                .enterAuthToken(AUTH_TOKEN)
                .clickSaveButton()
                .clickConfigure()
                .scrollToBuildTriggers();

        Assert.assertEquals(freestyleConfigurationPage.getAuthenticationTokenLabelText(), "Authentication Token");
        Assert.assertEquals(freestyleConfigurationPage.getAuthTokenDomValue(), AUTH_TOKEN);
    }

    @Test(dependsOnMethods = "testRemoteTriggerOptionDisplaysTokenField")
    public void testBuildAfterOtherProjectsAreBuiltOptionDisplaysField() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
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

    @Test(dependsOnMethods = "testBuildAfterOtherProjectsAreBuiltOptionDisplaysField")
    public void testBuildPeriodicallyScheduleFieldIsDisplayed() {
        String actualSchedule = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToIgnorePostCommitHooksCheckbox()
                .setBuildPeriodicallyCheckbox()
                .sendScheduleText(EXPECTED_SCHEDULE)
                .clickSaveButton()
                .clickConfigure()
                .scrollToBuildTriggers()
                .sendScheduleActualText();

        Assert.assertEquals(actualSchedule, EXPECTED_SCHEDULE);
    }

    @Test(dependsOnMethods = "testBuildPeriodicallyScheduleFieldIsDisplayed")
    public void testGithubHookCheckboxCanBeEnabled() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToIgnorePostCommitHooksCheckbox()
                .checkGithubHookCheckbox()
                .clickSaveButton()
                .clickConfigure()
                .scrollToBuildTriggers();

        Assert.assertTrue(freestyleConfigurationPage.isGithubHookCheckboxSelected());
    }

    @Test(dependsOnMethods = "testGithubHookCheckboxCanBeEnabled")
    public void testPollSCMCheckboxIsDisplayed() {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
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

    @Test(dependsOnMethods = "testPollSCMCheckboxIsDisplayed")
    public void testErrorShownForNonExistentProjectName() {
        boolean isErrorMessageAppears = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToIgnorePostCommitHooksCheckbox()
                .setWrongProjectToWatch(NON_EXISTENT_PROJECT_NAME)
                .clickOnDropdownToClose()
                .isNoSuchProjectErrorVisible();

        Assert.assertTrue(isErrorMessageAppears);
    }

    @Test(dependsOnMethods = "testErrorShownForNonExistentProjectName")
    public void testErrorShownForInvalidBuildPeriodicallySchedule() {
        boolean isErrorMessageAppears = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToIgnorePostCommitHooksCheckbox()
                .sendScheduleText(UNEXPECTED_SCHEDULE)
                .checkGithubHookCheckbox()
                .isScheduleSpecErrorVisible();

        Assert.assertTrue(isErrorMessageAppears);
    }

    @Test(dependsOnMethods = "testErrorShownForInvalidBuildPeriodicallySchedule")
    public void testErrorShownForInvalidPollSCMSchedule() {
        boolean isErrorMessageAppears = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToIgnorePostCommitHooksCheckbox()
                .sendScheduleTextForPollSCM(UNEXPECTED_SCHEDULE)
                .checkGithubHookCheckbox()
                .isScheduleSpecErrorVisible();

        Assert.assertTrue(isErrorMessageAppears);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectAndOpenBuildTriggersSection")
    public void testAvailableBuildNowOnProjectPage() {
        boolean isTextBuildScheduled = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickLeftSideMenuBuildNow()
                .isTextBuildScheduled();

        assertTrue(isTextBuildScheduled);
    }

    @Test(dependsOnMethods = "testAvailableBuildNowOnProjectPage")
    public void testDeleteBuild() {
        boolean isDeleteSuccess = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickLastBuild()
                .clickDeleteBuild()
                .clickSubmitDeleteBuild()
                .clickChanges()
                .isBuildDeleted();

        assertTrue(isDeleteSuccess);
    }

    @Test(dependsOnMethods = "testDeleteBuild")
    public void testAvailableBuildNowOnbreadcrumbs() {
        boolean isTextBuildScheduled = new HomePage(getDriver())
                .clickScheduleBuild()
                .isTextBuildScheduled();

        assertTrue(isTextBuildScheduled);
    }

    @Test(dependsOnMethods = "testAvailableBuildNowOnbreadcrumbs")
    public void testAvailableSuccessResult() {
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

    @Test(dataProvider = "tooltipFeatures", dependsOnMethods = "testCreateFreestyleProjectAndOpenBuildTriggersSection")
    public void testTooltipsAppearForBuildTriggers(String featureName) {
        freestyleConfigurationPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .scrollToBuildTriggers()
                .hoverHelpIcon(featureName);

        Assert.assertTrue(freestyleConfigurationPage.isTooltipVisibleWithText(EXPECTED_TOOLTIP_TEXT+featureName));
    }
}
