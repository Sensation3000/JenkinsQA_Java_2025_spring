package school.redrover.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BaseComponent;

public class CommonComponent extends BaseComponent {

    public CommonComponent(WebDriver driver) {
        super(driver);
    }

    public boolean doesUrlContainJobEndpoint() {
        return getWait5().until(ExpectedConditions.urlContains("/job"));
    }

    public boolean isHeadingDisplayed() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(By.id("general"))).isDisplayed();
    }
}
