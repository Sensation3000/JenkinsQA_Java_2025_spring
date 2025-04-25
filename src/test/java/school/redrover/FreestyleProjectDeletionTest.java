package school.redrover;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class FreestyleProjectDeletionTest extends BaseTest {

    private static final String projectName = "TestProject";

    @Test
    public void testFreestyleProjectDeletion() {

        HomePage homePage = new HomePage(getDriver());
        homePage.clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .clickLeftSideMenuDelete()
                .clickPopUpYesDeleteProject()
                .isFreestileProgectDeleted(projectName);

        boolean projectDeleted = homePage.isFreestileProgectDeleted(projectName);

        Assert.assertTrue(projectDeleted, "Project '" + projectName + "'is not deleted");
    }
}

