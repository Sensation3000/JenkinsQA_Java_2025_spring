package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class FolderConfigurationTest extends BaseTest {
    @Test
    public void testQquestionMarkIcon() {
        WebDriverWait wait5 = getWait5();
        WebElement newItemButton = wait5.until(ExpectedConditions.visibilityOfElementLocated
                (By.linkText("New Item")));
        newItemButton.click();

        WebElement field = wait5.until(ExpectedConditions.visibilityOfElementLocated
                (By.name("name")));
        field.sendKeys("My Folder");

        WebElement folder = wait5.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label/span[text()='Folder']")));
        folder.click();

        WebElement submitButton = wait5.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("ok-button")));
        submitButton.click();

        WebElement icon = wait5.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("a.jenkins-help-button")));

        Assert.assertTrue(icon.isEnabled(), "Button is not clickable");
    }

    @Test
    public void testEmptyDisplayName() {
        WebDriver driver = getDriver();
        String FOLDER_NAME = "TestFolder";

        TestUtils.createFolder(driver, FOLDER_NAME);
        driver.navigate().to("http://localhost:8080/job/" + FOLDER_NAME + "/configure");
        driver.findElement(By.name("_.displayNameOrNull")).clear();
        driver.findElement(By.name("Submit"));
        TestUtils.gotoHomePage(driver);
        getWait5().until(ExpectedConditions.visibilityOf(driver.findElement(By.id("projectstatus"))));

        Assert.assertEquals(
                driver.findElement(By.xpath("//a[@href='job/TestFolder/']")).getText(), FOLDER_NAME);
    }

    @Test
    public void testValidDisplayName() {
        WebDriver driver = getDriver();
        String FOLDER_NAME = "TestFolder";
        String DISPLAY_NAME = "Folder Display Name";

        TestUtils.createFolder(driver, FOLDER_NAME);
        getWait5().until(ExpectedConditions.visibilityOf(driver.findElement(By.name("_.displayNameOrNull")))).clear();
        driver.findElement(By.name("_.displayNameOrNull")).sendKeys(DISPLAY_NAME);
        driver.findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//div[@id='main-panel']/h1"))));

        Assert.assertEquals(
                driver.findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), DISPLAY_NAME);
    }
}