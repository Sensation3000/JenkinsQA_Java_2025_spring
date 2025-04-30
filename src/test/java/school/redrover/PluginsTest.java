package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class PluginsTest extends BaseTest {

    @Test
    public void testPluginInstallationStatus() {

        String pluginStatus = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickPlugins()
                .clickAvailablePlugins()
                .clickRestApiCheckbox()
                .clickInstallButton()
                .getPluginStatus();

        Assert.assertEquals(pluginStatus, "Success");
    }

    @Test(dependsOnMethods = "testPluginInstallationStatus")
    public void testPluginIsListed() {
        String plugin = "Pipeline: REST API Plugin";

        List<WebElement> installedPlugin = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickPlugins()
                .clickInstalledPlugins()
                .sendText(plugin)
                .checkInstalledPluginInList(plugin);

        Assert.assertFalse(installedPlugin.isEmpty());
    }
}