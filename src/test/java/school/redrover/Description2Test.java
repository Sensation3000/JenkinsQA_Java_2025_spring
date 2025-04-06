package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class Description2Test extends BaseTest {

    @Test
    public void testCreateDescription() {
        String newDescription = "Description";

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@class='task-link task-link-no-confirm ']")).click();
        driver.findElement(By.xpath("//input[@class='jenkins-input']")).sendKeys("Freestule");
        driver.findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(newDescription);
        driver.findElement(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']")).click();
        driver.findElement(By.className("textarea-hide-preview")).click();
        driver.findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        Assert.assertEquals(driver.findElement
                (By.cssSelector("#description")).getText(),newDescription);
    }
}
