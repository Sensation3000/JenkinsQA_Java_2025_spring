package school.redrover;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class FreestyleProjectDeletionTest extends BaseTest {

    private static final String PROJECT_NAME = "TestProject";

    @Test
    public void testCreateFreestyleProject() {
        String projectName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .getProjectName();

        Assert.assertEquals(projectName, PROJECT_NAME);
    }

    @Ignore //StaleElementReference stale element reference: stale element not found  -> id: main-panel]
    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testDeleteFreestyleProject() {
        List<String> projectNameList = new HomePage(getDriver())
                .clickFreestyleProjectOnDashboard(PROJECT_NAME)
                .clickLeftSideMenuDelete()
                .clickPopUpYesDeleteProject()
                .getProjectNameList();

        Assert.assertListNotContainsObject(projectNameList, PROJECT_NAME, "The project is not deleted");
    }
}

