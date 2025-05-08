package school.redrover;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import java.util.List;

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
        TestUtils.scrollAndClickWithJS(getDriver(),element);

        getDriver().findElement(By.xpath("//a[normalize-space()='\"Java Concurrency in Practice\" book annotations']"));

        WebElement middleElement = getDriver().findElement(By.xpath("//a[normalize-space()='args4j']"));
        TestUtils.scrollAndClickWithJS(getDriver(),middleElement);

        WebElement lastElement = getDriver().findElement(By.xpath("//a[normalize-space()='XStream Core']"));
        TestUtils.scrollAndClickWithJS(getDriver(),lastElement);

        Assert.assertTrue(lastElement.isDisplayed(), "Last element is not displayed");
    }

    @Test
    public void testDependenciesLinksExist() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();

        WebElement aboutLink = getDriver().findElement(By.xpath("//a[@href='about']"));
        TestUtils.scrollAndClickWithJS(getDriver(),aboutLink);

        List<WebElement> dependencies = getDriver().findElements(By.xpath("//tr/td[1]/a[@class='jenkins-table__link']"));
        Assert.assertFalse(dependencies.isEmpty(), "No plugins found");
        for (int i = 0; i < dependencies.size(); i++) {
            WebElement dependenciesLinks = dependencies.get(i);

            Assert.assertTrue(dependenciesLinks.isEnabled(), "Links #" + (i + 1) + " not clickable");
        }
    }
    @Test
    public void testDependenciesStaticResourcesLinksExist() {    getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        WebElement aboutLink = getDriver().findElement(By.xpath("//a[@href='about']"));
        TestUtils.scrollAndClickWithJS(getDriver(),aboutLink);
        getDriver().findElement(By.xpath("//a[normalize-space()='Static resources']")).click();

        getWait5();
        List<WebElement> dependency = getDriver().findElements(By.xpath("(//table)[2]//tr//td/a"));
        Assert.assertNotEquals(dependency.size(),0);

        for (int i = 0; i < dependency.size(); i++) {
            WebElement dependenciesStatic  = dependency.get(i);

            Assert.assertTrue(dependenciesStatic.isEnabled(), "Links №" + (i + 1) + " not clickable");
        }
    }
}