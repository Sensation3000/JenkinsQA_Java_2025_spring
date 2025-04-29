package school.redrover.common;

import org.openqa.selenium.WebDriver;
import school.redrover.component.HeaderComponent;

public abstract class BasePage extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeader() {
        return new HeaderComponent(getDriver());
    }
}
