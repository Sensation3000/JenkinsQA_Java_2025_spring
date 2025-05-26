package school.redrover.page.multibranch;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;

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
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='./configure']"))).click();

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
        for (int i = 0; i < 3; i++) {
            try {
                WebElement jobLink = getWait10()
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + jobName + "']")));
                jobLink.click();
                return this;
            } catch (StaleElementReferenceException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("Failed to click job link after 3 attempts due to stale element");
    }

    public List<String> getAllBranchNames() throws InterruptedException {
        Thread.sleep(60000);
        getDriver().navigate().refresh();

        List<WebElement> elements = getWait10().until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.xpath("//tbody//a//span"), 0));

        return elements.stream()
                .map(WebElement::getText)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
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

            boolean hasEvents = getWait10().until(driver -> {
                List<WebElement> events = driver.findElements(
                        By.xpath("//ul[@class='permalinks-list']//li[contains(@class, 'permalink-item')]")
                );

                if (events.isEmpty()) {
                    driver.navigate().refresh();
                    return null;
                }

                return true;
            });

            isEachBranchHaveEvents.add(hasEvents);

            getDriver().navigate().back();
            getWait10().until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody//a//span"))
            );
        }

        return isEachBranchHaveEvents.stream().allMatch(Boolean::booleanValue);
    }
}