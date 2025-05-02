package school.redrover;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.view.EditViewPage;
import school.redrover.page.view.NewViewPage;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class DashboardViewTest extends BaseTest {


    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateMyView() {
        String view_name = "TestViewName" + genetateRandomName();

        new HomePage(getDriver())
                .clickNewView()
                .addName(view_name)
                .clickMyView()
                .clickCreateButton();

        String newViewName = new HomePage(getDriver()).getNameOfView(view_name);
        assertEquals(newViewName, view_name);
        List<String> projectNameList = new HomePage(getDriver()).getProjectNameList();
        assertEquals(projectNameList.size(), 1);
        assertEquals(new HomePage(getDriver()).getNameOfView(MY_VIEW_NAME), MY_VIEW_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateListView() {
        String List_VIEW_NAME = "TestListViewName";
        String TEST_ITEM_JOB = "Test item";
        NewViewPage initialPage = new HomePage(getDriver()).clickNewView().addName(List_VIEW_NAME);

        EditViewPage editViewPage = (EditViewPage) initialPage
                .clickListView()
                .clickCreateButton();

        editViewPage
                .fillDescription("Description for Test List View")
                .JobsCheckTestItem(TEST_ITEM_JOB)
                .clickAddJobFilter()
                .clickStatusFilterOfJobFilter()
                .clickAddColumn()
                .checkProjectDescriptionColumn()
                .clickSaveButton();

        List<String> projectNameList = new HomePage(getDriver()).getProjectNameList();
        assertEquals(projectNameList.size(), 1);
        assertTrue(new HomePage(getDriver()).isJobDisplayed(TEST_ITEM_JOB));
        assertEquals(new HomePage(getDriver()).getNameOfView(List_VIEW_NAME), List_VIEW_NAME);
    }


    @Test
    public void testCreateFreestyleProjectForView() {
        String job_name = "Test item" + genetateRandomName();

        String projectName = new HomePage(getDriver())
                .clickNewItem()
                .sendItemName(job_name)
                .selectFreestyleClickOkAndWaitCreateItemFormIsClose()
                .waitUntilTextConfigureToBePresentInH1()
                .clickSaveButton()
                .waitUntilTextNameProjectToBePresentInH1(job_name)
                .getProjectName();

        assertEquals(projectName, job_name);
    }

    private static String genetateRandomName() {
        return "Test" + System.currentTimeMillis();
    }
}


