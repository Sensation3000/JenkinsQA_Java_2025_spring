package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewPipelineTest extends BaseTest {

    @Test
    public void testCreateNewPipeline() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//span[text()='Create a job']")).click();
        driver.findElement(By.id("name")).sendKeys("MyPipeline");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("This is a test pipeline");
        driver.findElement(By.xpath("//label[contains(text(),'Do not allow the pipeline to resume if the control')]")).click();
        driver.findElement(By.cssSelector("button[name='Submit']")).click();
        WebElement pipelineName = driver.findElement(By.xpath("//h1[text()='MyPipeline']"));
        Assert.assertEquals(pipelineName.getText(), "MyPipeline");

    }

}
