package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import static org.testng.Assert.assertEquals;


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


