package school.redrover.page.plugins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import java.util.List;

public class PluginsPage extends BasePage {

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
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("button-install")));
        getDriver().findElement(By.id("button-install")).click();

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
}
