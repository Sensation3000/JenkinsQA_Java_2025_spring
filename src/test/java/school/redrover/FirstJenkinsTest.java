package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FirstJenkinsTest extends BaseTest {

    @Test
    public void testDescriptionField() throws InterruptedException {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input   ")).sendKeys("It's my first test in Jenkins");

        getDriver().findElement(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']")).click();
        String previewText = getDriver().findElement(By.cssSelector(".textarea-preview")).getText();

        getDriver().findElement(By.cssSelector(".textarea-hide-preview")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        Thread.sleep(200);

        String resultText = getDriver().findElement(By.xpath("//*[@class='jenkins-!-margin-bottom-0']/div[1]")).getText();

        Assert.assertEquals(previewText, "It's my first test in Jenkins");
        Assert.assertEquals(resultText, "It's my first test in Jenkins");
    }

    @Test
    public void testCreateNewItemPipelineProject() throws InterruptedException {
        WebDriver driver = getDriver();

        WebElement dashboardLink = driver.findElement(By.xpath("//a[@href='/' and @class='model-link']"));
        moveAndClickWithJS(driver, dashboardLink);

        WebElement dropdownItem = driver.findElement(By.xpath("(//button[@class='jenkins-menu-dropdown-chevron'])[2]"));
        moveAndClickWithJS(driver, dropdownItem);

        driver.findElement(By.xpath("(//a[@href='/view/all/newJob'])[2]")).click();

        driver.findElement(By.name("name")).sendKeys("Test");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("description")).sendKeys("Test");
        driver.findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        Thread.sleep(500);

        driver.findElement(By.xpath("//a[@href='/' and @class='model-link']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Test']")).getText(), "Test");
    }

    public static void moveAndClickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", element);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].dispatchEvent(new Event('click'));", element);
    }
}
