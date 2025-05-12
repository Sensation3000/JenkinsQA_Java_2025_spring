package school.redrover.page.credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

public class CredentialsPage extends BasePage {

    @FindBy(css = "a[href*='credentials/store/folder']")
    private WebElement storedFolderName;

    @FindBy(xpath = "//*[@id='main-panel']/table[2]/tbody/tr/td[2]/a")
    private WebElement systemButton;

    @FindBy(xpath = "//*[@id='main-panel']/table[2]/tbody/tr/td[2]/a")
    private WebElement system;

    public CredentialsPage(WebDriver driver) {super(driver);}

    public DomainFolderPage clickStoredFolderName() {
        storedFolderName.click();

        return new DomainFolderPage(getDriver());
    }

    public DomainFolderPage clickSystem() {
        new Actions(getDriver()).moveToElement(system).perform();
        systemButton.click();

        return new DomainFolderPage(getDriver());
    }
}
