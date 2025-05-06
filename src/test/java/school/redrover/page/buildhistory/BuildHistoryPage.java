package school.redrover.page.buildhistory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.freestyle.FreestyleProjectPage;

public class BuildHistoryPage extends BasePage {

    @FindBy(css = "a[href*='Del']")
    private WebElement deleteButton;

    @FindBy(name = "Submit")
    private WebElement deleteSubmitButton;



    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    public String isBuildHistoryText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))).getText();
    }

    public String buildProjectText(String name) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[text()='%s']/..".formatted(name)))).getText();
    }

    public String getTextNoBuilds() {

        return getDriver().findElement(By.xpath("//*[@id='no-builds']")).getText();
    }

    public BuildHistoryPage clickCollectNow() {
        getDriver().findElement(By.xpath("//a[@data-task-post='true']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.className("app-builds-container__items"))).click();

        return this;
    }

    public FreestyleProjectPage clickDeleteBuild() {
        deleteButton.click();
        deleteSubmitButton.click();

        return new FreestyleProjectPage(getDriver());
    }
}