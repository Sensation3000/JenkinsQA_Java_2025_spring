package school.redrover.page.organizationfolder;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.common.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class OrganizationFolderConfigurePage extends BasePage {
    @FindBy(css = "button[name='Submit']")
    private WebElement saveButton;

    @FindBy(css = "button[name='Apply']")
    private WebElement applyButton;

    @FindBy(xpath = "//button[@data-section-id='appearance']")
    private WebElement appearanceOnLeftSidePanel;

    @FindBy(xpath = "(//select[contains(@class, 'dropdownList')])[2]")
    private WebElement iconDropDownList;

    @FindBy(css = "a.jenkins-help-button[tooltip*='Icon']")
    private WebElement iconHelpButton;

    @FindBy(xpath = "//*[@id='main-panel']/form/div[1]/section[4]/div[2]/div[2]/div/div[1]")
    private WebElement iconHelpBlock;

    @FindBy(name = "_.displayNameOrNull")
    private WebElement displayNameField;

    public OrganizationFolderConfigurePage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderPage clickSave() {
        getWait5().until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();

        return new OrganizationFolderPage(getDriver());
    }

    public OrganizationFolderConfigurePage clickApply() {
        applyButton.click();

        return this;
    }

    public OrganizationFolderConfigurePage clickAppearance() {
        appearanceOnLeftSidePanel.click();
        getWait10().until(ExpectedConditions.visibilityOf(appearanceOnLeftSidePanel));

        return this;
    }

    public List<String> getAvailableIcons() {
        Select select = new Select(getWait10().until(ExpectedConditions.elementToBeClickable(iconDropDownList)));

        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public OrganizationFolderConfigurePage selectIcon(String iconName) {
        Select select = new Select(getWait10().until(ExpectedConditions.elementToBeClickable(iconDropDownList)));
        select.selectByVisibleText(iconName);

        return this;
    }

    public String getIconHelpTooltip() {

        return getWait10().until(ExpectedConditions.elementToBeClickable(iconHelpButton))
                .getDomAttribute("tooltip");
    }

    public OrganizationFolderConfigurePage clickIconHelp() {
        WebElement helpButton = getWait10().until(ExpectedConditions.elementToBeClickable(iconHelpButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", helpButton);

        return this;
    }

    public boolean isIconHelpBlockDisplayed() {

        return getWait5().until(ExpectedConditions.visibilityOf(iconHelpBlock)).isDisplayed();
    }

    public OrganizationFolderConfigurePage setOrganizationFolderDisplayName(String displayName) {
        getWait10().until(ExpectedConditions.visibilityOf(displayNameField)).sendKeys(displayName);

        return this;
    }
}
