package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static java.sql.DriverManager.getDriver;

public class SunflowerTest extends BaseTest {

    @Test
    public void testCreatePipeline () throws InterruptedException {

        getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        getDriver().findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[2]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("New Pipeline");
        getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]")).click();

        Thread.sleep(1000);

        getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]")).click();

        WebElement title = getDriver().findElement(By.cssSelector("#main-panel > div.jenkins-app-bar > div.jenkins-app-bar__content.jenkins-build-caption > h1"));

        Assert.assertEquals(title.getText(), "New Pipeline");
    }

    @Test
    public void testManageJenkinsLinkPresence() {

        WebDriver driver = getDriver();

        WebElement manageJenkinsLink = driver.findElement(By.cssSelector("a[href*='manage']"));

        Assert.assertEquals(manageJenkinsLink.getText(), "Manage Jenkins");
    }
}
