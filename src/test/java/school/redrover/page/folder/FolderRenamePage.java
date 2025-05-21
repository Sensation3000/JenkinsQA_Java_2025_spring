package school.redrover.page.folder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class FolderRenamePage extends BasePage {

    public FolderRenamePage(WebDriver driver) {
        super(driver);
    }

    public FolderRenamePage sendNewName(String newName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("newName"))).clear();
        getDriver().findElement(By.name("newName")).sendKeys(newName);

        return this;
    }

    public FolderProjectPage clickRenameButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();


        return new FolderProjectPage(getDriver());
    }
}
