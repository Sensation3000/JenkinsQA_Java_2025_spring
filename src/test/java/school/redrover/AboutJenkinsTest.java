package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.Arrays;
import java.util.List;

public class AboutJenkinsTest extends BaseTest {

    @Test
    public void testOpenViaJenkinsVersionOnHomePage() {
        List<String> breadcrumbNavigation = new HomePage(getDriver())
                .clickJenkinsVersionButton()
                .clickAboutJenkinsInJenkinsVersionDropDownMenu()
                .getBreadcrumbsNavigationList();

        Assert.assertEquals(breadcrumbNavigation, Arrays.asList("Dashboard", "Manage Jenkins", "About Jenkins"));
    }

    @Test
    public void testOpenAboutJenkinsPage() {
        String currentUrl = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .getCurrentUrl();

        Assert.assertEquals(currentUrl, "http://localhost:8080/manage/about/");
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
    public void testLogoIsDisplayed() {
        boolean isLogoDisplayed = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickAboutJenkins()
                .isLogoDisplayed();

        Assert.assertTrue(isLogoDisplayed);
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
