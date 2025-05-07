package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SystemConfigureTest extends BaseTest {

    @Test
    public void testAccessSystem(){
        boolean isAccessSystem = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .isAccessSystemPage();

        assertTrue(isAccessSystem);
    }

    @Test
    public void testModifyGlobalSystemParameters() {
        final  String newExecutorsValue = "5";

        final String actualExecutorsValue = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .clearOfExecutors()
                .sendOfExecutors(newExecutorsValue)
                .clickButtonSave()
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemButton()
                .getOfExecutors();

        assertEquals(actualExecutorsValue, newExecutorsValue);
    }
}
