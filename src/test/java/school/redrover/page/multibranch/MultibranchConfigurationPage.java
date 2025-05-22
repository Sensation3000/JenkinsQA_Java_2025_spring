package school.redrover.page.multibranch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.MultibranchPipelineConfigurationTest;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;

import java.util.List;

public class MultibranchConfigurationPage extends BasePage {

    public MultibranchConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchConfigurationPage hoverOnEnabledDisabledToggle() {
        new Actions(getDriver()).moveToElement(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("toggle-switch-enable-disable-project")))).perform();

        return this;
    }

    public MultibranchConfigurationPage sendMultibranchName (String name) {
        getDriver().findElement(By.name("_.displayNameOrNull")).sendKeys(name);
        return this;
    }

    public MultibranchConfigurationPage clickEnabledDisabledToggle() {
        getDriver().findElement(By.className("jenkins-toggle-switch__label")).click();

        return this;
    }

    public MultibranchProjectPage clickSaveButton() {
        getDriver().findElement(By.className("jenkins-submit-button")).click();

        return new MultibranchProjectPage(getDriver());
    }

    public MultibranchConfigurationPage clickApplyButton() {
        getDriver().findElement(By.className("apply-button")).click();

        return this;
    }

    public String getNotificationText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).getText();
    }

    public String getDisableToggleText() {
        return getDriver().findElement(By.className("jenkins-toggle-switch__label__unchecked-title")).getText();
    }

    public String getEnableToggleText() {
        return getDriver().findElement(By.className("jenkins-toggle-switch__label__checked-title")).getText();
    }

    public String getEnabledDisabledToggleShownAttribute() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("toggle-switch-enable-disable-project"))).getDomAttribute("aria-describedby");
    }

    public MultibranchConfigurationPage sendDescription(String text) {
        getDriver().findElement(By.xpath("//textarea[@name='_.description']")).sendKeys(text);

        return this;
    }

    public MultibranchConfigurationPage scrollAndClickOnBranchSourcesSectionWithJs() {
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector("button[suffix='sources']")));

        return this;
    }

    public String getBranchSourcesSectionText() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("branch-sources"))).getText().trim();
    }

    public MultibranchConfigurationPage clickOnBranchSourcesSectionText(String branchSourceName) {
        try { getWait5()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'jenkins-dropdown__item ') and contains(text(), '%s')]"
                .formatted(branchSourceName.trim()))))
                .click();
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Please enter the valid branch source name", e);
        }

        return this;
    }

    public boolean isBranchSourceButtonDisplayed() {
        return getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-section-id='branch-sources']")))
                .isDisplayed();
    }

    public List<String> getBranchSourcesTypeNames() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-dropdown.jenkins-dropdown--compact")));

        return getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button[class*='jenkins-dropdown__item']")))
                .stream()
                .map(webelement -> webelement.getText().trim())
                .toList();
    }

    public MultibranchPipelineLogScanningPage enterValueIntoProjectRepositoryInputAndClickSubmit(String repositoryUrl, By locator) {
        WebElement GitProjectRepositoryInput = getWait5().until(ExpectedConditions.elementToBeClickable(locator));

        GitProjectRepositoryInput.clear();
        GitProjectRepositoryInput.sendKeys(repositoryUrl);

        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.name("Submit")));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-app-bar h1")));

        return new MultibranchPipelineLogScanningPage(getDriver());
    }

    public MultibranchConfigurationPage checkPeriodicallyIfNotOtherwiseRun() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("scan-multibranch-pipeline-triggers")));
        TestUtils.scrollToItemWithJS(getDriver(), getDriver().findElement(By.id("scan-multibranch-pipeline-triggers")));
        getDriver().findElement(By.xpath("//label[text()='Periodically if not otherwise run']")).click();

        return this;
    }

    public MultibranchConfigurationPage selectIntervalForPeriodicallyIfNotOtherwiseRun(String interval) {
        new Select(getDriver().findElement(By.name("_.interval"))).selectByValue(interval);

        return this;
    }
}
