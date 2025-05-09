package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AboutJenkinsTest extends BaseTest {

    @Test
    public void checkJenkinsAboutFunction() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement footerButton = driver.findElement(By.cssSelector("footer .jenkins_ver"));
        footerButton.click();

        List<WebElement> dropdownMenuOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".jenkins-dropdown a")));
        List<String> footerDropdown = dropdownMenuOptions.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        List<String> expectedTitles = Arrays.asList("About Jenkins", "Get involved", "Website");

        assertTrue(footerDropdown.containsAll(expectedTitles),
                "Footer dropdown menu options do not match expected footer names. Found: " + footerDropdown);

        WebElement aboutOption = dropdownMenuOptions.stream()
                .filter(option -> option.getText().trim().equals("About Jenkins"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("'About Jenkins' option not found in the dropdown"));

        aboutOption.click();

        List<WebElement> breadcrumbMenu = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".jenkins-breadcrumbs a")));

        List<String> menuOptions = breadcrumbMenu.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        List<String> expectedOptions = Arrays.asList("Dashboard", "Manage Jenkins", "About Jenkins");

        assertTrue(menuOptions.containsAll(expectedOptions),
                "Breadcrumb menu options do not match expected menu options. Found: " + menuOptions);
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