package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static org.testng.Assert.assertEquals;

public class CheckToolTipTest extends BaseTest {
@Test
    public void testToolTip () throws InterruptedException {
        Actions actions = new Actions(getDriver());
        WebElement linkElement = getDriver().findElement(By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and @data-href='http://localhost:8080/']"));
        actions.moveToElement(linkElement).pause(2000).click(linkElement).perform();
        Thread.sleep(2000);
        WebElement manageTools= getDriver().findElement(By.xpath("//a[@class='jenkins-dropdown__item ' and @href='/manage']"));

         Thread.sleep(2000);
        actions.moveToElement(manageTools).perform();
        getDriver().findElement(By.xpath("//a[contains(@href, '/manage/configureTools')]")).click();

        WebElement pathToGit = getDriver().findElement(By.xpath("//a[@tooltip='Help for feature: Use default maven settings']"));
        actions.moveToElement(pathToGit).perform();
        Thread.sleep(1000);
        assertEquals(getDriver().findElement(By.id("tippy-9")).getText(), "Help for feature: Use default maven settings");
    }
}
