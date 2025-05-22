package school.redrover.component;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BaseComponent;
import school.redrover.page.HomePage;
import school.redrover.page.search.SearchPage;

import java.time.Duration;

public class HeaderComponent extends BaseComponent {

    @FindAll({
            @FindBy(id = "jenkins-home-link"),
            @FindBy(xpath = "//*[@id='jenkins-home-link']"),
            @FindBy(css = "#jenkins-home-link")
    })
    private WebElement logo;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogoIcon() {
        int attempts = 0;
        while (attempts < 5) {
            try {
                getWait10().until(ExpectedConditions.elementToBeClickable(logo)).click();

                return new HomePage(getDriver());
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 5) throw e;
            }
        }

        return new HomePage(getDriver());
    }

    public HomePage goToHomePage() {
        WebElement logo = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins-home-link")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("main-panel")));
        return new HomePage(getDriver());
    }

    public HomePage clickLogo() {
        Actions actions = new Actions(getDriver());
        actions.pause(Duration.ofSeconds(1)).perform();

        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='jenkins-home-link']")));
        actions.moveToElement(logo).click().perform();

        return new HomePage(getDriver());
    }

    public SearchPage clickSearchButton() {
        getWait10().until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("button-open-command-palette")))
                .click();

        return new SearchPage(getDriver());
    }
}
