package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

public class Folder2Test extends BaseTest {

    private static final String FOLDER_NAME = "Folder A";
    private static final String JOB_NAME = "New Job";

    @Test
    public void testNewFolderIsEmptyByDefault() {
        String folderStatus = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOkWithJS()
                .clickSave()
                .getFolderStatus();

        Assert.assertEquals(folderStatus, "This folder is empty");
    }

    @Test(dependsOnMethods = "testNewFolderIsEmptyByDefault")
    public void testCannotCreateItemsWithTheSameNameInFolder() {
        WebElement errorMessage = new HomePage(getDriver())
                .clickOnOrganizationFolderInListOfItems(FOLDER_NAME)
                .clickOnNewItemWithinFolder()
                .sendItemName(JOB_NAME)
                .selectFreestyleClickOkAndReturnToHomePage()
                .clickOnOrganizationFolderInListOfItems(FOLDER_NAME)
                .clickOnNewItemWithinFolder()
                .sendItemName(JOB_NAME)
                .getInvalidItemNameError();

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(),
                String.format("» A job already exists with the name ‘%s’", JOB_NAME));
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
