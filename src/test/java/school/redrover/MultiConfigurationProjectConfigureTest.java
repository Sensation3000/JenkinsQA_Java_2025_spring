package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;

public class MultiConfigurationProjectConfigureTest extends BaseTest {

    private static final String PROJECT_NAME = "Project name";
    private static final String CUSTOM_DIRECTORY = "/myWorkspace";
    private static final String CUSTOM_CHILD_DIRECTORY = "/myWorkspace/${BUILD_NUMBER}";

    @Test
    public void testQuietPeriodValueSet() {
        MultiConfigurationConfigurePage multiConfigurationConfigurePage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectMultiConfigurationAndClickOk()
                .clickAdvancedProjectOptions()
                .clickAdvanced()
                .clickQuietPeriodCheckbox()
                .increaseQuietPeriodValue()
                .clickSaveButton()
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced();

        Assert.assertEquals(multiConfigurationConfigurePage.checkQuietPeriodValue(), "6");
    }

    @Test(dependsOnMethods = "testQuietPeriodValueSet")
    public void testRetryCountValueSet() {
        MultiConfigurationConfigurePage multiConfigurationConfigurePage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced()
                .clickRetryCountCheckbox()
                .enterRetryCountValue()
                .clickSaveButton()
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced();

        Assert.assertEquals(multiConfigurationConfigurePage.checkRetryCountValue(), "2");
    }

    @Test(dependsOnMethods = "testQuietPeriodValueSet")
    public void testBlockBuildOptionsSet() {
        MultiConfigurationConfigurePage multiConfigurationConfigurePage= new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced()
                .clickBlockBuildWhenUpstreamBuildingCheckbox()
                .clickBlockBuildWhenDownstreamBuildingCheckbox()
                .clickSaveButton()
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced();

        Assert.assertTrue(multiConfigurationConfigurePage.isCheckboxBlockBuildWhenUpstreamBuildingSelected());
        Assert.assertTrue(multiConfigurationConfigurePage.isCheckboxBlockBuildWhenDownstreamBuildingSelected());
    }

    @Test(dependsOnMethods = "testQuietPeriodValueSet")
    public void testCustomWorkspaceSet() {
        MultiConfigurationConfigurePage multiConfigurationConfigurePage= new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced()
                .clickUseCustomWorkspaceCheckbox()
                .enterCustomWorkspaceDirectoryValue(CUSTOM_DIRECTORY)
                .clickSaveButton()
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced();

        Assert.assertEquals(multiConfigurationConfigurePage.checkCustomWorkspaceDirectoryValue(), CUSTOM_DIRECTORY);
    }

    @Test(dependsOnMethods = "testQuietPeriodValueSet")
    public void testCustomChildWorkspaceSet() {
        MultiConfigurationConfigurePage multiConfigurationConfigurePage= new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced()
                .clickUseCustomChildWorkspaceCheckbox()
                .enterCustomChildWorkspaceDirectoryValue(CUSTOM_CHILD_DIRECTORY)
                .clickSaveButton()
                .clickConfigure()
                .clickAdvancedProjectOptions()
                .clickAdvanced();

        Assert.assertEquals(multiConfigurationConfigurePage.checkCustomChildWorkspaceDirectoryValue(), CUSTOM_CHILD_DIRECTORY);
    }
}
