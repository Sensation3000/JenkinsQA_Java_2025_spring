package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class CreateItemToFolderTest extends BaseTest {

    private static final String FOLDER_NAME = "Folder1";
    private static final String FOLDER_NAME_2 = "Folder2";
    private static final String ITEM_NAME = "Item1";

    @Test
    public void testCreateNewItemOnTheFolder() {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='newJob']"))).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(ITEM_NAME);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        TestUtils.gotoHomePage(this);

        Actions actions = new Actions(getDriver());
        WebElement folderLink = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'job/Folder1') and contains(@class, 'inside')]")));
        actions.moveToElement(folderLink).click().perform();
        String actualItemName = getDriver()
                .findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)"))
                .getText();

        Assert.assertEquals(actualItemName, ITEM_NAME);
    }

    @Test
    public void testCreateFolderToFolder() {

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='newJob']"))).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME_2);
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        TestUtils.gotoHomePage(this);

        Actions actions = new Actions(getDriver());
        WebElement folderLink = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'job/Folder1') and contains(@class, 'inside')]")));
        actions.moveToElement(folderLink).click().perform();
        String actualItemName = getDriver()
                .findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)"))
                .getText();

        Assert.assertEquals(actualItemName, FOLDER_NAME_2);
    }
}