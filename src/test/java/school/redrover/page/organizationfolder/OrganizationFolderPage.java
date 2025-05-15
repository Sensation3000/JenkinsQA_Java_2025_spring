package school.redrover.page.organizationfolder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class OrganizationFolderPage extends BasePage {
    @FindBy(xpath = "//*[@id='main-panel']/h1")
    private WebElement header;

    @FindBy(xpath = "//*[@id='main-panel']/h1/*[name()='svg']")
    private WebElement headerIcon;

    @FindBy(xpath = "//a[@data-title='Delete Organization Folder']")
    private WebElement deleteOrganizationFolderOnLeftSidePanel;

    @FindBy(css = "a[href*='configure']")
    private WebElement configureOnLeftSidePanel;

    @FindBy(xpath = "//button[@data-id='ok']")
    private WebElement yesButtonOnDeletionConfirmationPopup;

    @FindBy(xpath = "//button[@data-id='cancel']")
    private WebElement cancelButtonOnDeletionConfirmationPopup;

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderPage clickDeleteOrganizationFolderOnLeftSidePanel() {
        deleteOrganizationFolderOnLeftSidePanel.click();

        return this;
    }

    public OrganizationFolderConfigurePage clickConfigureOnLeftSidePanel() {
        getWait10().until(ExpectedConditions.visibilityOf(configureOnLeftSidePanel)).click();

        return new OrganizationFolderConfigurePage(getDriver());
    }

    public OrganizationFolderPage clickYesOnDeletionConfirmationPopup() {
        yesButtonOnDeletionConfirmationPopup.click();

        return new OrganizationFolderPage(getDriver());
    }

    public OrganizationFolderPage clickCancelOnDeletionConfirmationPopup() {
        cancelButtonOnDeletionConfirmationPopup.click();

        return new OrganizationFolderPage(getDriver());
    }

    public String getOrganizationFolderNameFromHeader() {

        return getWait10().until(ExpectedConditions.visibilityOf(header)).getText();
    }

    public String getOrganizationFolderIconTitleNameFromHeader() {
        getWait10().until(ExpectedConditions.visibilityOf(header));

        return headerIcon.getDomAttribute("title");
    }
}
