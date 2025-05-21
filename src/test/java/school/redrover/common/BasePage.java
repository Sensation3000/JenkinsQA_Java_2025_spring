package school.redrover.common;

import org.openqa.selenium.WebDriver;
import school.redrover.component.HeaderComponent;
import school.redrover.component.SideMenuInFolderComponent;

public abstract class BasePage extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeader() {
        return new HeaderComponent(getDriver());
    }

    public SideMenuInFolderComponent getSideMenuInFolder(){
        return new SideMenuInFolderComponent(getDriver());
    }

    public String getCurrentUrl() {

        return getDriver().getCurrentUrl();
    }
}
