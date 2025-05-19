package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.plugins.PluginsPage;

public class CloudTest extends BaseTest {

    private static final String PROJECT_NAME = "CloudCreationTestName";

    @Test
    public void installWindowsCloudPlugin() {
        String successInstallStatus = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickClouds()
                .clickInstallPlugin()
                .sendWindowCloudPlugin()
                .clickCheckBoxWindowsCloud()
                .clickInstallButton()
                .getSuccessInstallStatus();

        Assert.assertEquals(successInstallStatus, "Success");
    }

    @Test(dependsOnMethods = "installWindowsCloudPlugin")
    public void testCreateNewCloud() {
        String createdCloud = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickClouds()
                .clickCreateNewCloud()
                .sendCloudName(PROJECT_NAME)
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

    @Test
    public void testSearchFieldText() {
        String searchFieldText = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickClouds()
                .clickInstallPlugin()
                .getSearchFieldText();

        Assert.assertEquals(searchFieldText, "Cloud Providers");
    }
}
