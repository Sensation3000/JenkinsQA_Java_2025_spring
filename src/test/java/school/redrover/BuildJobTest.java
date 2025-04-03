package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import static org.testng.AssertJUnit.assertTrue;

public class BuildJobTest extends BaseTest {

    @Test
    public void testBuildJob() {
        final String jobName = "Test item";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(jobName);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).sendKeys(Keys.ENTER);
        TestUtils.gotoHomePage(this);
        selectMenuFromItemDropdown(jobName, "Build Now");
        getDriver().findElement(By.linkText(jobName)).click();
        getDriver().navigate().refresh();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='lastBuild/']"))).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'console')]")).click();
        String out = getDriver().findElement(By.id("out")).getText();

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