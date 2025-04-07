package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class Folder2Test extends BaseTest {

    @Test
    public void testNewFolderIsEmptyByDefault() {
        final String folderName = "New Folder";

        getWait5().until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='New Item']/preceding-sibling::span")))
                .click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")))
                .sendKeys(folderName);

        TestUtils.scrollAndClickWithJS(getDriver(),
                getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='Folder']"))));
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button")))
                .click();
        TestUtils.gotoHomePage(this);

        getWait5().until(ExpectedConditions.elementToBeClickable(
                        (By.cssSelector(".jenkins-table__link > span:nth-child(1)"))))
                .click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                        (By.className("h4"))).getText(),
                "This folder is empty");
    }

    @Test
    public void testCannotCreateItemsWithSameNameInFolder() {
        final String folderName = "New Folder";
        final String jobName = "New Job";

        TestUtils.newItemCreate(this, folderName, 4);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + folderName + "']/parent::a"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='New Item']/ancestor::a"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")))
                .sendKeys(jobName);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='Freestyle project']"))).click();
        TestUtils.scrollAndClickWithJS(getDriver(),
                getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))));
        TestUtils.gotoHomePage(this);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + folderName + "']/parent::a"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='New Item']/ancestor::a"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")))
                .sendKeys(jobName + " ");

        WebElement invalidItemName = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("itemname-invalid")));

        Assert.assertTrue(invalidItemName.isDisplayed());
        Assert.assertEquals(invalidItemName.getText(),
                "» A job already exists with the name ‘" + jobName + "’");
    }

    @Ignore
    @Test
    public void testSameNameItemsInDifferentFolders() {
        final String folderOneName = "Folder A";
        final String folderTwoName = "Folder B";
        final String itemName = "ProjectX";

        TestUtils.newItemCreate(this, folderOneName, 4);
        TestUtils.newItemCreate(this, folderTwoName, 4);

//        TestUtils.createItemWithinFolder(this, itemName, folderOneName, 1);
//        TestUtils.createItemWithinFolder(this, itemName, folderTwoName, 1);

        getWait5().until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[text()='" + folderOneName + "']"))).click();
        String firstItemName = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//td/a/span"))).getText();
        TestUtils.gotoHomePage(this);

        getWait5().until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[text()='" + folderTwoName + "']"))).click();
        String secondItemName = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//td/a/span"))).getText();
        TestUtils.gotoHomePage(this);

        Assert.assertEquals(firstItemName, itemName);
        Assert.assertEquals(secondItemName, itemName);
    }
}
