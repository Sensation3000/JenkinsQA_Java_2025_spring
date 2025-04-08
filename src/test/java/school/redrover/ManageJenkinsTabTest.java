package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class ManageJenkinsTabTest extends BaseTest {

    @Test
    public void testGoToTabSystemManageJenkinsTab() {
        WebDriver driver = getDriver();
        getDriver().findElement(By.xpath("(//div[contains(@id,'tasks')]//a)[3]")).click();
        getDriver().findElement(By.xpath("//*[text()='System']")).click();

        String resultClickSystemTab = driver.findElement(By.xpath("//*[text()='By default, Jenkins stores all of its data in this directory on the file system']")).getText();

        Assert.assertEquals(resultClickSystemTab, "By default, Jenkins stores all of its data in this directory on the file system");
    }

    @Test
    public void testAccessAboutJenkinsOption() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        WebElement element = getDriver().findElement(By.xpath("//a[@href='about']"));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://localhost:8080/manage/about/", "About Jenkins' page did not open");
    }

    @Test
    public void testVerifyLogoAboutJenkins() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        WebElement element = getDriver().findElement(By.xpath("//a[@href='about']"));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        WebElement logoElement = getDriver().findElement(By.xpath("//img[@alt='logo']"));

        Assert.assertTrue(logoElement.isDisplayed(), "logo with alt='logo' is not displayed");
    }

    @Test
    public void testVerifyVersionAboutJenkinsPage() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        WebElement element = getDriver().findElement(By.xpath("//a[@href='about']"));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        WebElement versionElement = getDriver().findElement(By.xpath("//p[@class='app-about-version']"));

        Assert.assertTrue(versionElement.isDisplayed(), "About Jenkins' version  is not displayed");
    }

    @Test
    public void testReviewListOfDependenciesAboutJenkinsPage() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        WebElement element = getDriver().findElement(By.xpath("//a[@href='about']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        getDriver().findElement(By.xpath("//a[normalize-space()='\"Java Concurrency in Practice\" book annotations']"));

        WebElement mediaElement = getDriver().findElement(By.xpath("//a[normalize-space()='args4j']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", mediaElement);

        WebElement lastElement = getDriver().findElement(By.xpath("//a[normalize-space()='XStream Core']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", lastElement);

        Assert.assertTrue(lastElement.isDisplayed(), "Last element is not displayed");
    }
}