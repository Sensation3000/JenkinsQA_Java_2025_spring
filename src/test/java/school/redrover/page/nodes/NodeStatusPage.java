package school.redrover.page.nodes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class NodeStatusPage extends BasePage {
    public NodeStatusPage(WebDriver driver) {
        super(driver);
    }

    public NodeStatusPage clickSetOffline() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }

    public NodeStatusPage clickSubmitMarkOffline() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        return this;
    }

    public String getStatusMessage() {
        return getDriver().findElement(By.className("message")).getText();
    }

    public NodeStatusPage clickDeleteAgent() {
        getDriver().findElement(By.xpath("//a[@data-title='Delete Agent']")).click();

        return this;
    }

    public NodesPage clickConfirmDelete() {
        getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();

        return new NodesPage(getDriver());
    }
}
