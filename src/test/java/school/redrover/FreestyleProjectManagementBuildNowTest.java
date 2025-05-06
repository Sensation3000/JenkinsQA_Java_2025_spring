package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import static org.testng.Assert.assertTrue;


public class FreestyleProjectManagementBuildNowTest extends BaseTest {

    final String name_Freestyle_Project = "new Freestyle Project";

    @Test
    public void testAvailableBuildNowOnProjectPage() {
        final boolean rez = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(name_Freestyle_Project)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .clickLeftSideMenuBuildNow()
                .isTextBuildScheduled();
        assertTrue(rez);
    }

    @Test
    public void testAvailableBuildNowOnbreadcrumbs() {
        final boolean rez = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(name_Freestyle_Project)
                .selectFreestyleAndClickOk()
                .clickApply()
                .getHeader()
                .goToHomePage()
                .clickScheduleBuild()
                .isTextBuildScheduled();
        assertTrue(rez);
    }

    @Test
    public void testAvailableSuccesResult() {
        final boolean rez = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(name_Freestyle_Project)
                .selectFreestyleAndClickOk()
                .clickApply()
                .getHeader()
                .goToHomePage()
                .clickProjectName(name_Freestyle_Project)
                .clickLeftSideMenuBuildNow()
                .clickStatus()
                .clickLastBuild()
                .clickConsoleOutput()
                .isFinishedSuccess();
        assertTrue(rez,"статус не соответсвует ожидаемому Finished: SUCCESS");
    }
}
