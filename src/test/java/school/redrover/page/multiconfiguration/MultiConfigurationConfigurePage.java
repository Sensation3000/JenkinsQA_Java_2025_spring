package school.redrover.page.multiconfiguration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;
import school.redrover.page.freestyle.FreestyleConfigurationPage;

import java.util.List;

public class MultiConfigurationConfigurePage extends BasePage {

    @FindBy(css = "a[href*='configure']")
    private WebElement buttonConfigure;

    @FindBy(xpath = "//*[@id='main-panel']/form/div[1]/section[2]/div[2]/div[1]/button")
    private WebElement buttonAdvanced;

    @FindBy(xpath = "//input[@name='blockBuildWhenUpstreamBuilding']")
    private WebElement checkboxBlockBuildWhenUpstreamBuilding;

    @FindBy(xpath = "//input[@name='blockBuildWhenDownstreamBuilding']")
    private WebElement checkboxBlockBuildWhenDownstreamBuilding;

    @FindBy(name = "_.displayNameOrNull")
    private WebElement displayNameField;

    @FindBy(name = "_.childCustomWorkspace")
    private WebElement customWorkspace;

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
                By.xpath("//div[@id='environment']/../descendant::input[@type='checkbox']"));

        return checkboxes.stream().allMatch(checkbox -> checkbox.isSelected());
    }

    public MultiConfigurationConfigurePage selectAddTimestampsCheckbox() {
        getDriver().findElement(
                By.xpath("//label[contains(text(),'Add timestamps to the Console Output')]")).click();

        return this;
    }

    public MultiConfigurationConfigurePage clickAdvanced(){
        buttonAdvanced.click();

        return this;
    }

    public MultiConfigurationConfigurePage clickConfigure() {
        buttonConfigure.click();

        return new MultiConfigurationConfigurePage(getDriver());
    }

    public MultiConfigurationConfigurePage scrollToAdvancedProjectOptions() {
        WebElement header = getDriver().findElement(By.xpath("//div[@id='advanced-project-options']"));

        TestUtils.scrollToItemWithJS(getDriver(), header);

        return this;
    }

    public MultiConfigurationConfigurePage clickQuietPeriodCheckbox() {
        getDriver().findElement(By.xpath("//label[text()='Quiet period']")).click();

        return this;
    }

    public MultiConfigurationConfigurePage increaseQuietPeriodValue() {
        WebElement inputField = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='quiet_period']")));
        inputField.click();
        inputField.sendKeys(Keys.ARROW_UP);

        return this;
    }

    public String checkQuietPeriodValue() {
        WebElement inputField = getDriver().findElement(By.xpath("//input[@name='quiet_period']"));

        return inputField.getDomAttribute("value");
    }

    public MultiConfigurationConfigurePage clickRetryCountCheckbox() {
        getDriver().findElement(By.xpath("//label[text()='Retry Count']")).click();

        return this;
    }

    public MultiConfigurationConfigurePage enterRetryCountValue() {
        WebElement inputField = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='scmCheckoutRetryCount']")));
        inputField.click();
        inputField.sendKeys("2");

        return this;
    }

    public String checkRetryCountValue() {
        WebElement inputField = getDriver().findElement(By.xpath("//input[@name='scmCheckoutRetryCount']"));

        return inputField.getDomAttribute("value");
    }

    public MultiConfigurationConfigurePage clickBlockBuildWhenUpstreamBuildingCheckbox() {
        getDriver().findElement(By.xpath("//label[contains(text(), 'Block build when upstream project')]")).click();

        return this;
    }

    public MultiConfigurationConfigurePage clickBlockBuildWhenDownstreamBuildingCheckbox() {
        getDriver().findElement(By.xpath("//label[contains(text(), 'Block build when downstream project')]")).click();

        return this;
    }

    public boolean isCheckboxBlockBuildWhenUpstreamBuildingSelected() {
        return checkboxBlockBuildWhenUpstreamBuilding.isSelected();
    }

    public boolean isCheckboxBlockBuildWhenDownstreamBuildingSelected() {
        return checkboxBlockBuildWhenDownstreamBuilding.isSelected();
    }

    public MultiConfigurationConfigurePage clickUseCustomWorkspaceCheckbox() {
        getDriver().findElement(By.xpath("//label[contains(text(), 'Use custom workspace')]")).click();

        return this;
    }

    public MultiConfigurationConfigurePage enterCustomWorkspaceDirectoryValue(String directory) {
        getDriver().findElement(By.name("_.customWorkspace")).sendKeys(directory);

        return this;
    }

    public String checkCustomWorkspaceDirectoryValue() {
        WebElement inputField = getDriver().findElement(By.name("_.customWorkspace"));

        return inputField.getDomAttribute("value");
    }

    public MultiConfigurationConfigurePage clickUseCustomChildWorkspaceCheckbox() {
        getDriver().findElement(By.xpath("//label[contains(text(), 'Use custom child workspace')]")).click();

        return this;
    }

    public MultiConfigurationConfigurePage enterCustomChildWorkspaceDirectoryValue(String directory) {
        customWorkspace.clear();
        customWorkspace.sendKeys(directory);

        return this;
    }

    public String checkCustomChildWorkspaceDirectoryValue() {
        return customWorkspace.getDomAttribute("value");
    }

    public int numberHelpTooltips() {
        return new FreestyleConfigurationPage(getDriver()).numberHelpTooltips();
    }
}