package school.redrover.page.organizationfolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.folder.FolderProjectPage;

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
        configureOnLeftSidePanel.click();

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

        return getWait5().until(ExpectedConditions.visibilityOf(header)).getText();
    }

    public String getOrganizationFolderIconTitleNameFromHeader() {
        getWait10().until(ExpectedConditions.visibilityOf(header));

        return headerIcon.getDomAttribute("title");
    }

    public FolderProjectPage clickProjectName() {
        getWait5()
                .until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("a[href*='job'].jenkins-table__link"))))
                .click();

        return new FolderProjectPage(getDriver());
    }
}
