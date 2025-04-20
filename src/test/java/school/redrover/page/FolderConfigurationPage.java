package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;

public class FolderConfigurationPage extends BasePage {

    public FolderConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public FolderConfigurationPage sendDescription(String text) {
        getDriver().findElement(By.xpath("//textarea[@name='_.description']")).sendKeys(text);

        return this;
    }

    public FolderProjectPage clickSave() {
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        return new FolderProjectPage(getDriver());
    }

}
