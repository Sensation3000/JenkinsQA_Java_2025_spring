package school.redrover.page.credentials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

public class NewCredentialsPage extends BasePage {
    @FindBy(name = "_.username")
    private WebElement usernameField;

    @FindBy(name = "_.password")
    private WebElement passwordField;

    @FindBy(name = "Submit")
    private WebElement createButton;

    public NewCredentialsPage (WebDriver driver) {
        super(driver);
    }

    public NewCredentialsPage sendItemCredentialsName(String name) {
        usernameField.sendKeys(name);

        return this;
    }

    public NewCredentialsPage sendItemCredentialsPassword(String password) {
        passwordField.sendKeys(password);

        return this;
    }

    public GlobalCredentialsPage clickCreateButton() {
        createButton.click();

        return new GlobalCredentialsPage(getDriver());
    }
}

