package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import static org.testng.Assert.assertTrue;


public class SetupBuildTest extends BaseTest {
;

    @Test
    public void NewNodeShouldBeOfflineTest()   {
        getDriver().findElement(By.xpath("//a[@href='computer/new']")).click();
        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys("Node1");
        getDriver().findElement(By.xpath("//label")).click();
        getDriver().findElement(By.xpath("//*[@id='ok']")).click();
        WebElement RemoteDirectory =getDriver().findElement(By.xpath("//input[@name='_.remoteFS']"));
        RemoteDirectory.click();
        RemoteDirectory.sendKeys("/var/jenkins_home/workspace/node1");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        assertTrue(getDriver().findElement(By.xpath("//a[@href='../computer/Node1/']")).isDisplayed(),
                "Node1 is not displayed" );
        assertTrue(getDriver().findElement(By.xpath("//div[@class='computer-caption']" +
                "[.//a[@href='/computer/Node1/']]/div")).getText().equals("(offline)"),
                "Node1 is not offline" );
    }
}
