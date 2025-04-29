package school.redrover.page.error;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class ErrorPage extends BasePage {

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText();
    }

    public String getErrorText() {
        return getDriver().findElement(By.xpath("//*[@id='main-panel']/p")).getText();
    }

    public String getHeadingText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel h1"))).getText();
    }
}
