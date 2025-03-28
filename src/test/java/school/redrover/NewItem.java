package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItem extends BaseTest {
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
        WebDriver driver = getDriver();
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.id("name")).sendKeys("New Item1");
        driver.findElement(By.xpath("//span[text()='Freestyle project']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
        String headerNewItem = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(headerNewItem, "New Item1");
    }

    @Test
    public void testCreateNewItemOrganizationFolder() {
        WebDriver driver = getDriver();
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.id("name")).sendKeys("New Item2");
        WebElement organizationFolder = driver.findElement(By.xpath("//span[text()='Organization Folder']"));
        WebElement pageFooterLink = driver.findElement(By.className("page-footer__links"));
        actions = new Actions(driver);
        actions.moveToElement(pageFooterLink).perform();
        organizationFolder.click();

        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
        String headerNewItem = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(headerNewItem, "New Item2");
    }
}
