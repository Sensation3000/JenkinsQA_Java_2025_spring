package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
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
        String quietPeriodValue = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectMultiConfigurationAndClickOk()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced()
                .clickQuietPeriodCheckbox()
                .increaseQuietPeriodValue()
                .clickSaveButton()
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced().checkQuietPeriodValue();

        Assert.assertEquals(quietPeriodValue, "6");
    }

    @Test(dependsOnMethods = "testQuietPeriodValueSet")
    public void testBlockBuildOptionsSet() {
        MultiConfigurationConfigurePage multiConfigurationConfigurePage= new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced()
                .clickBlockBuildWhenUpstreamBuildingCheckbox()
                .clickBlockBuildWhenDownstreamBuildingCheckbox()
                .clickSaveButton()
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced();

        Assert.assertTrue(multiConfigurationConfigurePage.isCheckboxBlockBuildWhenUpstreamBuildingSelected());
        Assert.assertTrue(multiConfigurationConfigurePage.isCheckboxBlockBuildWhenDownstreamBuildingSelected());
    }

    @Test(dependsOnMethods = "testBlockBuildOptionsSet")
    public void testRetryCountValueSet() {
        String retryCountValue = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced()
                .clickRetryCountCheckbox()
                .enterRetryCountValue()
                .clickSaveButton()
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced()
                .checkRetryCountValue();

        Assert.assertEquals(retryCountValue, "2");
    }

    @Test(dependsOnMethods = "testRetryCountValueSet")
    public void testCustomWorkspaceSet() {
        String customWorkspaceDirectoryValue = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced()
                .clickUseCustomWorkspaceCheckbox()
                .enterCustomWorkspaceDirectoryValue(CUSTOM_DIRECTORY)
                .clickSaveButton()
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced()
                .checkCustomWorkspaceDirectoryValue();

        Assert.assertEquals(customWorkspaceDirectoryValue, CUSTOM_DIRECTORY);
    }

    @Test(dependsOnMethods = "testCustomWorkspaceSet")
    public void testCustomChildWorkspaceSet() {
        String customChildWorkspaceDirectoryValue = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced()
                .clickUseCustomChildWorkspaceCheckbox()
                .enterCustomChildWorkspaceDirectoryValue(CUSTOM_CHILD_DIRECTORY)
                .clickSaveButton()
                .clickConfigure()
                .scrollToAdvancedProjectOptions()
                .clickAdvanced()
                .checkCustomChildWorkspaceDirectoryValue();

        Assert.assertEquals(customChildWorkspaceDirectoryValue, CUSTOM_CHILD_DIRECTORY);
    }

    @Test(dependsOnMethods =  "testQuietPeriodValueSet")
    public void testAddTimestampToConsoleOutput() {
        String timestamp = new HomePage (getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .scrollToEnvironmentSectionWithJS()
                .selectAddTimestampsCheckbox()
                .clickSaveButton()
                .clickBuildNow()
                .clickStatus()
                .clickLastBuild()
                .clickConsoleOutput()
                .getTimestampsText();

        Assert.assertEquals(timestamp,"Timestamps");
    }
}
