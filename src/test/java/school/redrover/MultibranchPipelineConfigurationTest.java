package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.multibranch.MultibranchConfigurationPage;
import school.redrover.page.multibranch.MultibranchProjectPage;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class MultibranchPipelineConfigurationTest extends BaseTest {
    private static final String MULTIBRANCH_PIPELINE_NAME = "New Multibranch Pipeline";
    private static final String VALID_REPOSITORY_URL = "https://github.com/StepanidaKirillina1/testRepo";

    @Test
    public void createMultibranchPipeline() {
        boolean isBranchSourceButtonDisplayed = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(MULTIBRANCH_PIPELINE_NAME, MultibranchConfigurationPage.class)
                .isBranchSourceButtonDisplayed();

        Assert.assertTrue(isBranchSourceButtonDisplayed);
    }

    @Test(dependsOnMethods = "createMultibranchPipeline")
    public void testTheTypesOfBranchSources() {
        List<String> expectedBranchSourceTypeNames = List.of("Git", "GitHub", "Single repository & branch");

        List<String> actualBranchSourceTypeNames = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .clickConfigureLeftSidePanel()
                .scrollAndClickOnBranchSourcesSectionWithJs()
                .getBranchSourcesTypeNames();

        Assert.assertEquals(actualBranchSourceTypeNames, expectedBranchSourceTypeNames);
    }

    @Test(dependsOnMethods = "testTheTypesOfBranchSources")
    public void testIfBranchSourceSectionIsPresent() {
        String branchSourcesSectionText = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .clickConfigureLeftSidePanel()
                .getBranchSourcesSectionText();

        Assert.assertEquals(branchSourcesSectionText, "Branch Sources");
    }

    @Test(dependsOnMethods = "testIfBranchSourceSectionIsPresent")
    public void testScanMultibranchPipelineTriggers(){
        String notificationText = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .clickConfigureLeftSidePanel()
                .checkPeriodicallyIfNotOtherwiseRun()
                .selectIntervalForPeriodicallyIfNotOtherwiseRun("1m")
                .clickApplyButton()
                .getNotificationText();

        Assert.assertEquals(notificationText, "Saved");
    }

    @Test(dependsOnMethods = "testScanMultibranchPipelineTriggers")
    public void testCancelDeletionProject(){
        String homePage = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .clickConfigureLeftSidePanel()
                .clickSaveButton()
                .cancelDeletionMultiBranchPipeline()
                .getHeader()
                .goToHomePage()
                .getProjectName();

        Assert.assertEquals(homePage, MULTIBRANCH_PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testCancelDeletionProject")
    public void testDisableMultibranchPipeline() {
        String toggleText = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .clickConfigureLeftSidePanel()
                .clickEnabledDisabledToggle()
                .clickSaveButton()
                .clickConfigureLeftSidePanel()
                .getDisableToggleText();

        Assert.assertEquals(toggleText, "Disabled", "EnableToggle is not Disabled");
    }

    @Test(dependsOnMethods = "testDisableMultibranchPipeline")
    public void testEnableMultibranchPipeline() {
        String toggleText = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .clickConfigureLeftSidePanel()
                .clickEnabledDisabledToggle()
                .clickSaveButton()
                .clickConfigureLeftSidePanel()
                .getEnableToggleText();

        Assert.assertEquals(toggleText, "Enabled");
    }

    @Test(dependsOnMethods = "testEnableMultibranchPipeline")
    public void testEnableDisablePipeline() {
        String tooltipDefaultText = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .clickConfigureLeftSidePanel()
                .getEnableToggleText();

        Assert.assertEquals(tooltipDefaultText, "Enabled");
    }

    @Test(dependsOnMethods = "testEnableDisablePipeline")
    public void testEnabledDisabledToggleTooltip() {
        String tooltipEnabledAttribute = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .clickConfigureLeftSidePanel()
                .hoverOnEnabledDisabledToggle()
                .getEnabledDisabledToggleShownAttribute();

        Assert.assertEquals(tooltipEnabledAttribute, "tippy-10");
    }

    @Test(dependsOnMethods = "testEnabledDisabledToggleTooltip")
    public void testDeleteMultibranchPipelineProject(){
        List<String> ProjectNameList = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_PIPELINE_NAME, new MultibranchProjectPage(getDriver()))
                .deleteMultiBranchPipeline()
                .getProjectNameList();

        Assert.assertEquals(ProjectNameList.size(), 0);
    }

    @Test(dataProvider = "branchSourceTypes", dataProviderClass = TestDataProvider.class)
    public void testGitBranchSourceWithInvalidUrl(String branchSourceType, By repositoryInputLocator) {
        boolean isSuccessSubstringAppeared = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(TestUtils.generateRandomAlphanumeric())
                .selectMultibranchPipelineAndClickOkWithJS()
                .scrollAndClickOnBranchSourcesSectionWithJs()
                .clickOnBranchSourcesSectionText(branchSourceType)
                .enterValueIntoProjectRepositoryInputAndClickSubmit(VALID_REPOSITORY_URL + "!", repositoryInputLocator)
                .isSuccessSubstringAppeared(branchSourceType);

        Assert.assertFalse(isSuccessSubstringAppeared);
    }

    @Test(dataProvider = "branchSourceTypes", dataProviderClass = TestDataProvider.class)
    public void testGitBranchSourceWithValidUrl(String branchSourceType, By repositoryInputLocator) {
        boolean isSuccessSubstringAppeared = new HomePage(getDriver())
                .clickOnManageJenkinsLink()
                .clickSystemButton()
                .selectAnOptionAtGitHubApiUsageDropdownMenu("Throttle at/near rate limit")
                .clickOnSubmitButton()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(TestUtils.generateRandomAlphanumeric())
                .selectMultibranchPipelineAndClickOkWithJS()
                .scrollAndClickOnBranchSourcesSectionWithJs()
                .clickOnBranchSourcesSectionText(branchSourceType)
                .enterValueIntoProjectRepositoryInputAndClickSubmit(VALID_REPOSITORY_URL, repositoryInputLocator)
                .isSuccessSubstringAppeared(branchSourceType);

        Assert.assertTrue(isSuccessSubstringAppeared);
    }
}