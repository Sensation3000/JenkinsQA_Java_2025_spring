package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;

public class FreestyleConfigurationPage extends BasePage {

    public FreestyleConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleConfigurationPage addDescription(String text) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(text);

        return this;
    }

    public FreestyleProjectPage clickSaveButton() {
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        return new FreestyleProjectPage(getDriver());
    }
}
