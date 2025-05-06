package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.buildhistory.BuildHistoryPage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.freestyle.FreestyleProjectPage;
import school.redrover.page.newitem.NewItemPage;

public class AssemblyTest extends BaseTest {

    private static final String PROJECT_NAME = "Freestyle Project";

    @Test
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
