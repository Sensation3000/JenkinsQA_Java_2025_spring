package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class BuildHistoryTest extends BaseTest {

    @Test
    public void testQuickAccessToTheBuildHistorySection() {
        final String text = "Build History of Jenkins";

        String buildHistoryPage = new HomePage(getDriver())
                .clickBuildHistoryTab()
                .isBuildHistoryText();

        Assert.assertEquals(buildHistoryPage, text);
    }

    @Test
    public void testCheckTheBuildStatusDisplay() {
        final String projectName = "Freestyle Project";

        String buildStatusProjectName = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(projectName)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .clickLeftSideMenuBuildNow()
                .getHeader()
                .goToHomePage()
                .clickBuildHistoryTab()
                .buildProjectText(projectName);

        Assert.assertEquals(buildStatusProjectName, projectName);
    }
}
