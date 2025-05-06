package school.redrover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.view.EditViewPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class DashboardViewTest extends BaseTest {
    private final String JOB_NAME = "Test item";

    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateMyView() {
        String view_name = "TestViewName";
        new HomePage(getDriver())
                .clickNewView()
                .addName(view_name)
                .clickMyView()
                .clickCreateButton();

        String newViewName = new HomePage(getDriver()).getNameOfView(view_name);
        assertEquals(newViewName, view_name);
    }

    @Ignore
    @Test(dependsOnMethods = {"testCreateFreestyleProjectForView"})
    public void testCreateListView() {
        String listViewName = "TestlistViewName";
        String testItemJob = "Test item";
        String projectName = ((EditViewPage) new HomePage(getDriver())
                .clickNewView()
                .addName(listViewName)
                .clickListView()
                .clickCreateButton())
                .fillDescription("Description for Test List View")
                .JobsCheckTestItem(testItemJob)
                .clickAddJobFilter()
                .clickStatusFilterOfJobFilter()
                .clickAddColumn()
                .checkProjectDescriptionColumn()
                .clickSaveButton()
                .getProjectNameList()
                .get(0);

        assertEquals(projectName, testItemJob
        );
        assertTrue(new HomePage(getDriver()).isJobDisplayed(JOB_NAME));
        assertEquals(new HomePage(getDriver()).getNameOfView(listViewName), listViewName);
    }


    @Test
    public void testCreateFreestyleProjectForView() {
        String projectName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(JOB_NAME)
                .selectFreestyleClickOkAndWaitCreateItemFormIsClose()
                .waitUntilTextConfigureToBePresentInH1()
                .clickSaveButton()
                .waitUntilTextNameProjectToBePresentInH1(JOB_NAME)
                .getProjectName();

        assertEquals(projectName, JOB_NAME);
    }

}


