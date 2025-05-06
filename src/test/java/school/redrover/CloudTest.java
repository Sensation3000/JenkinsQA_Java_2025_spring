package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class CloudTest extends BaseTest {

    private static final String PROJECT_NAME = "CloudCreationTestName";
    private static final String PLUGIN_NAME = "Windows cloud";
@Ignore//CloudTest.testCreateNewCloud:36 » WebDriver unknown error: unhandled inspector error: {"code":-32000,"message":"Node with given id does not belong to the document"}
    @Test
    public void testInstallCloudPlugin() {
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

    @Test(dependsOnMethods = "testInstallCloudPlugin")
    public void testCreateNewCloud() {
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

    @Test(dependsOnMethods = "testCreateNewCloud")
    public void testDeleteCloud() {
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