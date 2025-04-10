package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class EnterAnItemNameTest extends BaseTest {

    @Test
    public void testCheckingThePresenceOfTheInputField() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]")).click();

        WebElement input = driver.findElement(By.className("jenkins-input"));

        Assert.assertTrue(input.isDisplayed());
        Assert.assertTrue(input.isEnabled());
    }
}
