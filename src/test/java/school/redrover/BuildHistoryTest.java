package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.buildhistory.BuildHistoryPage;
import school.redrover.page.freestyle.FreestyleProjectPage;

import java.util.Properties;

public class BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "Freestyle Project";

    @Test
    public void testQuickAccessToTheBuildHistorySection() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .clickBuildHistoryTab();

        Assert.assertTrue(buildHistoryPage.getBuildHistoryText().contains("Build History of Jenkins"));
    }

    @Test
    public void testCheckTheBuildStatusDisplay() {
        String buildStatusProjectName = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .clickLeftSideMenuBuildNow()
                .getHeader()
                .goToHomePage()
                .clickBuildHistoryTab()
                .buildProjectText(PROJECT_NAME);

        Assert.assertEquals(buildStatusProjectName, PROJECT_NAME);
    }

    public void testBuildHistory() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectFreestyleAndClickOk()
                .clickFreestyleLink(PROJECT_NAME)
                .clickLeftSideMenuBuildNow()
                .clickOnBuildProject()
                .clickDeleteBuild();

        Assert.assertTrue(freestyleProjectPage.getBuildNameList().isEmpty());
    }
}
