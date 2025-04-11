package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class WelcomePageTest extends BaseTest {

    @Test
    public void testAddDescription() {
        final String description = "Test Project Description";
        WebDriver driver = getDriver();

        driver.findElement(By.id("description-link")).click();
        driver.findElement(By.name("description")).sendKeys(description);
        driver.findElement(By.name("Submit")).click();
        String actualDescription = getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#description>div:not(.jenkins-buttons-row)"))).getText();

        Assert.assertEquals(actualDescription, description);

    }
}
