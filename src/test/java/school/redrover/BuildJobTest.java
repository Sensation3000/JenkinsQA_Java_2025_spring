package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class BuildJobTest extends BaseTest {
    private WebDriverWait wait5;

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    @Test
    public void testBuildJob() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Test item");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).sendKeys(Keys.ENTER);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/']"))).click();
        selectMenuFromItemDropdown("Test item", "Build Now");
        getDriver().findElement(By.xpath("//tr[@id = 'job_Test item']//a[@href='job/Test%20item/']")).click();
        getDriver().navigate().refresh();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='lastBuild/']"))).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'console')]")).click();
        String out = getDriver().findElement(By.xpath("//*[@id='out']")).getText();

        assertTrue("В Console Output отсутствует запись об успешной сборке", out.contains("Finished: SUCCESS"));
    }
    private void selectMenuFromItemDropdown(String itemName, String menuName) {
        moveAndClickWithJS(getDriver(), getDriver().findElement(By.xpath("//td/a/span[text() = '%s']/../button".formatted(itemName))));
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown__item__icon']/parent::*[contains(., '%s')]".formatted(menuName)))).click();
    }
    public static void moveAndClickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('click'));", element);
    }
}