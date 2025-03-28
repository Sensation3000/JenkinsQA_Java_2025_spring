package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;


public class NewItemTest extends BaseTest {
    private Actions actions;

    @Test
    public void testCheckNewItemPageHeader() {
        WebDriver driver = getDriver();
        driver.findElement(By.linkText("New Item")).click();
        String header = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(header, "New Item");
    }

    @Test
    public void testCreateNewItemFreestyleProject() {
        String headerNewItem = "New Item1";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebDriver driver = getDriver();
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.id("name")).sendKeys(headerNewItem);
        driver.findElement(By.xpath("//span[text()='Freestyle project']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        String nameOfCreatedItem = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//h1[contains(text(), '" + headerNewItem + "')]")))
                .getText();
        Assert.assertEquals(nameOfCreatedItem, "New Item1");
    }

    @Test
    public void testCreateNewItemOrganizationFolder() {
        String headerNewItem = "New Item2";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebDriver driver = getDriver();
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.id("name")).sendKeys(headerNewItem);
        WebElement organizationFolder = driver.findElement(By.xpath("//span[text()='Organization Folder']"));
        WebElement pageFooterLink = driver.findElement(By.className("page-footer__links"));
        actions = new Actions(driver);
        actions.moveToElement(pageFooterLink).perform();
        organizationFolder.click();

        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        String nameOfCreatedItem = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//h1[contains(text(), '" + headerNewItem + "')]")))
                .getText();
        Assert.assertEquals(nameOfCreatedItem, "New Item2");
    }
}
