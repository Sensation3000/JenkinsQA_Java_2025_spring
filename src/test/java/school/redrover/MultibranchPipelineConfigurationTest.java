package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.multibranch.MultibranchConfigurationPage;
import school.redrover.page.multibranch.MultibranchProjectPage;

import java.util.List;


public class MultibranchPipelineConfigurationTest extends BaseTest {
    final String projectName = "New Multibranch Pipeline Project";
    private static final String VALID_REPOSITORY_URL = "https://github.com/StepanidaKirillina1/testRepo/";

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
                .selectMultibranchAndClickOk()
                .getEnableToggleText();

        Assert.assertEquals(tooltipDefaultText, "Enabled", "Toggle is disabled!");
    }

    @Test
    public void testEnabledDisabledToggleTooltip() {
        String tooltipEnabledAttribute = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectMultibranchAndClickOk()
                .hoverOnEnabledDisabledToggle()
                .getEnabledDisabledToggleShownAttribute();

        Assert.assertEquals(tooltipEnabledAttribute, "tippy-10", "Tooltip is not displayed!");
    }

    @Test
    public void testDisableMultibranchPipeline() {
        String toggleText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectMultibranchAndClickOk()
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

    @Test(dependsOnMethods = "createMultibranchPipelineProject")
    public void testIfBranchSourceSectionIsPresent() {
        String branchSourcesSectionText = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .getBranchSourcesSectionText();

        Assert.assertEquals(branchSourcesSectionText, "Branch Sources");
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

    @Test(dependsOnMethods = "createMultibranchPipelineProject")
    public void testGitBranchSourceWithValidUrl() {
        boolean isSuccessSubstringAppeared = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .scrollAndClickOnBranchSourcesSectionWithJs()
                .clickOnBranchSourcesSectionText("Git")
                .enterValueIntoGitProjectRepositoryInputAndClickSubmit(VALID_REPOSITORY_URL)
                .isSuccessSubstringAppeared();

        Assert.assertTrue(isSuccessSubstringAppeared);
    }

    @Test
    public void testGitBranchSourceWithInvalidUrl() {
        String multibranchPipelineProjectName = "projectTitle";

        boolean isSuccessSubstringAppeared = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(multibranchPipelineProjectName)
                .selectMultibranchPipelineAndClickOkWithJS()
                .scrollAndClickOnBranchSourcesSectionWithJs()
                .clickOnBranchSourcesSectionText("Git")
                .enterValueIntoGitProjectRepositoryInputAndClickSubmit(TestUtils.generateRandomAlphanumeric())
                .isSuccessSubstringAppeared();

        Assert.assertFalse(isSuccessSubstringAppeared);
    }
}