package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class GroupJavaEST extends BaseTest {

    @Test
    public void testCreatePipeline() {
        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//a[@href ='newJob']")).click();
        //driver.findElement(By.xpath("//*[@id='tasks']/div[1]/span/a/span[2]")).click();
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys("First Pipeline");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.xpath("//*[@id='ok-button']")).click();
        driver.findElement(By.xpath("//*[@name='Submit']")).click();
        WebElement pipelineName = driver.findElement(By.xpath("//*[@id='main-panel']/div[1]/div[1]/h1"));

        Assert.assertEquals(pipelineName.getText(), "First Pipeline");
    }
}
