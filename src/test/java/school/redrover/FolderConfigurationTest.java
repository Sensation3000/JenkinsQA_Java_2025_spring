package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

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
}