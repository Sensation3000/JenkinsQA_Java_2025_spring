package school;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class SunflowersPathTest extends BaseTest {
    @Test
    public void MyViewsShouldBeLinkToAllViewTest() {
       getDriver().findElement(By.xpath("//a[@href='/me/my-views']    ")).click();
       Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/me/my-views/view/all/");
    }
    @Test
    public void testAddDescription() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']    ")).click();
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/me/my-views/view/all/");
    }

}
