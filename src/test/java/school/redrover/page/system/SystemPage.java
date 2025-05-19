package school.redrover.page.system;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

public class SystemPage extends BasePage {

    @FindBys({
            @FindBy(css = "h1"),
            @FindBy(xpath = "//*[@id='main-panel']/div[1]/div[1]/h1")
    })
    private WebElement h1;

    @FindBy(name = "_.numExecutors")
    private WebElement ofExecutors;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement buttonSave;

    @FindBy(name = "_.usageStatisticsCollected")
    private WebElement usageStatisticsCheckbox;

    public SystemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean isAccessSystemPage(){
        return h1.getText().equals("System");
    }
    public SystemPage clearOfExecutors(){
        ofExecutors.clear();

        return this;
    }

    public HomePage clickButtonSave(){
        buttonSave.click();

        return new HomePage(getDriver());
    }

    public SystemPage sendOfExecutors(String string){
        ofExecutors.sendKeys(string);

        return this;
    }

    public String getOfExecutors(){
        return ofExecutors.getDomProperty("value");
    }

    public SystemPage selectAnOptionAtGitHubApiUsageDropdownMenu(String visibleText) {
        TestUtils.scrollToItemWithJS(getDriver(), getDriver().findElement(By.id("github-api-usage")));

        Select GitHubApiUsageDropdown = new Select(getDriver().findElement(By.name("_.apiRateLimitChecker")));
        GitHubApiUsageDropdown.selectByVisibleText(visibleText);

        return this;
    }

    public HomePage clickOnSubmitButton() {
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.name("Submit")));

        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/view/all/newJob']")));

        return new HomePage(getDriver());
    }

    public SystemPage setUsageStatisticsCheckbox(boolean enable) {
        getWait5().until(ExpectedConditions.visibilityOf(usageStatisticsCheckbox));
        getWait5().until(ExpectedConditions.elementToBeClickable(usageStatisticsCheckbox));

        if (usageStatisticsCheckbox.isSelected() != enable) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", usageStatisticsCheckbox);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", usageStatisticsCheckbox);
        }

        return this;
    }

    public boolean isUsageStatisticsChecked() {
        return usageStatisticsCheckbox.isSelected();
    }
}
