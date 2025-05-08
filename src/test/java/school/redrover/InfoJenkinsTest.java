package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class InfoJenkinsTest extends BaseTest {

    @Test
    public void checkJenkinsLogoIsVisible() {
        String textAltLogo = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getTextInAltLogoImg();

        Assert.assertEquals(textAltLogo, "logo");
    }

    @Test
    public void checkJenkinsVersion() {
        String version = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getCurrentVersion();

        Assert.assertEquals(version, "Version 2.492.2");
    }

    @Test
    public void getMavenizedDependenciesList() {
        List<String> MavenizedDependenciesList = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getMavenizedDependenciesList();

        Assert.assertFalse(MavenizedDependenciesList.isEmpty(), "Mavenized Dependencies List is empty");
    }
}
