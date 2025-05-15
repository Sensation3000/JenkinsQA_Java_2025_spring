package school.redrover.page.credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class CreatedCredentialPage extends BasePage {
    @FindBy(tagName = "h1")
    private WebElement credentialName;

    @FindBy(name = "Submit")
    private WebElement yesButton;

    public CreatedCredentialPage(WebDriver driver) {super(driver);}

    public String getCreatedCredentialName() {

        return getWait10().until(ExpectedConditions.visibilityOf(credentialName)).getText();
    }

    public CreatedCredentialPage clickDeleteCredentialButton() {
        getDriver().findElements(By.cssSelector(".task")).stream()
                .filter(button -> "Delete".equals(button.getText()))
                .findFirst()
                .ifPresent(WebElement::click);

        return new CreatedCredentialPage(getDriver());
    }
    public GlobalCredentialsPage clickYesButton() {
        yesButton.click();

        return new GlobalCredentialsPage(getDriver());
    }
}
