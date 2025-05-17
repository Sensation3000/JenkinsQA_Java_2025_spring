package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.buildhistory.BuildHistoryPage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
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

        Assert.assertTrue(buildHistoryPage.isBuildHistoryEmpty());
        Assert.assertEquals(buildHistoryPage.getBuildHistoryHeaders(), List.of("S", "Build", "Time Since", "Status"));
    }

    @Test
    public void testCheckTheBuildStatusDisplay() {
        String buildStatusProjectName = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, FreestyleConfigurationPage.class)
                .clickSaveButton()
                .clickLeftSideMenuBuildNow()
                .getHeader()
                .goToHomePage()
                .clickBuildHistoryOnLeftSidePanel()
                .buildProjectText(PROJECT_NAME);

        Assert.assertEquals(buildStatusProjectName, PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCheckTheBuildStatusDisplay")
    public void testChangeIconSize() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .clickBuildHistoryOnLeftSidePanel();

        Assert.assertEquals(buildHistoryPage.selectIconSize("S").getCurrentIconSize(), "16px");
        Assert.assertEquals(buildHistoryPage.selectIconSize("M").getCurrentIconSize(), "20.7969px");
        Assert.assertEquals(buildHistoryPage.selectIconSize("L").getCurrentIconSize(), "24px");
    }

    @Test(dependsOnMethods = "testChangeIconSize")
    public void testVerifyDeleteBuildHistory() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickOnBuildProject()
                .clickDeleteBuild();

        Assert.assertTrue(freestyleProjectPage.getBuildNameList().isEmpty());
    }
}
