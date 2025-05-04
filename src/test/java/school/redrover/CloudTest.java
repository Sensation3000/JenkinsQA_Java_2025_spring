package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class CloudTest extends BaseTest {

    private static final String PROJECT_NAME = "CloudCreationTestName";
    private static final String PLUGIN_NAME = "Windows cloud";

    @Test
    public void installCloudPluginTest() {
        String pluginStatus = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickClouds()
                .clickInstallAPlugin()
                .typePluginName(PLUGIN_NAME)
                .clickSelectedPluginCheckbox(PLUGIN_NAME)
                .clickInstallButton()
                .getDownloadProgressStatus();

        Assert.assertEquals(pluginStatus, "Success");
    }

    @Test(dependsOnMethods = "installCloudPluginTest")
    public void createNewCloudTest() {
        String createdCloud = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickClouds()
                .clickCreateNewCloud()
                .typeCloudName(PROJECT_NAME)
                .selectCloudType()
                .clickCreateButton()
                .clickSaveButton()
                .getCreatedCloudName();

        Assert.assertEquals(createdCloud, PROJECT_NAME);
    }

    @Test(dependsOnMethods = "createNewCloudTest")
    public void deleteCloudTest() {
        String emptyStatusText = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickClouds()
                .clickCloudName()
                .clickDeleteCloud()
                .clickYesButton()
                .getEmptyStatusText();

        Assert.assertTrue(emptyStatusText.contains("There are no clouds currently set up"));
    }
}