package school.redrover.page.credentials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

public class UpdateCredentialsPage extends BasePage {
    @FindBy(name = "_.username")
    private WebElement updatedUsernameField;

    @FindBy(name = "_.password")
    private WebElement updatedPasswordField;

    @FindBy(css = ".hidden-password-update-btn")
    private WebElement changePasswordButton;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    public UpdateCredentialsPage(WebDriver driver) {super (driver);}

    public CreatedCredentialPage clickSaveButton() {
        saveButton.click();

        return new CreatedCredentialPage(getDriver());
    }

    public UpdateCredentialsPage clickChangePasswordButton() {
        changePasswordButton.click();

        return this;
    }

    public UpdateCredentialsPage sendItemCredentialsName(String updatedName) {
        updatedUsernameField.clear();
        updatedUsernameField.sendKeys(updatedName);

        return new UpdateCredentialsPage(getDriver());
    }

    public UpdateCredentialsPage sendItemCredentialsPassword(String updatedPassword) {
        updatedPasswordField.clear();
        updatedPasswordField.sendKeys(updatedPassword);

        return new UpdateCredentialsPage(getDriver());
    }
}
