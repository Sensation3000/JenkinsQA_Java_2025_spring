package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "Folder1";

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
        final String folderName = "ProjectFolder";

        WebDriver driver = getDriver();

        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.id("name")).sendKeys(folderName);
        driver.findElement(By.xpath("//span[text() = 'Folder']")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), folderName);

        TestUtils.gotoHomePage(this);

        Assert.assertEquals(
                driver.findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText(),
                folderName);
    }
}
