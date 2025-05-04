package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

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
}
