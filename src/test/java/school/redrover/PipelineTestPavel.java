package school.redrover;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import org.testng.Assert;

public class PipelineTestPavel extends BaseTest {
    @Test
    public void createPipeline() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[@href ='newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("YourPipelineName");
        getDriver().findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]" +
                "/ul/li[2]/div[2]/div")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]")).click();

        Thread.sleep(5000);

        Assert.assertTrue(getDriver().getCurrentUrl().contains("/job/YourPipelineName/"),
                "URL does not contain expected path!");
    }
}
