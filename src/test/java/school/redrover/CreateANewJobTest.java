package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateANewJobTest extends BaseTest {
    @Ignore
    @Test
    public void testCreateAJobBlock() {
        WebDriver driver = getDriver();

        WebElement contentBlockCreateAJob = driver.findElement(By.xpath("//span[text()='Create a job']"));
        contentBlockCreateAJob.click();

        WebElement itemNameHeader = driver.findElement(By.xpath("//input[@id='name']"));
        itemNameHeader.sendKeys("Monday");

        WebElement typeSelection = driver.findElement(By.xpath("//span[@class='label']"));
        typeSelection.click();
        WebElement okButton = driver.findElement(By.id("ok-button"));
        okButton.click();

        WebElement buttonSave = driver.findElement(By.name("Submit"));
        buttonSave.click();

        WebElement iconJenkins = driver.findElement(By.xpath("//img[@id='jenkins-name-icon']"));
        iconJenkins.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[text()='Monday']")).getText(), "Monday");
    }
}
