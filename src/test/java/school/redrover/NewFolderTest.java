package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class NewFolderTest extends BaseTest {

    @Test
    public void createFolder() {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        driver.findElement(By.xpath("//input[@id = \"name\"]")).sendKeys("Test");
        driver.findElement(By.xpath("//*[@id=\"j-add-item-type-nested-projects\"]/ul/li[1]")).click();
        driver.findElement(By.cssSelector("#ok-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]")));

        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]"));
        saveButton.click();

        String folderText = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/h1")).getText();

        Assert.assertEquals(folderText, "Test");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[4]/div/section/h2")).getText(), "This folder is empty");

    }
}
