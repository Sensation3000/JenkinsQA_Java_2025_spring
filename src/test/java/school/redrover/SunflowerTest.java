package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SunflowerTest extends BaseTest {
    @Test
    public void testCreatePipeline (){

        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div/section[1]/ul/li/a")).click();
        getDriver().findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[2]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("New Pipeline");
        getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]")).click();

        WebElement title = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[1]/div[1]/h1"));

        Assert.assertEquals(title.getText(), "New Pipeline");
    }
}
