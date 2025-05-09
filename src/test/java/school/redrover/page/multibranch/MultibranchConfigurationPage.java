package school.redrover.page.multibranch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;

import java.util.List;

public class MultibranchConfigurationPage extends BasePage {

    public MultibranchConfigurationPage(WebDriver driver) { super(driver); }

    public MultibranchConfigurationPage hoverOnEnabledDisabledToggle() {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.id("toggle-switch-enable-disable-project"))).perform();

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

    public String getDisableToggleText() {
        return getDriver().findElement(By.className("jenkins-toggle-switch__label__unchecked-title")).getText();
    }

    public String getEnableToggleText() {
        return getDriver().findElement(By.className("jenkins-toggle-switch__label__checked-title")).getText();
    }

    public String getEnabledDisabledToggleShownAttribute() {
        return getDriver().findElement(By.id("toggle-switch-enable-disable-project")).getDomAttribute("aria-describedby");
    }

    public MultibranchConfigurationPage sendDescription(String text) {
        getDriver().findElement(By.xpath("//textarea[@name='_.description']")).sendKeys(text);

        return this;
    }

    public String getBranchSourcesSectionText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("branch-sources"))).getText().trim();
    }

    public boolean isBranchSourceButtonDisplayed() {
        return getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-section-id='branch-sources']")))
                .isDisplayed();
    }

    public List<String> getBranchSourcesTypeNames() {
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector("button[suffix='sources']")));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-dropdown.jenkins-dropdown--compact")));

        return getDriver().findElements(By.cssSelector("button[class*='jenkins-dropdown__item']"))
                .stream()
                .map(webelement -> webelement.getText().trim())
                .toList();
    }
}