package school.redrover.page.credentials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.folder.FolderProjectPage;

public class GlobalCredentialsPage extends BasePage {
    @FindBy(css = "a[href*='newCredentials']")
    private WebElement addCredentialsButton;

    @FindBy(xpath = "//*[@id='main-panel']/table/tbody/tr/td[3]")
    private WebElement credentialsName;

    @FindBy(css = "svg[tooltip='Update']")
    private WebElement updateTooltip;

    @FindBy(css = "a[href*='job'].jenkins-table__link")
    private WebElement projectName;

    @FindBy(tagName = "td")
    private WebElement emptyCredentialsStatus;

    public GlobalCredentialsPage(WebDriver driver) {
        super(driver);
    }

    public FolderProjectPage clickProjectName() {
        getWait5()
                .until(ExpectedConditions.elementToBeClickable(projectName)).click();

        return new FolderProjectPage(getDriver());
    }

    public String getEmptyCredentialsStatus() {

        return getWait10().until(ExpectedConditions.visibilityOf(emptyCredentialsStatus)).getText();
    }

    public String getCredentialsName() {

        return getWait10().until(ExpectedConditions.visibilityOf(credentialsName)).getText();
    }

    public NewCredentialsPage addCredentialsButton() {
        addCredentialsButton.click();

        return new NewCredentialsPage(getDriver());
    }

    public UpdateCredentialsPage clickUpdateTooltip() {
        updateTooltip.click();

        return new UpdateCredentialsPage(getDriver());
    }
}