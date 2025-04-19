package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

import java.time.Duration;

public class Folder2Test extends BaseTest {

    @Test
    public void testNewFolderIsEmptyByDefault() {
        final String folderName = "New Folder";

        String folderStatus = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(folderName)
                .selectFolderAndClickOkWithJS()
                .clickSave()
                .getFolderStatus();

        Assert.assertEquals(folderStatus, "This folder is empty");
    }

    @Test
    public void testCannotCreateItemsWithSameNameInFolder() {
        WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(1));
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

        WebElement input = getWait5().until
                (ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")));
        input.sendKeys(jobName);

        WebElement invalidItemName;
        try {
            invalidItemName = wait1.until
                    (ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid")));
        } catch (TimeoutException e) {
            input.sendKeys(" ");
            invalidItemName = wait1.until
                    (ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid")));
        }

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
