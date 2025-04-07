package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;

public class GroupJavaESTTest extends BaseTest {

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
    public void testGreetingsIsPresented() {
        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".empty-state-block h1")).getText(),
                "Welcome to Jenkins!"
        );
    }

    @Test
    public void testAddDescription(){
        Assert.assertEquals(
                getDriver().findElement(By.id("description-link")).getText(),
                "Add description"
        );
    }

    @Test
    public void testWelcomeHeader() {
        WebDriver driver = getDriver();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement welcome = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='empty-state-block']/h1")));

        Assert.assertEquals(welcome.getText(),"Welcome to Jenkins!");
    }

    @Test
    public void testJenkinsVersionButton(){
        Assert.assertEquals(
               getDriver().findElement(By.xpath("//button[contains(@class,'jenkins-button--tertiary')]"))
                       .getText(), "Jenkins 2.492.2"
        );
    }

    @Test
    public void testMenuOnTheVersionButton() {
      WebDriver driver = getDriver();
      
      WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(1));

      WebElement versionButton = wait2.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[contains(@class, 'jenkins-button--tertiary')]"))));
      versionButton.click();
      WebElement about = wait1.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//a[contains(@href, 'about')]"))));

      Assert.assertEquals(about.getText(),"About Jenkins");
    }

    @Test
    public void testSubHeaders() {
        String[] subheaders = {"Start building your software project", "Set up a distributed build"};
        List<WebElement> list = getDriver().findElements(By.xpath("//h2[@class='h4']"));
        for (int i = 0; i < subheaders.length; i++) {
            Assert.assertEquals(
                    list.get(i).getText(),
                    subheaders[i]
            );
        }
    }

    @Test
    public void testListOfLinks() {
        String[] listLinks = {"Create a job", "Set up an agent", "Configure a cloud", "Learn more about distributed builds"};
        List<WebElement> list = getDriver().findElements(By.xpath("//a[@class='content-block__link']"));
        for (int i = 0; i < listLinks.length; i++) {
            Assert.assertEquals(
                    list.get(i).getText(),
                    listLinks[i]);
        }
    }
    @Test
    public void testNotifications(){
        Assert.assertTrue(getDriver().findElement(By.xpath("//a[@id='visible-am-button']")).isDisplayed());
    }

    @Test
    public void testCreateAjob(){
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href ='newJob']")).getText(),
                "Create a job");
    }
}