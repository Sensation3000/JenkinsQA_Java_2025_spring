package school.redrover.page.organizationfolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.newitem.NewItemWithinFolderPage;

public class OrganizationFolderPage extends BasePage {

    @FindBy(xpath = "//*[@id='main-panel']/h1")
    private WebElement header;

    @FindBy(xpath = "//*[@id='main-panel']/h1/*[name()='svg']")
    private WebElement headerIcon;

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderPage clickDeleteOrganizationFolderOnLeftSidePanel() {
        getDriver().findElement(By.xpath("//a[@data-title='Delete Organization Folder']")).click();

        return this;
    }

    public OrganizationFolderConfigurePage clickConfigureOnLeftSidePanel() {
        getDriver().findElement(By.cssSelector("a[href*='configure']")).click();

        return new OrganizationFolderConfigurePage(getDriver());
    }

    public OrganizationFolderPage clickYesOnDeletionConfirmationPopup() {
        getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();

        return new OrganizationFolderPage(getDriver());
    }

    public OrganizationFolderPage clickCancelOnDeletionConfirmationPopup() {
        getDriver().findElement(By.xpath("//button[@data-id='cancel']")).click();

        return new OrganizationFolderPage(getDriver());
    }

    public String getDeletionPopupText() {

        return getWait5().until(ExpectedConditions.visibilityOf(
                getDriver().findElement(By.className("jenkins-dialog__contents")))).getText();
    }

    public NewItemWithinFolderPage clickOnNewItemWithinFolder() {
        getDriver().findElement(By.xpath("//span[text()='New Item']/parent::a")).click();

        return new NewItemWithinFolderPage(getDriver());
    }

    public String getJobWithinFolderName(String jobName) {

        return getDriver().findElement(By.xpath(String.format("//span[text()='%s']", jobName))).getText();
    }

    public String getProjectName() {
        return header.getText();
    }

    public String getOrganizationFolderNameFromHeader() {

        return getWait5().until(ExpectedConditions.visibilityOf(header)).getText();
    }

    public String getOrganizationFolderIconTitleNameFromHeader() {
        getWait10().until(ExpectedConditions.visibilityOf(header));

        return headerIcon.getDomAttribute("title");

    }

}
