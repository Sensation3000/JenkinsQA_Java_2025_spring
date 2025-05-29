package school.redrover.page.multibranch;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public class MultibranchProjectPage extends BasePage {
    Logger logger = Logger.getLogger(MultibranchProjectPage.class.getName());

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
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }
        throw new WebDriverException("Failed to navigate to job status for: " + jobName);
    }

    public boolean checkAllBranchesHaveEvents() {
        getWait10().until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(text(), 'Indexing')]")
                )
        );

        getDriver().navigate().refresh();
        List<WebElement> branchElements = getWait10().until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.xpath("//tbody//a//span"), 0)
        );

        List<String> branchNames = branchElements.stream()
                .map(WebElement::getText)
                .filter(name -> !name.isEmpty())
                .toList();

        for (int i = 1; i <= branchNames.size(); i++) {
            String branchName = branchNames.get(i - 1);
            try {
                WebElement branch = getWait10().until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("(//tbody//a//span)[" + i + "]/..")
                        )
                );

                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", branch);
                int attempts = 0;
                boolean hasEvents = false;
                while (attempts < 5) {
                    try {
                        hasEvents = new WebDriverWait(getDriver(), Duration.ofSeconds(20)).until(driver -> {
                            List<WebElement> events = driver.findElements(
                                    By.xpath("//h2//../ul//li")
                            );
                            return !events.isEmpty();
                        });
                        break;
                    } catch (TimeoutException e) {
                        getDriver().navigate().refresh();
                        attempts++;
                    }
                }

                if (!hasEvents) {
                    logger.info("No events found for branch: " + branchName);
                    return false;
                }

                getDriver().navigate().back();
                getWait10().until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("(//tbody//a//span)[1]/..")
                        )
                );
            } catch (TimeoutException e) {
                logger.info("Timeout when processing branch: " + branchName);
                return false;
            }
        }

        return true;
    }
}