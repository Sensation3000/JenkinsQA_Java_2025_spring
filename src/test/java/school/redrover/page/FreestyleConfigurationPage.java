package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import java.util.List;

public class FreestyleConfigurationPage extends BasePage {

    public FreestyleConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleConfigurationPage addDescription(String text) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(text);

        return this;
    }

    public FreestyleProjectPage clickSaveButton() {
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        return new FreestyleProjectPage(getDriver());
    }


    public FreestyleConfigurationPage scrollToGeneralItem() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//*[@id='general']")));

        return this;
    }

    public FreestyleConfigurationPage scrollToSourceCodeManagementItem() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//*[@id='source-code-management']")));

        return this;
    }

    public FreestyleConfigurationPage scrollToTriggersItem() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//*[@id='triggers']")));

        return this;
    }

    public FreestyleConfigurationPage scrollToBuildEnvironmentItem() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//*[@id='environment']")));

        return this;
    }

    public FreestyleConfigurationPage scrollToBuildStepsItem() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//*[@id='build-steps']")));

        return this;
    }

    public FreestyleConfigurationPage clickPostBuildActionsItem() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//*[@id='post-build-actions']")));


        return this;
    }

    public FreestyleConfigurationPage checkTriggerBuildsRemotelyCheckbox() {
        getDriver().findElement(By.xpath("//label[contains(text(), 'Trigger builds remotely (e.g., from scripts)')]")).click();

        return this;
    }

    public FreestyleConfigurationPage checkBuildAfterProjectsCheckbox() {
        getDriver().findElement(By.xpath("//label[contains(text(), 'Build after other projects are built')]")).click();

        return this;
    }

    public FreestyleConfigurationPage checkBuildPeriodicallyCheckbox() {
        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Build periodically')]")));
        getDriver().findElement(By.xpath("//label[contains(text(), 'Build periodically')]")).click();

        return this;
    }

    public FreestyleConfigurationPage checkGithubHookCheckbox() {
        getDriver().findElement(By.xpath("//label[contains(text(), 'GitHub hook trigger for GITScm polling')]")).click();

        return this;
    }

    public FreestyleConfigurationPage checkPollCSMCheckbox() {
        getDriver().findElement(By.xpath("//label[contains(text(), 'Poll SCM')]")).click();

        return this;
    }

    public FreestyleConfigurationPage sendScheduleText(String text) {
        getDriver().findElement(By.xpath("//div[@class='setting-main']/textarea[@name='_.spec']")).clear();
        getDriver().findElement(By.xpath("//div[@class='setting-main']/textarea[@name='_.spec']")).sendKeys(text);

        return this;
    }

    public String sendScheduleActualText() {

        return getDriver().findElement(By.xpath("//div[@class='setting-main']/textarea[@name='_.spec']")).getText();
    }

    public FreestyleConfigurationPage clickEnableDisableToggle() {
        getDriver().findElement(By.cssSelector("label[for='enable-disable-project']")).click();

        return this;
    }

    public String getToggleStatus() {
        List<WebElement> statusList = getDriver().findElements(By.cssSelector("span[class^='jenkins-toggle-switch__label']"));
        for (WebElement e : statusList) {
            if (e.isDisplayed()) {
                return e.getText();
            }
        }
        return "";
    }
}
