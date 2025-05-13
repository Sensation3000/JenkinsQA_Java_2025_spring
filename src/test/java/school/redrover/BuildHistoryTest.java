package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.buildhistory.BuildHistoryPage;
import school.redrover.page.freestyle.FreestyleProjectPage;

import java.util.List;

public class BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "Freestyle Project";

    @Test
    public void testQuickAccessToTheBuildHistorySection() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .clickBuildHistoryOnLeftSidePanel();

        Assert.assertTrue(buildHistoryPage.getBuildHistoryText().contains("Build History of Jenkins"));
    }

    @Test
    public void testEmptyBuildHistory() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .clickBuildHistoryOnLeftSidePanel();
        List<String> expectedBuildHistoryTableHeaders = List.of("S", "Build", "Time Since", "Status");

        Assert.assertTrue(buildHistoryPage.isBuildHistoryEmpty());
        Assert.assertEquals(buildHistoryPage.getBuildHistoryHeaders(), expectedBuildHistoryTableHeaders);
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
                .clickBuildHistoryOnLeftSidePanel()
                .buildProjectText(PROJECT_NAME);

        Assert.assertEquals(buildStatusProjectName, PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCheckTheBuildStatusDisplay")
    public void testVerifyDeleteBuildHistory() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickOnBuildProject()
                .clickDeleteBuild();

        Assert.assertTrue(freestyleProjectPage.getBuildNameList().isEmpty());
    }
}
