package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import static org.testng.Assert.assertEquals;

public class CheckToolTipTest extends BaseTest {

    @Test
    public void testToolTip() throws InterruptedException {
        Actions actions = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@class='jenkins-breadcrumbs__list-item']")
        )));
        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and @data-href='http://localhost:8080/']")
        )));
        js.executeScript("arguments[0].click();", getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and @data-href='http://localhost:8080/']"))));
        Thread.sleep(1000);

        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'manage')][contains(@class,'dropdown')]")
        )));
        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/manage/configureTools']")
        )));
        js.executeScript("arguments[0].click();", getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/manage/configureTools']"))));
        Thread.sleep(1000);

        WebElement pathToGit = getDriver().findElement(By.xpath("//a[@tooltip='Help for feature: Use default maven settings']"));
        actions.moveToElement(pathToGit).perform();
        Thread.sleep(1000);
        assertEquals(getDriver().findElement(By.id("tippy-9")).getText(), "Help for feature: Use default maven settings");
    }
}
