package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.view.EditViewPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class DashboardViewTest extends BaseTest {
    private final String JOB_NAME = "Test item";

    @Test
    public void testCreateMyView() {
        CreateFreestyleProjectForView();

        String view_name = "TestViewName";
        new HomePage(getDriver())
                .clickNewView()
                .addName(view_name)
                .clickMyView()
                .clickCreateButton();

        String newViewName = new HomePage(getDriver()).getNameOfView(view_name);
        assertEquals(newViewName, view_name);
    }

    @Test
    public void testCreateListView() {
        String listViewName = "TestlistViewName";
        String testItemJob = "Test item";

        CreateFreestyleProjectForView();

        ((EditViewPage) new HomePage(getDriver())
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
                .clickSaveButton();

        assertTrue(new HomePage(getDriver()).isJobDisplayed(JOB_NAME));
        assertEquals(new HomePage(getDriver()).getNameOfView(listViewName), listViewName);
    }

    private void CreateFreestyleProjectForView() {
        new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(JOB_NAME)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo();
    }

}



