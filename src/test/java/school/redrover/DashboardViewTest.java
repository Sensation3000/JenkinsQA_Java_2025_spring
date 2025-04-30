package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.view.EditViewPage;
import school.redrover.page.view.NewViewPage;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class DashboardViewTest extends BaseTest {

    private final String MY_VIEW_NAME = "TestMyViewName";
    private final String List_VIEW_NAME = "TestListViewName";
    private static final String TEST_ITEM_JOB = "Test item";

    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateMyView() {
        new HomePage(getDriver()).clickNewView().addName(MY_VIEW_NAME)
                .clickMyView()
                .clickCreateButton();

        List<String> projectNameList = new HomePage(getDriver()).getProjectNameList();
        assertEquals(projectNameList.size(), 1);
        assertEquals(new HomePage(getDriver()).getNameOfView(MY_VIEW_NAME), MY_VIEW_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateListView() {
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
        String projectName = new HomePage(getDriver())
                .clickNewItem()
                .sendItemName(TEST_ITEM_JOB)
                .selectFreestyleClickOkAndWaitCreateItemFormIsClose()
                .waitUntilTextConfigureToBePresentInH1()
                .clickSaveButton()
                .waitUntilTextNameProjectToBePresentInH1(TEST_ITEM_JOB)
                .getProjectName();

        assertEquals(projectName, TEST_ITEM_JOB);
    }

}
