package school.redrover.page.plugins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.common.BasePage;

import java.time.Duration;
import java.util.List;

public class PluginsPage extends BasePage {

    @FindBy(id = "filter-box")
    private WebElement searchField;

    @FindBy(css = "[data-plugin-id='windows-cloud'] .jenkins-checkbox")
    private WebElement checkBoxWindowsCloud;

    @FindBy(id = "button-install")
    private WebElement installButton;

    @FindBy(css = "tr[id='row4'] td[id]")
    private WebElement statusEl;

    public PluginsPage(WebDriver driver) {
        super(driver);
    }

    public PluginsPage clickAvailablePlugins() {
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[2]/span/a")).click();

        return this;
    }

    public PluginsPage clickRestApiCheckbox() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='plugin.pipeline-rest-api.default']")));
        getDriver().findElement(By.xpath("//label[@for='plugin.pipeline-rest-api.default']")).click();

        return this;
    }

    public PluginsPage clickInstallButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(installButton)).click();

        return this;
    }

    public String getPluginStatus() {
        getWait10().until(ExpectedConditions
                .textToBePresentInElement(getDriver().findElement(By.xpath("//tr[td[text()='Pipeline: REST API']]/td[2]")), "Success"));

        return getDriver().findElement(By.xpath("//tr[td[text()='Pipeline: REST API']]/td[2]")).getText();
    }

    public PluginsPage clickInstalledPlugins() {
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[3]/span/a")).click();

        return this;
    }

    public PluginsPage sendText(String text) {
        getDriver().findElement(By.id("filter-box")).sendKeys(text);

        return this;
    }

    public List<WebElement> checkInstalledPluginInList(String pluginName) {
        return getDriver().findElements(By.xpath("//tr[@data-plugin-name=\"" + pluginName + "\"]"));
    }

    public String getSearchFieldText() {
        return getWait10().until(ExpectedConditions.visibilityOf(searchField)).getDomAttribute("value");
    }

    public PluginsPage sendWindowCloudPlugin() {
        getWait10().until(ExpectedConditions.visibilityOf(searchField)).clear();
        getWait10().until(ExpectedConditions.visibilityOf(searchField)).sendKeys("Windows cloud");

        return this;
    }

    public PluginsPage clickCheckBoxWindowsCloud() {
        getWait10().until(ExpectedConditions.visibilityOf(checkBoxWindowsCloud)).click();

        return this;
    }

    public String getSuccessInstallStatus() {
        WebDriverWait wait60 = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        wait60.until(ExpectedConditions.textToBePresentInElement(statusEl, "Success"));

        return statusEl.getText();
    }
}