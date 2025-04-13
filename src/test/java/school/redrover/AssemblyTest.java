package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AssemblyTest extends BaseTest {

    @Test
    public void testBuildHistory() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@class='task-link task-link-no-confirm ']")).click();
        driver.findElement(By.xpath("//input[@class='jenkins-input']")).sendKeys("Freestule");
        driver.findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.xpath("//a[text()='Freestule']")).click();
        driver.findElement(By.xpath("//a[@data-task-post='true']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.className("app-builds-container__items"))).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[5]/span/a")).click();
        driver.findElement(By.xpath("//span[text()='Delete the build ‘#1’?']")).getText();
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(driver.findElement
                (By.xpath("//div[text()='No builds']")).getText(),"No builds");
    }
}
