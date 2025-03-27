package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewItemNumber2Test extends BaseTest {

    @Test
    public void testItemCreation_Success() {
        getDriver().findElement(By.cssSelector("a.task-link.task-link-no-confirm[href='/view/all/newJob']")).click();
        getDriver().findElement(By.name("name")).sendKeys("TestItemBlaBla");
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//button[contains(@class,'jenkins-button--primary')]")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement createdProjectLink = getDriver().findElement(
                By.cssSelector("a.model-link[href='/job/TestItemBlaBla/']")
        );

        Assert.assertTrue(createdProjectLink.isDisplayed(), "Project link not found after creation.");
    }
}