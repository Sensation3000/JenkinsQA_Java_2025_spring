package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewJenkinsTest extends BaseTest {
    @Test
    public void testElement(){
        WebDriver driver = getDriver();
        WebElement createElement = driver.findElement(By.xpath("//a[@class='task-link task-link-no-confirm ']"));
        createElement.click();

        WebElement createTask = driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[2]/div[2]/div[1]/ul/li[1]"));
        createTask.click();

        WebElement element= driver.findElement(By.xpath("//input[@class='jenkins-input']"));
        element.sendKeys("Freestule");

        WebElement confirm = driver.findElement(By.xpath("//button[@id='ok-button']"));
        confirm.click();

        WebElement tune = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/h1"));
        Assert.assertEquals(tune.getText(), "Configure");
    }
}
