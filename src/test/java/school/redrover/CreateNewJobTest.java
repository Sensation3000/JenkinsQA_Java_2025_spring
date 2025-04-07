package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewJobTest extends BaseTest {

    @Test
    public void testNewJob() {
        getDriver().findElement(By.xpath(
                "//*[@id=\"main-panel\"]/div[2]/div/section[1]/ul/li/a/span[1]")).click();
        getDriver().findElement(By.xpath(
                "//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[1]/div[2]/div")).click();
        String message = "» This field cannot be empty, please enter a valid name";
        Assert.assertEquals(getDriver().findElement(By.xpath(
                "//*[@id=\"itemname-required\"]")).getText(), message);
    }

}
