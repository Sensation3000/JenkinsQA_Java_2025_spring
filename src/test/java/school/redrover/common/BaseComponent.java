package school.redrover.common;

import org.openqa.selenium.WebDriver;
import school.redrover.component.HeaderComponent;

public abstract class BaseComponent extends BaseModel {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }
}
