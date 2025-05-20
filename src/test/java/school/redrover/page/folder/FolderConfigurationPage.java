package school.redrover.page.folder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;
import school.redrover.component.HeaderComponent;
import school.redrover.page.HomePage;

public class FolderConfigurationPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'Display Name')]/a")
    private WebElement questionMarkButton;
    @FindBy(name = "Apply")
    private WebElement  applyButton;

    @FindBy(xpath = "//*[@id='notification-bar' and contains(text(),'Saved')]")
    private WebElement savedNotification;

    public FolderConfigurationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public FolderConfigurationPage sendDisplayName(String text) {
        getDriver().findElement(By.xpath("//input[@checkdependson]")).sendKeys(text);

        return this;
    }

    public FolderConfigurationPage clearDisplayName() {
        getDriver().findElement(By.xpath("//input[@checkdependson]")).clear();

        return this;
    }

    public FolderConfigurationPage sendDescription(String text) {
        getDriver().findElement(By.xpath("//textarea[@name='_.description']")).sendKeys(text);

        return this;
    }

    public FolderProjectPage clickSave() {
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector("button[name='Submit']")));

        return new FolderProjectPage(getDriver());
    }

    public String clickApplyForSavedNotification() {
        applyButton.click();
        getWait10().until(ExpectedConditions.visibilityOf(savedNotification));

        return savedNotification.getText();
    }

    public HomePage saveAndReturnToHomePage() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link"))).click();

        return new HomePage(getDriver());
    }

    public boolean isQuestionMarkIconEnabled(){
        return questionMarkButton.isEnabled();
    }

    public FolderConfigurationPage clickHealthMetrics(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-section-id='health-metrics']"))).click();

        return this;
    }

    public String getTitleHealthMetrics(){
        return getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='health-metrics']"))).getText();
    }
    public String getTextDropdownHealthMetrics(){
        return getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[@id='main-panel']/form/div[1]/section[1]/div[2]/div[1]/button"))).getText();
    }

}

