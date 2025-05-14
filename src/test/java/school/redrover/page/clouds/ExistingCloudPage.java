package school.redrover.page.clouds;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class ExistingCloudPage extends BasePage {

    @FindBy(css = "[data-title='Delete Cloud']")
    private WebElement deleteCloud;

    @FindBy(css = "[data-id='ok']")
    private WebElement yesDeleteButton;

    public ExistingCloudPage(WebDriver driver) {
        super(driver);
    }

    public ExistingCloudPage clickDeleteCloud() {
        getWait10().until(ExpectedConditions.visibilityOf(deleteCloud)).click();

        return new ExistingCloudPage(getDriver());
    }

    public CloudsPage clickYesButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(yesDeleteButton)).click();

        return new CloudsPage(getDriver());
    }
}
