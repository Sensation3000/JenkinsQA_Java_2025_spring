package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ClickabilityCreateAJobButtonTest extends BaseTest {

    @Test
    public void testClickabilityCreateJob() {
        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//*[text()='Create a job']")).click();

        String resultCreateAJobButton = driver.findElement(By.xpath("//*[@id=\"add-item-panel\"]/h1")).getText();
        Assert.assertEquals(resultCreateAJobButton, "New Item");
    }
}
