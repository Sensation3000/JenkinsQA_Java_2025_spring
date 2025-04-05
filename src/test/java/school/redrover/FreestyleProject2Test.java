package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestyleProject2Test extends BaseTest {

    @Test
    public void testCreate() {
        final String projectName = "Monday";

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//span[text()='Create a job']")).click();
        driver.findElement(By.id("name")).sendKeys("Monday");
        driver.findElement(By.xpath("//span[text()='Freestyle project']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("description")).sendKeys("Freestyle project");
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(
                driver.findElement(By.xpath("//h1[text()='Monday']")).getText(),
                projectName);
    }
}
