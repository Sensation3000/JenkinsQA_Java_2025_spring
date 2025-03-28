package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class CreateFolderTest extends BaseTest {

    @Test
    public void testCreateFolder () {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        String folderName = "New Folder";

        wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='New Item']/preceding-sibling::span")))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")))
                .sendKeys(folderName);

        WebElement folderBox = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[contains(text(), 'container')]")));
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", folderBox);
        folderBox.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ok-button")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".jenkins-submit-button ")))
                .click();

        String nameOfCreatedFolder = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//h1[contains(text(), '" + folderName + "')]")))
                .getText();

        assertEquals(nameOfCreatedFolder, folderName);
    }
}