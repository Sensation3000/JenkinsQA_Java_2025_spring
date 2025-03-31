package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class RinaGJenkinsTest extends BaseTest {

    @Test
    public void testWelcome() {
        WebDriver driver = getDriver();

        WebElement titleElement = driver.findElement(By.cssSelector(".empty-state-block h1"));
        Assert.assertEquals(titleElement.getText(), "Добро пожаловать в Jenkins!");
    }
    @Test
    public void testAssertViews() {
        WebDriver driver = getDriver();

        WebElement titleElement = driver.findElement(By.cssSelector(".empty-state-block h1"));
        Assert.assertEquals(titleElement.getText(), "Добро пожаловать в Jenkins!");
        driver.findElements(By.className("task-link-wrapper")).get(3).click();
        WebElement TextElement = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div/section/h2"));
        Assert.assertEquals(TextElement.getText(), "This folder is empty");
    }
}

