package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.folder.FolderProjectPage;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "ProjectFolder";

    @Test(dataProvider = "projectNames", dataProviderClass = TestDataProvider.class)
    public void  testCreateWithValidName(String folderName) {

        List<String> jobs = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(folderName)
                .selectFolderAndClickOk()
                .saveAndReturnToHomePage()
                .getProjectNameList();

        Assert.assertEquals(jobs.size(), 1);
        Assert.assertEquals(jobs.get(0), folderName);
    }

    @Test
    public void testCreateWithDisplayName() {
        final String expectedDisplayName = "Folder Display Name";

        String actualDisplayName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .sendDisplayName(expectedDisplayName)
                .clickSave()
                .getProjectName();

        Assert.assertEquals(actualDisplayName, expectedDisplayName);
    }

    @Test
    public void testIfCopyFromFieldAppears() {
        final String expectedText = "Copy from";

        String copyFromFieldText = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .getHeader()
                .clickLogoIcon()
                .clickNewItemOnLeftSidePanel()
                .getCopyFromFieldText();

        Assert.assertEquals(copyFromFieldText, expectedText);
    }

    @Test
    public void testCreateWithDescription () {
        final String descriptionName = "folder";

        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .createJob()
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .sendDescription(descriptionName)
                .clickSave();

        Assert.assertEquals(folderProjectPage.getProjectName(),FOLDER_NAME);
        Assert.assertEquals(folderProjectPage.getDescription(),descriptionName);
    }
}
