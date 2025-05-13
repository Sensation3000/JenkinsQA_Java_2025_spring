package school.redrover.page.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import school.redrover.common.BasePage;

public class PipelineConfigurationPage extends BasePage {

    public PipelineConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public PipelineConfigurationPage sendDescription(String text) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(text);

        return this;
    }

    public PipelineConfigurationPage checkByLabel(String label) {
        getDriver().findElement(By.xpath("//label[contains(text(),'%s')]".formatted(label))).click();

        return this;
    }

    public PipelineConfigurationPage switchToggle() {
        By toggle = By.id("toggle-switch-enable-disable-project");

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(toggle));

        getDriver().findElement(toggle).click();

        return this;
    }

    public String checkTooltipText() {

        WebElement toggle = getDriver().findElement(By.id("toggle-switch-enable-disable-project"));

        String tooltipText = toggle.getAttribute("title");

        return tooltipText;
    }

    public boolean isToggleDisabled() {
        By toggleDisabled = By.xpath("//span[@class='jenkins-toggle-switch__label__unchecked-title']");

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(toggleDisabled));

        return getDriver().findElement(toggleDisabled).isDisplayed();
    }

    public boolean isToggleEnabled() {
        By toggleEnabled = By.xpath("//span[@class='jenkins-toggle-switch__label__checked-title']");

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(toggleEnabled));

        return getDriver().findElement(toggleEnabled).isDisplayed();
    }

    public PipelineProjectPage clickSave() {
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        return new PipelineProjectPage(getDriver());
    }

    public String checkStatusOnToggle() {
        WebElement statusToggle;
        statusToggle = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-toggle-switch__label__checked-title")));

        return statusToggle.getText();
    }

    public String checkStatusOffToggle() {
        WebElement statusToggle;
        statusToggle = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-toggle-switch__label__unchecked-title")));

        return statusToggle.getText();
    }

    public PipelineConfigurationPage clickTriggerMenu() {
        WebElement triggerMenu = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[2]/span/button"));
        triggerMenu.click();
        return this;
    }

    public boolean isTriggerCheckbox(WebElement checkbox) {

        return checkbox.isSelected();
    }

    public List<WebElement> clickTriggerCheckbox() {
        List<WebElement> Trigger = List.of(
        getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/form/div[1]/section[1]/section/div[4]/div[1]/div/span/label")),
        getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/form/div[1]/section[1]/section/div[5]/div[1]/div/span/label")),
        getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/form/div[1]/section[1]/section/div[6]/div[1]/div/span/label")),
        getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/form/div[1]/section[1]/section/div[7]/div[1]/div/span/label")),
        getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/form/div[1]/div[5]/div[1]/div/span/label")));

        List<WebElement> checkboxes = List.of(
            getDriver().findElement(By.id("cb8")),
            getDriver().findElement(By.id("cb9")),
            getDriver().findElement(By.id("cb10")),
            getDriver().findElement(By.id("cb11")),
            getDriver().findElement(By.id("cb12")));

        for (WebElement webElement : Trigger) {
            ((JavascriptExecutor) getDriver()).executeScript(
                "const rect = arguments[0].getBoundingClientRect();" +
                    "window.scrollBy({ top: rect.top - 100 });",
                webElement);
            getWait5().until(ExpectedConditions.elementToBeClickable(webElement)).click();
        }
        return checkboxes;
    }
}
