package school.redrover.page.folder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

import java.util.List;

public class FolderConfigurationPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'Display Name')]/a")
    private WebElement questionMarkButton;

    @FindBy(name = "Apply")
    private WebElement  applyButton;

    @FindBy(css = "button[name='Submit']")
    private WebElement  saveButton;

    @FindBy(id = "notification-bar")
    private WebElement savedNotification;

    @FindBy(xpath = "//input[@checkdependson]")
    private WebElement displayNameInput;

    @FindBy (xpath = "//*[@id='description']/div[1]")
    private WebElement description;

    public FolderConfigurationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public FolderConfigurationPage sendDisplayName(String text) {
        getDriver().findElement(By.xpath("//input[@checkdependson]")).sendKeys(text);

        return this;
    }

    public String getDisplayName(){
        return displayNameInput.getAttribute("value");
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
        getDriver().findElement(By.name("Submit")).click();

        return  new FolderProjectPage(getDriver());
    }

    public boolean isSaveButtonEnabled() {
       return  saveButton.isEnabled();
    }

    public boolean isApplyButtonEnabled() {
        return  applyButton.isEnabled();
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

    public FolderConfigurationPage clickAddDescriptionButton(){
        getDriver().findElement(By.id("description-link")).click();

        return this;
    }

    public FolderConfigurationPage fillInDescriptionBox(String description){
        getDriver().findElement(By.name("description")).sendKeys(description);

        return this;
    }

    public List<String> getAllHealthMetricsTitles() {
        return List.of(getTitleHealthMetrics(), getTextDropdownHealthMetrics());
    }
}

