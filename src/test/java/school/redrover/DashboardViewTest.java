package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.freestyle.FreestyleProjectPage;
import school.redrover.page.view.EditViewPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class DashboardViewTest extends BaseTest {
    private final static String JOB_NAME = "Test item";

    @Test
    public void testCreateFreestyleProjectForView() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(JOB_NAME)
                .selectFreestyleAndClickOk()
                .clickSaveButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), JOB_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateMyView() {
        final String view_name = "TestViewName";

        HomePage homePage = (HomePage) new HomePage(getDriver())
                .clickNewView()
                .addName(view_name)
                .clickMyView()
                .clickCreateButton();

        assertEquals(homePage.getNameOfView(), view_name);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateListView() {
        String listViewName = "TestlistViewName";
        String testItemJob = "Test item";

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
        assertEquals(new HomePage(getDriver()).getNameOfView(), listViewName);
    }
}
