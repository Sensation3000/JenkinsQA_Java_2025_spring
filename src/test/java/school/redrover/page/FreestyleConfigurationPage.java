package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.BaseTest;

public class FreestyleConfigurationPage extends BasePage {

    public FreestyleConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleConfigurationPage sendDescription(String text) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(text);

        return this;
    }

    public FreestyleProjectPage clickSave() {
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        return new FreestyleProjectPage(getDriver());
    }
}
