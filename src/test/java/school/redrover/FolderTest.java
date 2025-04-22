package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "ProjectFolder";

    @Ignore
    @Test(dataProvider = "projectNames", dataProviderClass = TestDataProvider.class)
    public void  testCreateWithValidName(String folderName) {

        TestUtils.createFolder(getDriver(), folderName);
        TestUtils.gotoHomePage(this);

        List<WebElement> jobs = getDriver().findElements(By.xpath("//tr[contains(@id, 'job')]//a"));

        Assert.assertEquals(jobs.size(), 1);
        Assert.assertEquals(jobs.get(0).getText(), folderName);
    }

    @Test
    public void testCreateFolderWithBlankConfiguration () {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.xpath("//span[text() = 'Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.name("Submit")))).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), FOLDER_NAME);

        TestUtils.gotoHomePage(this);

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText(),
                FOLDER_NAME);
    }

    @Test
    public void testCreateFolder() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[contains(@it,'hudson')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("NewFolder");
        getDriver().findElement(By.xpath("//li[contains(@class,'Folder')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//input[@checkdependson]")).sendKeys("Folder1");
        getDriver().findElement(By.name("Submit")).click();

        Thread.sleep(2000);

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Folder1");
    }
}
