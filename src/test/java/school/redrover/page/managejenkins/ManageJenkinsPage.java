package school.redrover.page.managejenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.aboutjenkins.AboutJenkinsPage;
import school.redrover.page.clouds.CloudsPage;
import school.redrover.page.plugins.PluginsPage;
import school.redrover.page.system.SystemPage;
import school.redrover.page.systeminfo.SystemInfoPage;
import school.redrover.page.user.UsersPage;

import java.util.List;

public class ManageJenkinsPage extends BasePage {

    @FindBy(css = "a[href='configure']")
    private WebElement buttonSystem;

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

    public CloudsPage clickClouds() {
        List<WebElement> buttons = getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//*[@id=\"main-panel\"]/section[2]/div/div/a/dl/dt")
        ));

        for (int i = 0; i < buttons.size(); i++) {
            try {
                WebElement button = getDriver().findElements(
                        By.xpath("//*[@id=\"main-panel\"]/section[2]/div/div/a/dl/dt")
                ).get(i);
                if (button.getText().equals("Clouds")) {
                    button.click();
                    return new CloudsPage(getDriver());
                }
            } catch (StaleElementReferenceException e) {
                i--;
            }
        }
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
}
