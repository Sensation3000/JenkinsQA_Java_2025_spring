package school.redrover.page.credentials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

public class CredentialsPage extends BasePage {
    @FindBy(css = "a[href*='credentials/store/folder']")
    private WebElement storedFolderName;

    public CredentialsPage(WebDriver driver) {super(driver);}

    public DomainFolderPage clickStoredFolderName() {
        storedFolderName.click();

        return new DomainFolderPage(getDriver());
    }
}
