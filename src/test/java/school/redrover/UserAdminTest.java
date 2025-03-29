package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class UserAdminTest extends BaseTest {

    @Test
    public void testUserAdmin() throws InterruptedException {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id='button-open-command-palette']")).click();

        WebElement textBox = driver.findElement(By.cssSelector("#command-bar"));
        textBox.sendKeys("admin");
        Thread.sleep(1000);
        textBox.sendKeys(Keys.RETURN);

        WebElement text = driver.findElement(By.xpath("//div[text()='Jenkins User ID: admin']"));
        String value = text.getText();

        Assert.assertEquals(value, "Jenkins User ID: admin");
    }
}
