package school.redrover.page.clouds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class NewCloudPage extends BasePage {

    @FindBy(xpath = "//label[contains(text(), 'Windows')]")
    private WebElement AWSCloud;

    @FindBy(css = ".jenkins-table__link")
    private WebElement cloudName;

    @FindBy(xpath = "//button[contains(text(), 'Save')]")
    private WebElement saveButton;

    public NewCloudPage(WebDriver driver) {
        super(driver);
    }

    public NewCloudPage sendCloudName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public NewCloudPage selectCloudType() {
        getWait10().until(ExpectedConditions.elementToBeClickable(AWSCloud)).click();

        return this;
    }

    public NewCloudPage clickCreateButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("ok"))).click();

        return this;
    }

    public NewCloudPage clickSaveButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(saveButton)).click();

        return this;
    }

    public String getCreatedCloudName() {
        return getWait10().until(ExpectedConditions.visibilityOf(cloudName)).getText();
    }
}
