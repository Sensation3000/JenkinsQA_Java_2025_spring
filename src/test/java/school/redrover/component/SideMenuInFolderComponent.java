package school.redrover.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import school.redrover.common.BaseComponent;
import school.redrover.common.BasePage;
import school.redrover.page.newitem.NewItemPage;


public class SideMenuInFolderComponent extends BaseComponent {

    public SideMenuInFolderComponent(WebDriver driver){
        super(driver);
    }

    public <T extends BasePage> T clickItemOnSidePanel(String itemName, T resultPage) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasks']//a[.//span[text()='" + itemName + "']]"))).click();
        return resultPage;
    }

    public NewItemPage clickNewItem() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tasks']/div[3]/span/a"))).click();

        return new NewItemPage(getDriver());
    }


}
