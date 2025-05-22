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
        boolean isBranchSourceButtonDisplayed = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(projectName, MultibranchConfigurationPage.class)
                .isBranchSourceButtonDisplayed();

        Assert.assertTrue(isBranchSourceButtonDisplayed);
    }

    @Test(dependsOnMethods = "createMultibranchPipelineProject")
    public void testTheTypesOfBranchSources() {
        List<String> expectedBranchSourceTypeNames = List.of("Git", "GitHub", "Single repository & branch");

        List<String> actualBranchSourceTypeNames = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .scrollAndClickOnBranchSourcesSectionWithJs()
                .getBranchSourcesTypeNames();

        Assert.assertEquals(actualBranchSourceTypeNames, expectedBranchSourceTypeNames);
    }

    @Test(dependsOnMethods = "testTheTypesOfBranchSources")
    public void testIfBranchSourceSectionIsPresent() {
        String branchSourcesSectionText = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .getBranchSourcesSectionText();

        Assert.assertEquals(branchSourcesSectionText, "Branch Sources");
    }

    @Test(dependsOnMethods = {"testIfBranchSourceSectionIsPresent"})
    public void testDeleteMultibranchPipelineProject(){
        List<String> ProjectNameList = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .deleteMultiBranchPipeline()
                .getProjectNameList();

        Assert.assertEquals(ProjectNameList.size(), 0);
    }

    @Test
    public void testCancelDeletionProject(){
        String homePage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(projectName, MultibranchConfigurationPage.class)
                .clickSaveButton()
                .cancelDeletionMultiBranchPipeline()
                .getHeader()
                .goToHomePage()
                .getProjectName();

        Assert.assertEquals(homePage, projectName);
    }

    @Test
    public void testDisableMultibranchPipeline() {
        String toggleText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(projectName, MultibranchConfigurationPage.class)
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

        Assert.assertEquals(toggleText, "Enabled");
    }

    @Test
    public void testEnableDisablePipeline() {
        String tooltipDefaultText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(projectName, MultibranchConfigurationPage.class)
                .getEnableToggleText();

        Assert.assertEquals(tooltipDefaultText, "Enabled");
    }

    @Test
    public void testEnabledDisabledToggleTooltip() {
        String tooltipEnabledAttribute = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(projectName, MultibranchConfigurationPage.class)
                .hoverOnEnabledDisabledToggle()
                .getEnabledDisabledToggleShownAttribute();

        Assert.assertEquals(tooltipEnabledAttribute, "tippy-10");
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