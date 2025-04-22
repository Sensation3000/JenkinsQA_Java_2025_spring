package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ItemName2Test extends BaseTest {

    @Test
    public void testEmptyItemName() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        driver.findElement(By.className("hudson_model_FreeStyleProject")).click();

        WebElement errorMessage = driver.findElement(By.id("itemname-required"));

        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertEquals(errorMessage.getCssValue("color"), "rgba(230, 0, 31, 1)");
        Assert.assertFalse(driver.findElement(By.id("ok-button")).isEnabled());
    }
}
