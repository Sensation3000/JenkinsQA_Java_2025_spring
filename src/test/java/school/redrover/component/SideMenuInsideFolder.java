package school.redrover.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import school.redrover.common.BaseComponent;
import school.redrover.common.BasePage;


public class SideMenuInsideFolder extends BaseComponent {

    public SideMenuInsideFolder(WebDriver driver){
        super(driver);
    }

    public <T extends BasePage> T clickItemOnSidePanel(String itemName, T resultPage) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasks']//a[.//span[text()='" + itemName + "']]"))).click();
        return resultPage;
    }

}
