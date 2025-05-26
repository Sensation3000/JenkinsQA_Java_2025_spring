package school.redrover.page.nodes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;

public class NewNodeCreatePage extends BasePage {


    public NewNodeCreatePage(WebDriver driver) {
        super(driver);
    }

    public NewNodeCreatePage sendNewNodeName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public NewNodeCreatePage selectPermanentAgent() {
        getDriver().findElement(By.xpath("//label[contains(text(),'Permanent Agent')]")).click();

        return this;
    }

    public NewNodeConfigurePage clickCreate() {
        getDriver().findElement(By.name("Submit")).click();

        return new NewNodeConfigurePage(getDriver());
    }
}
