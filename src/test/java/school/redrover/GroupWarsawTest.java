package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class GroupWarsawTest extends BaseTest {

    @Test
    public void testAddDescriptionButtonOnWelcomePage() throws InterruptedException {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id='description-link']")).click();
        driver.findElement(By.xpath("//*[@id='description']/form/div[1]/div[1]/textarea"))
                .sendKeys("This Jenkins server is used for the JenkinsQA_Java_2025_spring project.");

        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id='description']/form/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id='description']/div[1]"));

        Assert.assertEquals("This Jenkins server is used for the JenkinsQA_Java_2025_spring project.",
                driver.findElement(By.xpath("//*[@id='description']/div[1]"))
                        .getText(), "Description should be saved correctly");
    }
}