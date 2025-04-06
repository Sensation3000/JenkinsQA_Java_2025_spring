package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;


public class CreateItemToFolderTest extends BaseTest {

    @Ignore
    @Test
    public void testCreateNewItemOnTheFolder() {

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("New Folder");
        getDriver().findElement(By.xpath(
                "//*[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        String item1 = "MyItem";
        getDriver().findElement(By.id("name")).sendKeys(item1);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        TestUtils.gotoHomePage(this);

        getDriver().findElement(By.xpath("//a[contains(@class, 'inside')]")).click();
        String actualItemName = getDriver().findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText();
        Assert.assertEquals(actualItemName, item1);
    }

    @Ignore
    @Test
    public void testCreateFolderToFolder() {

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("New Folder");
        getDriver().findElement(By.xpath(
                "//*[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        String folder1 = "Myfolder";
        getDriver().findElement(By.id("name")).sendKeys(folder1);
        getDriver().findElement(By.xpath(
                "//*[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        TestUtils.gotoHomePage(this);

        getDriver().findElement(By.xpath("//a[contains(@class, 'inside')]")).click();
        String actualItemName = getDriver().findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText();
        Assert.assertEquals(actualItemName, folder1);
    }
}