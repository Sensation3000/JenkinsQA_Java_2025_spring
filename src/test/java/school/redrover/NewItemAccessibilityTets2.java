package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import org.testng.Assert;

public class NewItemAccessibilityTets2 extends BaseTest {
    @Test
    public void checkAccessibilityTest() {

        WebDriver driver = getDriver();
        WebElement newItem = driver.findElement(By.xpath("//span[@class='task-link-text' " +
                "and text()='New Item']"));
        Assert.assertTrue(newItem.isDisplayed(), "Элемент 'New Item' не отображается");
        Assert.assertTrue(newItem.isEnabled(), "Элемент 'New Item' не активен");
    }
        @Test
        public void checkFieldNewItemTest()  {
            WebDriver driver = getDriver();
            WebElement newItem = driver.findElement(By.xpath("//a[@href='/view/all/newJob' and contains(@class, 'task-link')]"));
            newItem.click();
            WebElement inputField = driver.findElement(By.xpath("//input[@name='name']"));
            Assert.assertTrue(inputField.isDisplayed());
            Assert.assertTrue(inputField.isEnabled());
        }
            @Test
            public void checkElementsNewItemTest() {
                WebDriver driver = getDriver();
                driver.findElement(By.xpath("//a[@href='/view/all/newJob' and contains(@class, 'task-link')]")).click();
            driver.findElement(By.xpath("//span[text()='Freestyle project']")).isDisplayed();
            driver.findElement(By.xpath("//span[text()='Pipeline']")).isDisplayed();
            driver.findElement(By.xpath("//span[text()='Multi-configuration project']")).isDisplayed();
            driver.findElement(By.xpath("//span[text()='Folder']")).isDisplayed();
            driver.findElement(By.xpath("//span[text()='Multibranch Pipeline']")).isDisplayed();
            driver.findElement(By.xpath("//span[text()='Organization Folder']")).isDisplayed();

    }
}
