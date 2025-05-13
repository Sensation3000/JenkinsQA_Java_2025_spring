package school.redrover.page.systeminfo;

import com.sun.jna.Structure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import school.redrover.common.BasePage;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

public class SystemInfoPage extends BasePage {

    public SystemInfoPage(WebDriver driver) {
        super(driver);
    }

    public String getSystemInfoTitleText() {
        return getDriver().findElement(By.xpath("//h1"))
                .getText();
    }

    public SystemInfoPage clickEnvironmentalVariablesSubpage() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div[2]/a"))
                .click();
        return this;
    }

    public SystemInfoPage clickPluginsSubpage() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div[3]/a"))
                .click();
        return this;
    }

    public SystemInfoPage clickMemoryUsageSubpage() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div[4]/a"))
                .click();
        return this;
    }

    public SystemInfoPage clickShowValuesButtonSP() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[3]/div/button[1]"))
                .click();
        return this;
    }

    public SystemInfoPage findJavaVendorProperty() {
        WebElement vendor = getDriver().findElement(By.xpath("//*[.='java.specification.vendor']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", vendor);

        return this;
    }

    public String getJavaVendorName() {
        return getDriver().findElement(By.xpath("//*[.='java.specification.vendor']/following-sibling::td/div[2]")).getText();
    }

    public String getElementClass() {
        WebElement example = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[4]/table/tbody/tr/td/div[1]"));

        return example.getDomAttribute(("class"));
    }

    public SystemInfoPage clickShowValuesButtonEV() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[4]/div/button[1]"))
                .click();
        return this;
    }

    public String getFirstPlugin() {
        return getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[5]/table/tbody/tr[1]/td[2]"))
                .getText();
    }

    public String getSecondPlugin() {
        return getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[5]/table/tbody/tr[2]/td[2]"))
                .getText();
    }

    public String getTimespanGraph() {
        return getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[6]/div[2]/img"))
                .getAttribute("src");
    }

    public SystemInfoPage setShortTimespan() {
        WebElement s = getDriver().findElement(By.id("timespan-select"));
        Select timespanSelector = new Select(s);
        timespanSelector.selectByVisibleText("Short");
        return this;
    }

    public SystemInfoPage setLongTimespan() {
        Select selector = new Select(getDriver().findElement(By.id("timespan-select")));
        selector.selectByVisibleText("Long");
        return this;
    }
}
