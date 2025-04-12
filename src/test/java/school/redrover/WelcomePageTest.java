package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @Test
    public void testCreateJobButton() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a[href='newJob']")).click();
        WebElement itemsList = driver.findElement(By.id("items"));

        Assert.assertTrue(itemsList.isDisplayed(), "The list of job item type should be visible.");
    }
}
