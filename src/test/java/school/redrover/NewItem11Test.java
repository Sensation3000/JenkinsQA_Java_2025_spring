package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class NewItem11Test extends BaseTest {
    protected static final String PROJECT_NAME = "TestItemBlaBla1213";
    @Test(enabled = false)
    public void testItemCreation_Success() {
        getDriver().findElement(By.cssSelector("a.task-link.task-link-no-confirm[href='/view/all/newJob']")).click();
        getDriver().findElement(By.name("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//button[contains(@class,'jenkins-button--primary')]")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement createdProjectLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.model-link[href='/job/" + PROJECT_NAME + "/']")
        ));
        Assert.assertTrue(createdProjectLink.isDisplayed(), "Project link not found after creation.");
    }
}