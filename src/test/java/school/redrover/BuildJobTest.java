package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static org.testng.AssertJUnit.assertTrue;

public class BuildJobTest extends BaseTest {
    @Test
    public void testBuildJob() throws InterruptedException {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Test item");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//a[@href='/']")).click();

        Actions actions = new Actions(getDriver());
        WebElement linkElement = getDriver().findElement(By.xpath("//tr[@id = 'job_Test item']//a[@href='job/Test%20item/']"));
        actions.moveToElement(linkElement).perform();

        getDriver().findElement(By.xpath("//a[@href='job/Test%20item/']//button[@class='jenkins-menu-dropdown-chevron']")).click();
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//button[contains(@href, 'build')]")).click();
        getDriver().findElement(By.xpath("//tr[@id = 'job_Test item']//a[@href='job/Test%20item/']")).click();
        getDriver().findElement(By.xpath("//a[@href='lastBuild/']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'console')]")).click();
        String out = getDriver().findElement(By.xpath("//*[@id='out']")).getText();

        assertTrue("В Console Output отсутствует запись об успешной сборке", out.contains("Finished: SUCCESS"));
    }
}
