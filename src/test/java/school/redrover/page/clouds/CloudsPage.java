package school.redrover.page.clouds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.common.BasePage;
import school.redrover.page.plugins.PluginsPage;

import java.time.Duration;
import java.util.List;

public class CloudsPage extends BasePage {

    public CloudsPage(WebDriver driver) {super(driver);}

    public ExistingCloudPage clickCloudName() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement cloudLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".jenkins-table__link")));
        cloudLink.click();

        return new ExistingCloudPage(getDriver());
    }

    public PluginsPage clickInstallAPlugin() {
        List<WebElement> buttons1 = getDriver().findElements(By.cssSelector(".empty-state-section-list li a"));
        for (WebElement button : buttons1) {
            if (button.getText().equals("Install a plugin")) {
                button.click();
                break;
            }
        }

        return new PluginsPage(getDriver());
    }

    public NewCloudPage clickCreateNewCloud() {
        List<WebElement> buttons2 = getDriver().findElements(By.cssSelector(".empty-state-section-list li a"));
        for (WebElement button : buttons2) {
            if (button.getText().equals("New cloud")) {
                button.click();
                break;
            }
        }

        return new NewCloudPage(getDriver());
    }

    public String getEmptyStatusText() {

        return getWait10()
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".empty-state-section p")))
                .getText();
    }
}
