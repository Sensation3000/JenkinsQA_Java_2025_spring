package school.redrover.page.multiconfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.freestyle.FreestyleProjectPage;

public class MultiConfigurationStatusPage extends BasePage {

    @FindBy(css = "a[href*='configure']")
    private WebElement buttonConfigure;

    @FindBy(xpath = "//span[text()='Build Now']/..")
    private WebElement leftSideMenuBuildNowButton;

    @FindBy(css = "a.task-link.task-link--active")
    private WebElement status;

    @FindBy(xpath = "//*[@href='lastBuild/']")
    private WebElement lastBuild;

    @FindBy(xpath = "//a[contains(@href, 'console')]")
    private WebElement consoleOutput;

    public MultiConfigurationStatusPage (WebDriver driver) {
        super(driver);
    }

    public MultiConfigurationStatusPage clickEnableButton() {
        getDriver().findElement(By.xpath("//*[@id='enable-project']/button")).click();

        return this;
    }

    public boolean projectDisabledMessageCheck() {

        try {
            return getDriver().findElement(By.xpath("//*[@id='enable-project']")).isDisplayed();
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean messageNotDisplayedCheck() {
        try {
            getWait5().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='enable-project']")));
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public MultiConfigurationConfigurePage clickConfigure() {
        buttonConfigure.click();

        return new MultiConfigurationConfigurePage(getDriver());
    }

    public MultiConfigurationStatusPage clickBuildNow() {
        getWait5().until(ExpectedConditions.elementToBeClickable(leftSideMenuBuildNowButton)).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar")));

        return this;
    }

    public MultiConfigurationStatusPage clickStatus() {
        status.click();

    return this;
    }

    public MultiConfigurationStatusPage clickLastBuild() {
        getWait10().until(ExpectedConditions.elementToBeClickable(lastBuild)).click();

        return this;
    }

    public MultiConfigurationStatusPage clickConsoleOutput() {
        consoleOutput.click();

        return this;
    }

    public String getTimestampsText() {
       return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Timestamps']"))).getText();
    }
}