package school.redrover.page.credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class CreatedCredentialPage extends BasePage {

    @FindBy(name = "Submit")
    private WebElement yesButton;

    public CreatedCredentialPage(WebDriver driver) {super(driver);}

    public String getCreatedCredentialName() {
        By credentialNameLocator = By.xpath("//*[@id='main-panel']/h1");
        int attempts = 0;
        while (attempts < 3) {
            try {
                return getWait10()
                        .until(ExpectedConditions.visibilityOfElementLocated(credentialNameLocator))
                        .getText();
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException();
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
