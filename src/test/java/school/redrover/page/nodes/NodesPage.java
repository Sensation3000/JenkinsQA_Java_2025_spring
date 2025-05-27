package school.redrover.page.nodes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import java.util.List;

public class NodesPage extends BasePage {

    public NodesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//a[@href='new']")
    private WebElement newNode;


    public NewNodeCreatePage clickNewNode() {
        newNode.click();

        return new NewNodeCreatePage(getDriver());
    }

    public NodeStatusPage clickNodeNameInTheList(String nodeName) {
        getDriver().findElement(By.xpath(String.format(
                "//a[contains(@href, '../computer/%s/')]", nodeName.replace(" ", "%20")))).click();

        return new NodeStatusPage(getDriver());
    }

    public boolean isNodeDisplayed(String nodeName) {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='node_" + nodeName + "']/td[2]/a"))).isDisplayed();
    }

    public List<String> getNodesNameList() {
        return getDriver().findElements(By.cssSelector(".jenkins-table__link")).stream()
                .map(WebElement::getText).toList();
    }
}
