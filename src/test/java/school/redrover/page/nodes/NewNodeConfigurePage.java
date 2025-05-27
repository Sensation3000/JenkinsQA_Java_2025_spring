package school.redrover.page.nodes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;

public class NewNodeConfigurePage extends BasePage {
    public NewNodeConfigurePage(WebDriver driver) {
        super(driver);
    }

    public NewNodeConfigurePage sendRemoteRootDirectory(String root) {
        getDriver().findElement(By.name("_.remoteFS")).sendKeys(root);

        return this;
    }

    public NodesPage clickSave() {
        getDriver().findElement(By.name("Submit")).click();

        return new NodesPage(getDriver());
    }
}
