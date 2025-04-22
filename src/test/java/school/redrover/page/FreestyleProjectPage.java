package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import java.util.List;

public class FreestyleProjectPage extends BasePage {

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        return getDriver().findElement(By.className("page-headline")).getText();
    }

    public String getDescription() {
        return getDriver().findElement(By.cssSelector("#description > div")).getText();
    }

    public FreestyleProjectPage clickEditDescriptionButton() {
        getDriver().findElement(By.id("description-link")).click();

        return this;
    }

    public FreestyleProjectPage sendDescription(String text) {
        getDriver().findElement(By.cssSelector("textarea[name='description']")).clear();
        getDriver().findElement(By.cssSelector("textarea[name='description']")).sendKeys(text);

        return this;
    }

    public FreestyleProjectPage clickSave() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }

    public FreestyleProjectPage clickLeftSideMenuRename() {
        getDriver().findElement(By.xpath("//span[text()='Rename']/..")).click();

        return this;
    }

    public FreestyleProjectPage sendName(String text) {
        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys(text);

        return this;
    }

    public FreestyleProjectPage clickRename() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }

    public FreestyleProjectPage clickLeftSideMenuDelete() {
        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();

        return this;
    }

    public HomePage clickPopUpYesDeleteProject() {
        getDriver().findElement(By.cssSelector("[data-id='ok']")).click();

        return new HomePage(getDriver());
    }

    public FreestyleConfigurationPage clickConfigure() {
        getDriver().findElement(By.cssSelector("a[href*='configure']")).click();

        return new FreestyleConfigurationPage(getDriver());
    }

    public String getDisabledWarningMessageText() {
        String fullText = getDriver().findElement(By.xpath("//div[@class='warning']/form")).getText();

        return fullText.split("\\n")[0];
    }

    public FreestyleProjectPage clickEnableButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='warning']/form")));

        return this;
    }

    public boolean isWarningMessageDisplayed() {
        return getDriver().findElement(By.xpath("//div[@class='warning']/form")).isDisplayed();
    }

    public List<WebElement> getWarningMessageList() {
       return getDriver().findElements(By.xpath("//div[@class='warning']/form"));
    }
}