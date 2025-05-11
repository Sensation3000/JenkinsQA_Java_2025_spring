package school.redrover.page.credentials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class GlobalCredentialsPage extends BasePage {
    @FindBy(css = "a[href*='newCredentials']")
    private WebElement addCredentialsButton;

    @FindBy(xpath = "//*[@id='main-panel']/table/tbody/tr/td[3]")
    private WebElement credentialsName;

    public GlobalCredentialsPage(WebDriver driver) {
        super(driver);
    }

    public String getCredentialsName() {

        return getWait10().until(ExpectedConditions.visibilityOf(credentialsName)).getText();
    }

    public NewCredentialsPage addCredentialsButton() {
        addCredentialsButton.click();

        return new NewCredentialsPage(getDriver());
    }
}