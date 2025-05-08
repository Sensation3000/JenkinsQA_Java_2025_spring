package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class InfoJenkinsTest extends BaseTest {

    @Test
    public void checkJenkinsVersion() {
        String version = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getCurrentVersion();

        Assert.assertEquals(version, "Version 2.492.2");
    }

    @Test
    public void checkMavenizedDependenciesListIsNotEmpty() {
        List<String> MavenizedDependenciesList = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getMavenizedDependenciesList();

        Assert.assertFalse(MavenizedDependenciesList.isEmpty());
    }

    @Test
    public void checkStaticResourcesListIsNotEmpty() {
        List<String> StaticResourcesList = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getStaticResourcesList();

        Assert.assertFalse(StaticResourcesList.isEmpty());
    }

    @Test
    public void checkLicenseAndDependencyInformationForPluginsListIsNotEmpty() {
        List<String> LicenseAndDependencyList = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getLicenseAndDependencyInformationForPluginsList();

        Assert.assertFalse(LicenseAndDependencyList.isEmpty());
    }
}
