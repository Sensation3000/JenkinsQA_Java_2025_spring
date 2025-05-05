package school.redrover.page.clouds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.common.BasePage;

import java.util.List;

public class ExistingCloudPage extends BasePage {

    public ExistingCloudPage (WebDriver driver) {super(driver);}

    public ExistingCloudPage clickDeleteCloud() {
        List<WebElement> buttons2 = getDriver().findElements(By.cssSelector(".task"));
        for (WebElement button : buttons2) {
            if (button.getText().equals("Delete Cloud")) {
               button.click();
               break;
            }
        }

        return new ExistingCloudPage(getDriver());
    }

    public CloudsPage clickYesButton() {
        getDriver().findElement(By.cssSelector(".jenkins-button--primary")).click();

        return new CloudsPage(getDriver());
    }
}
