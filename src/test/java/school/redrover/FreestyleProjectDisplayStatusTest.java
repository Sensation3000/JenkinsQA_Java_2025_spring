package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestyleProjectDisplayStatusTest extends BaseTest {

    @Test
    public void testBuildLinksPresence() {
        WebDriver driver = getDriver();

        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.name("name")).sendKeys("test");
        driver.findElement(By.xpath("//span[text()='Freestyle project']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'task') and .//span[text()='Build Now']]"))
                .click();
        driver.findElement(By.xpath("//div[contains(@class, 'task') and .//span[text()='Status']]"))
                .click();

        Assert.assertEquals(
                driver.findElements(
                        By.className("permalink-item")).size(),
                4,
                "Should be present 4 build links");
    }
}
