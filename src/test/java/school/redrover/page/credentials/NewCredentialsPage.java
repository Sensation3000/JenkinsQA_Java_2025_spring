package school.redrover.page.credentials;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.common.BasePage;

import java.time.Duration;

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
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (TimeoutException e) {
        }

        return new GlobalCredentialsPage(getDriver());
    }
}

