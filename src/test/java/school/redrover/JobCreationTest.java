package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.Arrays;
import java.util.List;

@Ignore
public class JobCreationTest extends BaseTest {

    final String NEW_JOB = "//a[@href='newJob']";
    final String ITEM_NAME_INPUT = "//input[@class='jenkins-input']";

    @Test
    public void testEmptyName() {

        getDriver().findElement(By.xpath(NEW_JOB)).click();
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        WebElement validationMessage = getDriver().findElement(By.id("itemname-required"));

        Assert.assertEquals(validationMessage.getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testInvalidCharactersInItemName() {
        List<String> invalidNames = Arrays.asList("My Job!@#", "Test Job$", "Job#123", "My@Job", "Job%Test");

        for (String invalidName : invalidNames) {
            getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NEW_JOB))).click();
            WebElement itemNameInput = getDriver().findElement(By
                    .xpath(ITEM_NAME_INPUT));
            itemNameInput.clear();
            itemNameInput.sendKeys(invalidName);

            WebElement errorMessage = getWait5().until(ExpectedConditions
                    .visibilityOfElementLocated(By.id("itemname-invalid")));
            Assert.assertTrue(errorMessage.isDisplayed(), "Ошибка не отображается для имени: " + invalidName);
            TestUtils.gotoHomePage(this);
        }
    }

    @Test
    public void testCreateItemAndNavigateToConfigPage() {
        getDriver().findElement(By.xpath(NEW_JOB)).click();

        WebElement itemNameInput = getDriver().findElement(By
                .xpath(ITEM_NAME_INPUT));
        itemNameInput.sendKeys("new_project_1");
        getDriver().findElement(By.xpath("//span[@class][text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        TestUtils.gotoHomePage(this);
        WebElement projectName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//a[@href='job/new_project_1/']")));

        Assert.assertEquals(projectName.getText(), "new_project_1");
    }
}
