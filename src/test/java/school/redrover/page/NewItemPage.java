package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;

public class NewItemPage extends BasePage {

    public NewItemPage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage sendItemName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public PipelineConfigurationPage selectPipelineAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        return new PipelineConfigurationPage(getDriver());
    }

    public FolderConfigurationPage selectFolderAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        return new FolderConfigurationPage(getDriver());
    }

    public String getNewItemPageHeaderText() {
        return getDriver().findElement(By.xpath("//h1[text()='New Item']")).getText();
    }

    public String getNewItemPageURL() {
        return getDriver().getCurrentUrl();
    }
}
