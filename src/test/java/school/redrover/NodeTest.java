package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NodeTest extends BaseTest {
    private static final By NEW_NODE_NAME = By.xpath("//input[@id='name']");

    @Test
    public void testNewNode() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[contains(.,'Manage Jenkins')]")).click();
        getDriver().findElement(By.xpath("//dt[.='Nodes']")).click();
        getDriver().findElement(By.cssSelector(".jenkins-button--primary")).click();
        getDriver().findElement(NEW_NODE_NAME).click();
        getDriver().findElement(NEW_NODE_NAME).sendKeys("New Node");
        getDriver().findElement(By.tagName("label")).click();
        getDriver().findElement(By.xpath("//button[@id='ok']")).click();
        Thread.sleep(1000);
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();
        getDriver().findElement(By.xpath("//a[.='Nodes']")).click();
        WebElement newNode = getDriver().findElement(By.xpath("//a[.='New Node']"));

        Assert.assertTrue(newNode.isDisplayed());
    }
}
