package school.redrover.page.aboutjenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

import java.util.List;

public class AboutJenkinsPage extends BasePage {

    @FindBy(xpath = "//div[@class='app-about-branding__aurora']/following-sibling::img")
    private WebElement logoImg;

    @FindBy(className = "app-about-version")
    private WebElement version;

    @FindBy(xpath = "//img[@alt='logo']")
    private WebElement logo;

    @FindBy(xpath = "//h2[text()='Mavenized dependencies']/following-sibling::table/tbody")
    private List<WebElement> mavenizedDependenciesTable;

    @FindBy(xpath = "//a[text()='Static resources']")
    private WebElement staticResourcesTab;

    @FindBy(xpath = "//h2[text()='Static resources']/following-sibling::table/tbody")
    private List<WebElement> staticResourcesTable;

    @FindBy(xpath = "//a[text()='License and dependency information for plugins']")
    private WebElement getLicenseAndDependencyInformationForPluginsTab;

    @FindBy(xpath = "//h2[text()='License and dependency information for plugins']/following-sibling::table/tbody")
    private List<WebElement> getLicenseAndDependencyInformationForPluginsTable;

    public AboutJenkinsPage(WebDriver driver) {super(driver);}

    public String getCurrentVersion() {
        return version.getText();
    }

    public List<String> getMavenizedDependenciesList() {
        return mavenizedDependenciesTable.stream().map(WebElement::getText).toList();
    }

    public List<String> getStaticResourcesList() {
        staticResourcesTab.click();

        return staticResourcesTable.stream().map(WebElement::getText).toList();
    }

    public List<String> getLicenseAndDependencyInformationForPluginsList() {
        getLicenseAndDependencyInformationForPluginsTab.click();

        return getLicenseAndDependencyInformationForPluginsTable.stream().map(WebElement::getText).toList();
    }

    public boolean isLogoDisplayed () {

        return logo.isDisplayed();
    }
}
