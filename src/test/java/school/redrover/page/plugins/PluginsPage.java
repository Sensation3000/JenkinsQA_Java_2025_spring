package school.redrover.page.plugins;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
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

    public PluginsPage typePluginName(String text) {
        WebElement searchInput = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".jenkins-search--app-bar input")
        ));
        searchInput.clear();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
        getDriver().navigate().refresh();

        boolean pluginVisible = false;
        int retryCount = 0;
        while (!pluginVisible && retryCount < 10) {
            try {
                WebElement pluginLink = this.getWait10().until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//a[@href='https://plugins.jenkins.io/windows-cloud']")
                        )
                );
                pluginVisible = pluginLink != null && pluginLink.isDisplayed();
            } catch (Exception e) {
                Assert.fail("Waiting for plugin to appear... Retry #" + retryCount);
            }
            retryCount++;
        }

        return this;
    }

    public PluginsPage clickSelectedPluginCheckbox(String pluginName) {
        List<WebElement> pluginRows = getDriver().findElements(By.cssSelector("tbody tr"));
        for (WebElement row : pluginRows) {
            WebElement tableLink = row.findElement(By.cssSelector(".jenkins-table__link"));
            if (tableLink.getText().contains(pluginName)) {
                WebElement checkbox = row.findElement(By.cssSelector(".jenkins-checkbox label"));
                if (!checkbox.isSelected()) {
                    this.getWait10().until(ExpectedConditions.elementToBeClickable(checkbox));
                    checkbox.click();
                    break;
                }
            }
        }

        return this;
    }

    public String getDownloadProgressStatus() {
        this.getWait10().until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("[id^='status']"), "Success"
        ));

        return getDriver().findElement(By.cssSelector("[id^='status']")).getText();
    }
}
