package school.redrover.page.multibranch;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultibranchProjectPage extends BasePage {

    @FindBy(id = "view-message")
    private WebElement descriptionText;

    @FindAll({
            @FindBy(xpath = "//*[@id='main-panel']/h1"),
            @FindBy(css = "#main-panel > h1"),
            @FindBy(xpath = "(//h1)")
    })
    private WebElement h1;

    public MultibranchProjectPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchConfigurationPage clickConfigureLeftSidePanel() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@id='side-panel']/div/div)[2]"))).click();

        return new MultibranchConfigurationPage(getDriver());
    }

    public String getProjectName() {
        return h1.getText();
    }

    public String getDescription() {
        return descriptionText.getText();
    }

    public HomePage deleteMultiBranchPipeline(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-title='Delete Multibranch Pipeline']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-id='ok']"))).click();

        return new HomePage(getDriver());
    }

    public MultibranchProjectPage cancelDeletionMultiBranchPipeline(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-title='Delete Multibranch Pipeline']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-id='cancel']"))).click();

        return this;
    }

    public MultibranchProjectPage navigateToJobStatus(String jobName) {
        for (int i = 0; i < 5; i++) {
            try {
                getWait10()
                        .until(ExpectedConditions
                                .elementToBeClickable(By.xpath("//a[text()='" + jobName + "']"))).click();
                return this;
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        }
        throw new WebDriverException("Failed to navigate to job status for: " + jobName);
    }

    public List<String> getAllBranchNames() {
        getWait10()
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Indexing')]")));
        getDriver().navigate().refresh();

        List<WebElement> elements = getWait10().until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.xpath("//tbody//a//span"), 0));

        return elements.stream()
                .map(WebElement::getText)
                .filter(name -> !name.isEmpty())
                .toList();
    }

    public boolean checkEachBranchHasEvents(List<String> branchNames) {
        List<Boolean> isEachBranchHaveEvents = new ArrayList<>();

        for (int i = 1; i <= branchNames.size(); i++) {
            WebElement branch = getWait10().until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("(//tbody//a//span)[" + i + "]/..")
                    )
            );
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", branch);

            boolean hasEvents = waitForEventsWithRefresh();

            isEachBranchHaveEvents.add(hasEvents);

            getDriver().navigate().back();
            getWait10().until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody//a//span"))
            );
        }

        return isEachBranchHaveEvents.stream().allMatch(Boolean::booleanValue);
    }

    private boolean waitForEventsWithRefresh() {
        int attempts = 0;
        while (attempts < 5) {
            try {
                return getWait10().until(driver -> {
                    List<WebElement> events = driver.findElements(
                            By.xpath("//ul[@class='permalinks-list']//li[contains(@class, 'permalink-item')]")
                    );
                    return !events.isEmpty();
                });
            } catch (TimeoutException e) {
                System.out.println("Events not found, refreshing page. Attempt: " + (attempts + 1));
                getDriver().navigate().refresh();
                attempts++;
            }
        }
        return false;
    }
}