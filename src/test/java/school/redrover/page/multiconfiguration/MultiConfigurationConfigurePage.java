package school.redrover.page.multiconfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;

import java.util.List;

public class MultiConfigurationConfigurePage extends BasePage {

    public MultiConfigurationConfigurePage(WebDriver driver) {
        super(driver);
    }

    public MultiConfigurationConfigurePage clickEnableToggle() {
        getDriver().findElement(By.className("jenkins-toggle-switch__label")).click();

        return this;
    }

    public MultiConfigurationStatusPage clickSaveButton() {
        getDriver().findElement(By.className("jenkins-submit-button")).click();

        return new MultiConfigurationStatusPage(getDriver());
    }

    public String checkTooltipVisibility() {
        Actions actions = new Actions(getDriver());
        WebElement element = getDriver().findElement(By.id("toggle-switch-enable-disable-project"));
        actions.moveToElement(element).perform();

        return element.getAttribute("aria-describedby");
    }

    public String getHeadingText() {
        return getDriver().findElement(By.id("general")).getText();
    }

    public MultiConfigurationConfigurePage scrollToEnvironmentSectionWithJS() {
        TestUtils.scrollToItemWithJS(getDriver(), getDriver().findElement(By.id("environment")));

        return this;
    }

    public MultiConfigurationStatusPage checkEnvironmentCheckboxesAndClickOnSaveButton() {
        List<WebElement> labels = getDriver().findElements(By.xpath("//div[@id='environment']/../descendant::label"));
        for (WebElement label : labels) {
            TestUtils.scrollAndClickWithJS(getDriver(), label);
        }
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.name("Submit")));

        return new MultiConfigurationStatusPage(getDriver());
    }

    public boolean verifyIfAllEnvironmentCheckboxesAreSelected() {
        List<WebElement> checkboxes = getDriver().findElements(
                By.xpath("//div[@id='environment']/../descendant::input[@type='checkbox']")
        );

        return checkboxes.stream().allMatch(checkbox -> checkbox.isSelected());
    }
}