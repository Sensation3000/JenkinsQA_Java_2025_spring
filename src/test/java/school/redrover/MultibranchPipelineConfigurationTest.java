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
    final String projectName = "New Multibranch Pipeline Project";
    private static final String VALID_REPOSITORY_URL = "https://github.com/StepanidaKirillina1/testRepo";

    @Test
    public void createMultibranchPipelineProject() {
        MultibranchConfigurationPage multiBranchConfigurationPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectMultibranchPipelineAndClickOkWithJS();

        Assert.assertTrue(multiBranchConfigurationPage.isBranchSourceButtonDisplayed());
    }

    @Test
    public void testEnableDisablePipeline() {
        String tooltipDefaultText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectMultibranchPipelineAndClickOkWithJS()
                .getEnableToggleText();

        Assert.assertEquals(tooltipDefaultText, "Enabled", "Toggle is disabled!");
    }

    @Test
    public void testEnabledDisabledToggleTooltip() {
        String tooltipEnabledAttribute = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectMultibranchPipelineAndClickOkWithJS()
                .hoverOnEnabledDisabledToggle()
                .getEnabledDisabledToggleShownAttribute();

        Assert.assertEquals(tooltipEnabledAttribute, "tippy-10", "Tooltip is not displayed!");
    }

    @Test
    public void testDisableMultibranchPipeline() {
        String toggleText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectMultibranchPipelineAndClickOkWithJS()
                .clickEnabledDisabledToggle()
                .clickSaveButton()
                .goToConfigurationPage()
                .getDisableToggleText();

        Assert.assertEquals(toggleText, "Disabled", "EnableToggle is not Disabled");
    }

    @Test(dependsOnMethods = "testDisableMultibranchPipeline")
    public void testEnableMultibranchPipeline() {
        String toggleText = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .clickEnabledDisabledToggle()
                .clickSaveButton()
                .goToConfigurationPage()
                .getEnableToggleText();

        Assert.assertEquals(toggleText, "Enabled", "EnableToggle is not Enabled");
    }

    @Test(dependsOnMethods = "testEnableDisablePipeline")
    public void testIfBranchSourceSectionIsPresent() {
        String branchSourcesSectionText = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .getBranchSourcesSectionText();

        Assert.assertEquals(branchSourcesSectionText, "Branch Sources");
    }

    @Test(dependsOnMethods = "testEnableDisablePipeline")
    public void testTheTypesOfBranchSources() {
        List<String> expectedBranchSourceTypeNames = List.of("Git", "GitHub", "Single repository & branch");

        List<String> actualBranchSourceTypeNames = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .scrollAndClickOnBranchSourcesSectionWithJs()
                .getBranchSourcesTypeNames();

        Assert.assertEquals(actualBranchSourceTypeNames, expectedBranchSourceTypeNames);
    }

    @Ignore
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

    @Ignore
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

    @Test(dependsOnMethods = "createMultibranchPipelineProject")
    public void testDeleteMultibranchPipelineProject(){
        HomePage homePage = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .deleteMultiBranchPipeline();

        Assert.assertEquals(homePage.getProjectNameList().size(), 0);
    }

    @Test
    public void testCancelDeletionProject(){
        String homePage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectMultibranchPipelineAndClickOkWithJS()
                .clickSaveButton()
                .cancelDeletionMultiBranchPipeline()
                .getHeader()
                .goToHomePage()
                .getProjectName();

        Assert.assertEquals(homePage, projectName);
    }
}