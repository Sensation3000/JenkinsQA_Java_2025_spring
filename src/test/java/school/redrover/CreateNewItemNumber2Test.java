package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class CreateNewItemNumber2Test extends BaseTest {

    @Test
    public void testItemCreation_Success() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@it='hudson.model.Hudson@71fdd4fe' and @data-task-success='Done.']")
        ));
        link.click();

        getDriver().findElement(By.name("name")).sendKeys
                ("TestItemBlaBla");
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//button[contains(@class,'jenkins-button--primary')]")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        WebElement createdProjectLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@class='model-link' and contains(@href, '/job/TestItemBlaBla/')]")
        ));

        Assert.assertTrue(createdProjectLink.isDisplayed(), "Project link not found after creation.");
    }
}