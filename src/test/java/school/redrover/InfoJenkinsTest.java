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
        List<String> mavenizedDependenciesList = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getMavenizedDependenciesList();

        Assert.assertFalse(mavenizedDependenciesList.isEmpty());
    }

    @Test
    public void checkStaticResourcesListIsNotEmpty() {
        List<String> staticResourcesList = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getStaticResourcesList();

        Assert.assertFalse(staticResourcesList.isEmpty());
    }

    @Test
    public void checkLicenseAndDependencyInformationForPluginsListIsNotEmpty() {
        List<String> licenseAndDependencyList = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getLicenseAndDependencyInformationForPluginsList();

        Assert.assertFalse(licenseAndDependencyList.isEmpty());
    }
}
