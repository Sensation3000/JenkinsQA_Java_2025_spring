package school.redrover.page.managejenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.aboutjenkins.AboutJenkinsPage;
import school.redrover.page.clouds.CloudsPage;
import school.redrover.page.credentials.CredentialsPage;
import school.redrover.page.nodes.NodesPage;
import school.redrover.page.plugins.PluginsPage;
import school.redrover.page.script.ScriptConsolePage;
import school.redrover.page.system.SystemPage;
import school.redrover.page.systeminfo.SystemInfoPage;
import school.redrover.page.user.UsersPage;

import java.util.List;

public class ManageJenkinsPage extends BasePage {

    @FindBy(css = "a[href='configure']")
    private WebElement buttonSystem;

    @FindBy(xpath = "//a[@href='script']")
    private WebElement buttonScriptConsole;

    @FindBy(xpath = "//a[@href='credentials']")
    private WebElement buttonCredentials;

    @FindBy (xpath = "//a[@href='computer']")
    private WebElement buttonNodes;

    @FindBy(css = "a[href='cloud']")
    private WebElement buttonClouds;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getManageJenkinsTitleText() {
        return getWait5().until(ExpectedConditions.visibilityOf(getDriver()
                .findElement(By.xpath("//div[@class='jenkins-app-bar__content']/h1")))).getText();
    }

    public UsersPage clickUsers() {
        getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='securityRealm/']"))).click();

        return new UsersPage(getDriver());
    }

    public ManageAppearansePage clickAppearanse() {
        getDriver().findElement(By.xpath("//a[@href='appearance']")).click();

        return new ManageAppearansePage(getDriver());
    }

    public PluginsPage clickPlugins() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/section[2]/div/div[3]/a")).click();

        return new PluginsPage(getDriver());
    }

    public NodesPage clickNodes() {
        buttonNodes.click();

        return new NodesPage(getDriver());
    }

    public CloudsPage clickClouds() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonClouds)).click();

        return new CloudsPage(getDriver());
    }

    public SystemPage clickSystemButton(){
        buttonSystem.click();

        return new SystemPage(getDriver());
    }

    public SystemInfoPage clickSystemInfo() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[1]/a")).click();

        return new SystemInfoPage(getDriver());
    }

    public AboutJenkinsPage clickAboutJenkins() {
        getDriver().findElement(By.xpath("//a[@href='about']")).click();

        return new AboutJenkinsPage(getDriver());
    }

    public ScriptConsolePage clickScriptConsole() {
        buttonScriptConsole.click();

        return new ScriptConsolePage(getDriver());
    }
    public CredentialsPage clickCredentials(){
        buttonCredentials.click();

        return new CredentialsPage(getDriver());
    }

    public List<String> getMainSectionTitlesOnManageJenkinsPage() {
        return getDriver().findElements(By.cssSelector(".jenkins-section__title"))
                          .stream()
                          .map(WebElement::getText)
                          .toList();
    }

    public List<String> getSubSectionTitlesOnManageJenkinsPage(int index) {
        List<WebElement> sections = getDriver().findElements(By.cssSelector(".jenkins-section"));
        WebElement section = sections.get(index);

        return section.findElements(By.cssSelector(".jenkins-section__item dt"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
