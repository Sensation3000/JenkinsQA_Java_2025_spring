package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class WelcomeDashboardTest extends BaseTest {

    @Test
    public void testOpenManageJenkinsFromDashboard() {
        String manageJenkinsTitleText = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel().getManageJenkinsTitleText();

        Assert.assertEquals(manageJenkinsTitleText, "Manage Jenkins");
    }
}
