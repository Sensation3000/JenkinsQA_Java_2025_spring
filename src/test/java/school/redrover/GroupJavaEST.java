package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.sql.Time;
import java.time.Duration;

public class GroupJavaEST extends BaseTest {

    @Test
    public void testCreatePipeline() {
        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//a[@href ='newJob']")).click();
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys("First Pipeline");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.xpath("//*[@id='ok-button']")).click();

        driver.findElement(By.xpath(
                "//*[@id='main-panel']/form/div[1]/div[2]/div/div[2]/textarea")).sendKeys(
                        "Pipeline Description");
        driver.findElement(By.xpath("//*[@name='Submit']")).click();

        WebElement pipelineName = driver.findElement(By.xpath("//*[@id='main-panel']/div[1]/div[1]/h1"));
        Assert.assertEquals(pipelineName.getText(), "First Pipeline");
    }

    @Test
    public void testAddDescription(){
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//a[@id='description-link']")).getText(),
                "Add description"
        );
    }

    @Test
    public void testWelcomeHeader() {
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1")).getText(),
                "Welcome to Jenkins!"
        );
    }

    @Test
    public void testJenkinsVersionButton(){
        Assert.assertEquals(getDriver().findElement(By.xpath("//button[contains(@class, 'jenkins-button--tertiary')]")).getText(),
               "Jenkins 2.492.2"
     );
    }

    @Test
    public void testMenuOnTheVersionButton() throws InterruptedException {
      getDriver().findElement(By.xpath("//button[contains(@class, 'jenkins-button--tertiary')]")).click();
        Thread.sleep(3000);
      Assert.assertEquals(getDriver().findElement(By.xpath("//a[contains(@href, 'about')]")).getText(),
              "About Jenkins"
        );
    }
}
