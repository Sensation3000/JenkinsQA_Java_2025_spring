package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

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
    public void testCreateNewItemPipelineProject() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("//a[@href='/' and @class='model-link']"))).perform();

        WebElement dropdownItem = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@class='jenkins-menu-dropdown-chevron'])[2]")));
        actions.moveToElement(dropdownItem).click().perform();

        WebElement newJobLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[@href='/view/all/newJob'])[2]")));
        newJobLink.click();

        driver.findElement(By.name("name")).sendKeys("Test");
        driver.findElement(By.xpath("//span[text()='Pipeline']")).click();
        driver.findElement(By.id("ok-button")).click();

        driver.findElement(By.name("description")).sendKeys("Test");
        driver.findElement(By.xpath("//button[@formnovalidate='formNoValidate']"))
                .click();

        WebElement homeLinkAgain = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/' and @class='model-link']")));
        homeLinkAgain.click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Test']")).getText(), "Test");
    }

}
