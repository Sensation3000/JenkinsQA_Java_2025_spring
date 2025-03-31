package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItem2Test extends BaseTest {

    @Test
    public void newNewFreestyleProject() throws InterruptedException {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        driver.findElement(By.id("name")).sendKeys("New freestyle project");
        driver.findElement((By.xpath("//span[text()='Freestyle project']"))).click();
        driver.findElement(By.id("ok-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(driver.findElement(
                By.xpath("//h1[text()='New freestyle project']")).getText(),
                "New freestyle project");
    }

    @Test
    public void newNewPipeline() throws InterruptedException {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        driver.findElement(By.id("name")).sendKeys("New pipline");
        driver.findElement((By.xpath("//span[text()='Pipeline']"))).click();
        driver.findElement(By.id("ok-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(driver.findElement(
                        By.xpath("//h1[text()='New pipline']")).getText(),
                "New pipline");
    }
}
