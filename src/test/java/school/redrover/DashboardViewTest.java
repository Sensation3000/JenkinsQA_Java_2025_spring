package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.freestyle.FreestyleProjectPage;
import school.redrover.page.view.EditViewPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardViewTest extends BaseTest {

    private final static String JOB_NAME = "Test item";
    private final static String LIST_VIEW_NAME = "TestlistViewName";
    private final static String TEST_ITEM_JOB = "Test item";
    private final static String VIEW_NAME = "TestViewName";

    @Test
    public void testCreateFreestyleProjectForView() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(JOB_NAME, FreestyleConfigurationPage.class)
                .clickSaveButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), JOB_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateMyView() {
        HomePage homePage = (HomePage) new HomePage(getDriver())
                .clickNewView()
                .addName(VIEW_NAME)
                .clickMyView()
                .clickCreateButton();

        assertEquals(homePage.getNameOfView(), VIEW_NAME);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectForView")
    public void testCreateListView() {
        ((EditViewPage) new HomePage(getDriver())
                .clickNewView()
                .addName(LIST_VIEW_NAME)
                .clickListView()
                .clickCreateButton())
                .fillDescription("Description for Test List View")
                .JobsCheckTestItem(TEST_ITEM_JOB)
                .clickAddJobFilter()
                .clickStatusFilterOfJobFilter()
                .clickAddColumn()
                .checkProjectDescriptionColumn()
                .clickSaveButton();

        assertTrue(new HomePage(getDriver()).isJobDisplayed(JOB_NAME));
        assertEquals(new HomePage(getDriver()).getNameOfView(), LIST_VIEW_NAME);
    }
}
