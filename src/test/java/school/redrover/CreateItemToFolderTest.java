package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
@Ignore
public class CreateItemToFolderTest extends BaseTest {

    private static final String FOLDER_NAME = "Folder1";
    private static final String FOLDER_NAME_2 = "Folder2";
    private static final String ITEM_NAME = "Item1";

    @Test
    public void testCreateNewItemOnTheFolder() {

        TestUtils.createFolder(getDriver(), FOLDER_NAME);
        TestUtils.gotoHomePage(this);
        TestUtils.openJobByName(getDriver(), FOLDER_NAME);
        TestUtils.createFreestyleProject(getDriver(), ITEM_NAME);
        TestUtils.gotoHomePage(this);

        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
        Actions actions = new Actions(getDriver());
        WebElement folder = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class, 'inside')]/span[text()='" + FOLDER_NAME + "']")));
        actions.moveToElement(folder).click().perform();
        String actualItemName = getDriver().findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText();

        Assert.assertEquals(actualItemName, ITEM_NAME);
    }

    @Test
    public void testCreateFolderToFolder() {

        TestUtils.createFolder(getDriver(), FOLDER_NAME);
        TestUtils.gotoHomePage(this);
        TestUtils.openJobByName(getDriver(), FOLDER_NAME);
        TestUtils.createFolder(getDriver(), FOLDER_NAME_2);
        TestUtils.gotoHomePage(this);

        Actions actions = new Actions(getDriver());
        WebElement folder = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class, 'inside')]/span[text()='Folder1']")));
        actions.moveToElement(folder).click().perform();
        String actualItemName = getDriver().findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText();

        Assert.assertEquals(actualItemName, FOLDER_NAME_2);
    }
}