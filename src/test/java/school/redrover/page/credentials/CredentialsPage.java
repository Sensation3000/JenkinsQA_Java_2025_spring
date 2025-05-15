package school.redrover.page.credentials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class CredentialsPage extends BasePage {

    @FindBy(css = "a[href*='credentials/store/folder']")
    private WebElement storedFolderName;

    @FindBy(css = "table:nth-child(4) td:nth-child(6) > a")
    private WebElement storeCredentialName;

    @FindBy(xpath = "//*[@id='main-panel']/table[2]/tbody/tr/td[2]/a")
    private WebElement systemButton;

    @FindBy(xpath = "//*[@id='main-panel']/table[2]/tbody/tr/td[2]/a")
    private WebElement system;

    public CredentialsPage(WebDriver driver) {super(driver);}

    public DomainFolderPage clickStoredFolderName() {
        getWait5().until(ExpectedConditions.elementToBeClickable(storedFolderName)).click();

        return new DomainFolderPage(getDriver());
    }

    public CreatedCredentialPage clickStoreCredentialName() {
        getWait5().until(ExpectedConditions.elementToBeClickable(storeCredentialName)).click();

        return new CreatedCredentialPage(getDriver());
    }

    public DomainFolderPage clickSystem() {
        new Actions(getDriver()).moveToElement(system).perform();
        systemButton.click();

        return new DomainFolderPage(getDriver());
    }
}
