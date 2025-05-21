package school.redrover.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BaseComponent;
import school.redrover.common.BasePage;

public class SideMenuInPipelineComponent extends BaseComponent {

    public SideMenuInPipelineComponent(WebDriver driver){
        super(driver);
    }

    public <T extends BasePage> T clickItemOnSidePanelIn(String itemName, T resultPage){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasks']//a[.//span[text()='" + itemName + "']]")));

        return resultPage;
    }
}