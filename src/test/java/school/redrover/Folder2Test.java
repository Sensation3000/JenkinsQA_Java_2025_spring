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

    @Ignore
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
                        (By.xpath("//span[text()='" + folderName + "']"))))
                .click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                        (By.className("h4"))).getText(),
                "This folder is empty");
    }

    @Ignore
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
                .sendKeys(jobName);

        WebElement invalidItemName = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("itemname-invalid")));

        Assert.assertTrue(invalidItemName.isDisplayed());
        Assert.assertEquals(invalidItemName.getText(),
                "» A job already exists with the name ‘" + jobName + "’");
    }

    @Test
    public void testSameNameItemsInDifferentFolders() {
        final String folderOneName = "Folder A";
        final String folderTwoName = "Folder B";
        final String itemName = "ProjectX";

        TestUtils.newItemCreate(this, folderOneName, 4);
        TestUtils.newItemCreate(this, folderTwoName, 4);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + folderOneName + "']/parent::a"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='New Item']/ancestor::a"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")))
                .sendKeys(itemName);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='Freestyle project']"))).click();
        TestUtils.scrollAndClickWithJS(getDriver(),
                getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))));
        TestUtils.gotoHomePage(this);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + folderTwoName + "']/parent::a"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='New Item']/ancestor::a"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")))
                .sendKeys(itemName);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='Freestyle project']"))).click();
        TestUtils.scrollAndClickWithJS(getDriver(),
                getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))));
        TestUtils.gotoHomePage(this);

        getWait5().until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[text()='" + folderOneName + "']"))).click();
        String firstItemName = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//td/a/span"))).getText();
        TestUtils.gotoHomePage(this);

        getWait5().until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[text()='" + folderTwoName + "']"))).click();
        String secondItemName = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//td/a/span"))).getText();

        Assert.assertEquals(firstItemName, itemName);
        Assert.assertEquals(secondItemName, itemName);
    }

    @Test
    public void testAddItem() {
        final String folderName = "TestFolder";
        final String itemName = "ProjectX";

        TestUtils.newItemCreate(this, folderName, 4);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + folderName + "']/parent::a"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='New Item']/ancestor::a"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")))
                .sendKeys(itemName);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='Freestyle project']"))).click();
        TestUtils.scrollAndClickWithJS(getDriver(),
                getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))));
        TestUtils.gotoHomePage(this);

        getWait5().until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[text()='" + folderName + "']"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//td/a/span"))).getText(), itemName);
    }
}
