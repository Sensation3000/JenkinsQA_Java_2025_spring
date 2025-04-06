package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "Folder1";

    @Test(dataProvider = "projectNames", dataProviderClass = TestDataProvider.class)
    public void  testCreateWithValidName(String folderName) {

        TestUtils.createFolder(getDriver(), folderName);
        TestUtils.gotoHomePage(this);

        List<WebElement> jobs = getDriver().findElements(By.xpath("//tr[contains(@id, 'job')]//a"));

        Assert.assertEquals(jobs.size(), 1);
        Assert.assertEquals(jobs.get(0).getText(), folderName);
    }
}
