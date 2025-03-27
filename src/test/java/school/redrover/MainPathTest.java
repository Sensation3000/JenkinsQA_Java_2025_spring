package school.redrover;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class MainPathTest extends BaseTest {
    private final String message = "Path is wrong"; 
    @Test
    public void MyViewsShouldBeLinkToAllViewTest() {
       getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
       assertTrue(getDriver().getCurrentUrl().contains("/me/my-views/view/all/"),
               message);
    }
    @Test
    public void ManageShouldBeLinkToManage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        assertTrue(getDriver().getCurrentUrl().contains("/manage"),
                message);
    }
    @Test
    public void BuildHistoryShouldBeLinkToAllBuildsTest() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();
        assertTrue(getDriver().getCurrentUrl().contains("/view/all/builds"),
                message);
    }
    @Test
    public void NewItemShouldBeLinkToAllNewJobTest() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        assertTrue(getDriver().getCurrentUrl().contains("/view/all/newJob"),
                message);
    }
    @Test
    public void CreateAJobShouldBeLinkToAllNewJobTest() {
        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        assertTrue(getDriver().getCurrentUrl().contains("/newJob"),
                message);
    }
    @Test
    public void SetupaAgentShouldBeLinkToNewJobTest() {
        getDriver().findElement(By.xpath("//a[@href='computer/new']")).click();
        assertTrue(getDriver().getCurrentUrl().contains("/computer/new"),
                message);
    }
    @Test
    public void ConfugureACloudShouldBeLinkToCloudTest() {
        getDriver().findElement(By.xpath("//a[@href='cloud/']")).click();
        assertTrue(getDriver().getCurrentUrl().contains("/cloud"),
                message);
    }
    @Test
    public void LearnShouldBeLinkToLearn()  {
        getDriver().findElement(By.xpath("//a[@target='_blank']")).click();
        getDriver().switchTo().window(new ArrayList<>(getDriver()
                .getWindowHandles())
                .get((new ArrayList<>(getDriver()
                        .getWindowHandles()).indexOf(getDriver()
                        .getWindowHandle()) + 1) % getDriver().getWindowHandles().size()));
        assertEquals(getDriver().getCurrentUrl(),
                "https://www.jenkins.io/doc/book/scaling/architecting-for-scale/#distributed-builds-architecture");
    }


}
