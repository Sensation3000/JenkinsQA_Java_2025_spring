package school.redrover.page.clouds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.plugins.PluginsPage;


public class CloudsPage extends BasePage {

    @FindBy(css = "a[href*='/manage/pluginManager']")
    private WebElement installPluginButton;

    @FindBy(css = "a[href='new']")
    private WebElement newCloudButton;

    @FindBy(css = ".jenkins-table__link")
    private WebElement cloudName;


    public CloudsPage(WebDriver driver) {
        super(driver);
    }

    public ExistingCloudPage clickCloudName() {
        getWait10()
                .until(ExpectedConditions.visibilityOf(cloudName))
                .click();

        return new ExistingCloudPage(getDriver());
    }

    public PluginsPage clickInstallPlugin() {
        getWait10()
                .until(ExpectedConditions.elementToBeClickable(installPluginButton))
                .click();

        return new PluginsPage(getDriver());
    }

    public NewCloudPage clickCreateNewCloud() {
        getWait10()
                .until(ExpectedConditions.visibilityOf(newCloudButton))
                .click();

        return new NewCloudPage(getDriver());
    }

    public String getEmptyStatusText() {

        return getWait10()
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".empty-state-section p")))
                .getText();
    }
}
