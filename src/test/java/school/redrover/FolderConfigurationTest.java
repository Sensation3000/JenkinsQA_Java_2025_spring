package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

public class FolderConfigurationTest extends BaseTest {
    private static final String FOLDER_NAME = "TestFolder";
    private static final String DESCRIPTION_BOX ="Some random text and special chars and русский текст";

    @Test
    public void testQuestionMarkIcon() {
        boolean isButtonExist = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                        .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .isQuestionMarkIconEnabled();

        Assert.assertTrue(isButtonExist);
    }

    @Test(dependsOnMethods = "testQuestionMarkIcon")
    public void testDescriptionBox() {
       getWait5().until(ExpectedConditions.visibilityOfElementLocated
              (By.xpath("//span[text()='" + FOLDER_NAME + "']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[@id='tasks']/div[2]/span/a"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.name("_.description"))).sendKeys(DESCRIPTION_BOX);
        getDriver().findElement(By.xpath("//button[@name ='Submit']")).click();
        WebElement viewMessageInDescription = getDriver().findElement(By.id("view-message"));

        Assert.assertEquals(viewMessageInDescription.getText(), DESCRIPTION_BOX);
    }

    @Test (dependsOnMethods = "testDescriptionBox")
    public void testPreviewButton() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + FOLDER_NAME + "']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[@id='tasks']/div[2]/span/a"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable
                (By.className("textarea-show-preview"))).click();
        WebElement previewDescription = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[text()='"+DESCRIPTION_BOX+"']")));

        Assert.assertEquals(previewDescription.getText(), DESCRIPTION_BOX);
    }

    @Test
    public void testDescriptionBoxSaveEmpty() {
        TestUtils.createFolder(getDriver(), FOLDER_NAME);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.name("_.description"))).sendKeys("");
        getDriver().findElement(By.xpath("//button[@name ='Submit']")).click();
        WebElement viewMessageInDescription = getDriver().findElement(By.id("view-message"));

        Assert.assertEquals(viewMessageInDescription.getText(), "");
    }

    @Test
    public void testDescriptionBoxUsingApplyButton() {
       TestUtils.createFolder(getDriver(), FOLDER_NAME);
       getWait5().until(ExpectedConditions.visibilityOfElementLocated
               (By.name("_.description"))).sendKeys(DESCRIPTION_BOX);
        getDriver().findElement(By.name("Apply")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[text()='" + FOLDER_NAME + "']"))).click();
        WebElement viewMessageInDescription = getDriver().findElement(By.id("view-message"));

        Assert.assertEquals(viewMessageInDescription.getText(), DESCRIPTION_BOX);
    }

    @Test
    public void testEmptyDisplayName() {
        WebDriver driver = getDriver();

        TestUtils.createFolder(driver, FOLDER_NAME);
        TestUtils.openHomePage(this);
        TestUtils.openJobByName(driver, FOLDER_NAME);
        driver.findElement(By.xpath("//a[@href='/job/" + FOLDER_NAME + "/configure']")).click();
        driver.findElement(By.name("_.displayNameOrNull")).clear();
        driver.findElement(By.name("Submit"));
        TestUtils.openHomePage(this);
        getWait5().until(ExpectedConditions.visibilityOf(driver.findElement(By.id("projectstatus"))));

        Assert.assertEquals(
                driver.findElement(By.xpath("//a[@href='job/TestFolder/']")).getText(), FOLDER_NAME);
    }
}