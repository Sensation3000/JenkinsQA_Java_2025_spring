package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class CreateItemToFolder extends BaseTest {
    private final String item1 = "MyItem";

    @Test
    public void testCreateNewItemOnTheFolder() {

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='newJob']")).click();
        driver.findElement(By.id("name")).sendKeys("New Folder");
        driver.findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        driver.findElement(By.xpath("//a[@href='newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(item1);
        driver.findElement(By.className("hudson_model_FreeStyleProject")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
        TestUtils.gotoHomePage(this);

        driver.findElement(By.xpath("//a[contains(@class, 'inside')]")).click();
        String actualItemName = driver.findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText();
        Assert.assertEquals(actualItemName, item1);
    }
}