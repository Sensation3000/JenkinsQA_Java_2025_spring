package school.redrover.page.systeminfo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class SystemInfoPage extends BasePage {

    public SystemInfoPage(WebDriver driver) {
        super(driver);
    }

    public String getSystemInfoTitleText() {
        return getDriver().findElement(By.xpath("//h1"))
                .getText();
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

    public SystemInfoPage clickEnviromentalVariablesSubpage() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div[2]/a"))
                .click();
        return this;
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

    public SystemInfoPage clickPluginsSubpage() {
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div[3]/a"))
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

    public SystemInfoPage clickShowValuesButton(String tabName, String buttonName) {
        getDriver().findElement(By.xpath("//h2[text()='%s']/following-sibling::div/button[normalize-space(text() = '%s')]"
                .formatted(tabName, buttonName))).click();

        return this;
    }

    public String getClassFirstElementInList() {
        return getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='app-hidden-info-hide']"))).getDomAttribute("class");

    }

    public SystemInfoPage clickTab(String tabName) {
        getDriver().findElement(By.xpath("//a[normalize-space()='%s']".formatted(tabName))).click();

        return this;
    }
}
