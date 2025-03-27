package school;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import static org.testng.Assert.assertEquals;



public class SunflowersPathTest extends BaseTest {
     private static String BASEURL = "http://localhost:8080";
    @Test
    public void MyViewsShouldBeLinkToAllViewTest() {
       getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
       assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/me/my-views/view/all/");
    }
    @Test
    public void ManageShouldBeLinkToManage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/manage/");
    }
    @Test
    public void BuildHistoryShouldBeLinkToAllBuildsTest() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();
        assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/view/all/builds");
    }
    @Test
    public void NewItemShouldBeLinkToAllNewJobTest() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/view/all/newJob");
    }
    @Test
    public void CreateAJobShouldBeLinkToAllNewJobTest() {
        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/newJob");
    }
    @Test
    public void SetupaAgentShouldBeLinkToNewJobTest() {
        getDriver().findElement(By.xpath("//a[@href='computer/new']")).click();
        assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/computer/new");
    }
    @Test
    public void CinfugureACloudShouldBeLinkToCloudTest() {
        getDriver().findElement(By.xpath("//a[@href='cloud/']")).click();
        assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/cloud/");
    }
    @Test
    public void LearnShouldBeLinkToLearn() {
        getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/section[2]/ul/li[3]/a/span[1]")).click();
        assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/doc/book/scaling/architecting-for-scale/#distributed-builds-architecture");
    }


}
