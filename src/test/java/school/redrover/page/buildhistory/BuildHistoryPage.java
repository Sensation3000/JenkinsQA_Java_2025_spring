package school.redrover.page.buildhistory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.freestyle.FreestyleProjectPage;

import java.util.List;
import java.util.stream.Collectors;

public class BuildHistoryPage extends BasePage {

    @FindBy(css = "a[href*='Del']")
    private WebElement deleteButton;

    @FindBy(name = "Submit")
    private WebElement deleteSubmitButton;

    @FindBy(tagName = "h1")
    private WebElement buildHistoryText;

    @FindBy(xpath = "//table[@id='projectStatus']/tbody")
    private WebElement buildHistoryTable;

    @FindBy(xpath = "//table[@id='projectStatus']/tbody/tr")
    private List<WebElement> buildHistoryTableRows;

    @FindBy(xpath = "//table[@id='projectStatus']/thead/tr/th")
    private List<WebElement> buildHistoryTableHeaders;

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    public String getBuildHistoryText() {
        return getWait5().until(ExpectedConditions.visibilityOf(buildHistoryText)).getText();
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

    public boolean isBuildHistoryEmpty() {

        return buildHistoryTableRows.isEmpty();
    }

    public List<String> getBuildHistoryHeaders() {

        return buildHistoryTableHeaders.stream()
                .map(WebElement::getText)
                .map(text -> text.replace("↑", "").trim())  // remove sort arrow
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());
    }
}