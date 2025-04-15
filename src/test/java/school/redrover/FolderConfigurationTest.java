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
    private final String FOLDER_NAME = "TestFolder";
    private final String DISPLAY_NAME = "Folder Display Name";

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

        TestUtils.createFolder(driver, FOLDER_NAME);
        TestUtils.openHomePage(this);
        TestUtils.openJobByName(driver, FOLDER_NAME);
        driver.findElement(By.xpath("//a[@href='/job/" + FOLDER_NAME + "/configure']")).click();
        driver.findElement(By.name("_.displayNameOrNull")).clear();
        driver.findElement(By.name("Submit"));
        TestUtils.openHomePage(this);
        getWait5().until(ExpectedConditions.visibilityOf(driver.findElement(By.id("projectstatus"))));

        Assert.assertEquals(
                driver.findElement(By.xpath("//a[@href='job/TestFolder/']")).getText(), FOLDER_NAME);
    }

    @Test
    public void testValidDisplayName() {
        WebDriver driver = getDriver();

        TestUtils.createFolder(driver, FOLDER_NAME);
        getWait5().until(ExpectedConditions.visibilityOf(driver.findElement(By.name("_.displayNameOrNull")))).clear();
        driver.findElement(By.name("_.displayNameOrNull")).sendKeys(DISPLAY_NAME);
        driver.findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//div[@id='main-panel']/h1"))));

        Assert.assertEquals(
                driver.findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), DISPLAY_NAME);
    }

    @Test
    public void testDisplayNameDisplayedOnDashboard() {
        TestUtils.createFolder(getDriver(), FOLDER_NAME);
        TestUtils.openHomePage(this);
        TestUtils.openJobByName(getDriver(), FOLDER_NAME);
        getDriver().findElement(By.xpath("//a[@href='/job/" + FOLDER_NAME + "/configure']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.name("_.displayNameOrNull")))).clear();
        getDriver().findElement(By.name("_.displayNameOrNull")).sendKeys(DISPLAY_NAME);
        getDriver().findElement(By.name("Submit")).click();
        TestUtils.gotoHomePage(getDriver());
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("projectstatus"))));

        Assert.assertTrue(
                getDriver().findElement(By.xpath("//td/a[@href='job/" + FOLDER_NAME + "/']")).isDisplayed());
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//td/a[@href='job/" + FOLDER_NAME + "/']/span")).getText(), DISPLAY_NAME);
    }
}