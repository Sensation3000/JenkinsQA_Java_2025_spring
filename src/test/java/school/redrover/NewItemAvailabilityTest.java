package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemAvailabilityTest extends BaseTest {

    @Test
    public void testCheck() {
        String expectedUrl = "http://localhost:8080/view/all/newJob";
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]")).click();

        String newItemUrl = driver.getCurrentUrl();

        Assert.assertEquals(newItemUrl, expectedUrl);
    }
}
