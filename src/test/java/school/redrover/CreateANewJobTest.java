package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class CreateANewJobTest extends BaseTest {

    @Test
    public void testCreateAJobBlock() {
        WebDriver driver = getDriver();

        WebElement contentBlockCreateAJob = driver.findElement(By.xpath("//span[text()='Create a job']"));
        contentBlockCreateAJob.click();

        WebElement itemNameHeader = driver.findElement(By.id("name"));
        itemNameHeader.sendKeys("Monday");

        WebElement typeSelection = driver.findElement(By.xpath("//span[text()='Freestyle project']"));
        typeSelection.click();
        WebElement okButton = driver.findElement(By.id("ok-button"));
        okButton.click();
        WebElement discriptionField = driver.findElement(By.name("description"));
        discriptionField.sendKeys("Freestyle project");
        WebElement buttonSave = driver.findElement(By.name("Submit"));
        buttonSave.click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement pipelineName = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Monday']")));
        Assert.assertEquals(pipelineName.getText(), "Monday");
    }
}
