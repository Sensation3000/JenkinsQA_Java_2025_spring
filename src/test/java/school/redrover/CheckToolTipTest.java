package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class CheckToolTipTest extends BaseTest {
    private WebDriverWait wait5;

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }

        return wait5;
    }

    @Test
    public void testToolTip() throws InterruptedException {

        Actions actions = new Actions(getDriver());
        WebElement dashboard = getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']"));
        actions.moveToElement(dashboard).pause(500).perform();
        WebElement linkElement = getDriver().findElement(By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and @data-href='http://localhost:8080/']"));
        actions.moveToElement(linkElement).pause(500).click(linkElement).perform();
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement element = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'manage')][contains(@class,'dropdown')]")
        ));
        js.executeScript("arguments[0].click();", element);

        getDriver().findElement(By.xpath("//a[@href='configureTools']")).click();
        WebElement pathToGit = getDriver().findElement(By.xpath("//a[@tooltip='Help for feature: Use default maven settings']"));
        actions.moveToElement(pathToGit).perform();
        Thread.sleep(1000);
        assertEquals(getDriver().findElement(By.id("tippy-9")).getText(), "Help for feature: Use default maven settings");
    }
}
