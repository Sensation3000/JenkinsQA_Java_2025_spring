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

    @Test
    public void testCheckJenkinsUrl(){
        getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/span/a")).click();
        getDriver().findElement(By.cssSelector("#main-panel > section:nth-child(5) > div > div:nth-child(1) > a")).click();

        WebElement jenkinsLocation = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[1]/div[2]/div[2]/input"));
        String actualValue = jenkinsLocation.getAttribute("value");

        Assert.assertEquals(actualValue, "http://localhost:8080/");
    }

    @Test
    public void testCreateDescription(){
        getDriver().findElement(By.xpath("//*[@href='editDescription' and @class='jenkins-button']")).click();

        WebElement inputField = getDriver().findElement(By.xpath(
                "//*[@id='description']/form/div[1]/div[1]/textarea"));
        inputField.sendKeys("Create description for testing");

        getDriver().findElement(By.xpath("//*[@id='description']/form/div[2]/button")).click();

        WebElement descriptionElement = getDriver().findElement(By.xpath("//*[@id='description']/div[1]"));

        String actualText = descriptionElement.getText();

        Assert.assertEquals(actualText, "Create description for testing");
    }
}
