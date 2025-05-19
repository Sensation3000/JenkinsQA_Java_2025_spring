package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class SystemConfigureTest extends BaseTest {

    final private static String DEFAULT_EXECUTORS_VALUE = "4";
    final private static String NEW_EXECUTORS_VALUE = "5";

    @Test
    public void testAccessSystem(){
        boolean isAccessSystem = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .isAccessSystemPage();

        assertTrue(isAccessSystem);
    }

    @Test
    public void testGlobalSystemParameters() {
        final String actualExecutorsValue = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .clearOfExecutors()
                .sendOfExecutors(NEW_EXECUTORS_VALUE)
                .clickButtonSave()
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .getOfExecutors();

        assertEquals(actualExecutorsValue, NEW_EXECUTORS_VALUE);
    }

    @Test(dependsOnMethods = "testGlobalSystemParameters")
    public void testModifyGlobalSystemParameters() {
        final String actualExecutorsValue = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .clearOfExecutors()
                .sendOfExecutors(DEFAULT_EXECUTORS_VALUE)
                .clickButtonSave()
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .getOfExecutors();

        assertEquals(actualExecutorsValue, DEFAULT_EXECUTORS_VALUE);
    }

    @Test(dependsOnMethods = "testModifyGlobalSystemParameters")
    public void testUndoChangeBeforeSave() {
        final String actualExecutorsValue = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .clearOfExecutors()
                .sendOfExecutors(NEW_EXECUTORS_VALUE)
                .getHeader()
                .goToHomePage()
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .getOfExecutors();

        assertEquals(actualExecutorsValue, DEFAULT_EXECUTORS_VALUE);
    }

    @Test
    public void testDisableUsageStatisticsOption() {
        boolean actualState = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .setUsageStatisticsCheckbox(false)
                .clickButtonSave()
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .isUsageStatisticsChecked();

        assertFalse(actualState);
    }
}