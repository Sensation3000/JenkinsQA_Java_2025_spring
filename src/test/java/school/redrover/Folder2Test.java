package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
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
}
