package school.redrover.page.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class UserAdminPage extends BasePage {

    @FindBy(css = ".jenkins-app-bar h1")
    private WebElement adminUserHeader;

    @FindAll({
            @FindBy(xpath = "//*[@id='description']/div"),
            @FindBy(className = "jenkins-!-margin-bottom-3"),
            @FindBy(id = "description")
    })
    private WebElement adminUserDescription;

    @FindBy(id = "description-link")
    private WebElement editDescriptionButton;

    @FindBy(name = "description")
    private WebElement descriptionField;

    @FindBy(name = "Submit")
    private WebElement saveDescriptionButton;

    public UserAdminPage(WebDriver driver) {
        super(driver);
    }

    public String getAdminUserHeaderText() {
        return getWait10().until(
                ExpectedConditions.visibilityOf(adminUserHeader)).getText();
    }

    public String getAdminUserDescription() {
        getWait5().until(ExpectedConditions.visibilityOf(adminUserHeader));

        return adminUserDescription.getText();
    }

    public UserAdminPage clickEditDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(editDescriptionButton)).click();

        return this;
    }

    public UserAdminPage clearAdminUserDescription() {
        getWait5().until(ExpectedConditions.visibilityOf(descriptionField)).clear();

        return this;
    }

    public UserAdminPage setAdminUserDescriptionAndSave(String userDescription) {
        descriptionField.sendKeys(userDescription);
        getWait5().until(ExpectedConditions.elementToBeClickable(saveDescriptionButton)).click();

        return this;
    }
}
