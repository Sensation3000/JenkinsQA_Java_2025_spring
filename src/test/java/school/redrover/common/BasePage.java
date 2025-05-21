package school.redrover.common;

import org.openqa.selenium.WebDriver;
import school.redrover.component.HeaderComponent;
import school.redrover.component.SideMenuInFolderComponent;
import school.redrover.component.SideMenuInHomePageComponent;

public abstract class BasePage extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeader() {
        return new HeaderComponent(getDriver());
    }

    public SideMenuInFolderComponent getSideMenuInFolder() {
        return new SideMenuInFolderComponent(getDriver());
    }

    public  SideMenuInHomePageComponent getSideMenuInHomePage(){
        return new SideMenuInHomePageComponent(getDriver());
    }

    public String getCurrentUrl() {

        return getDriver().getCurrentUrl();
    }
}
