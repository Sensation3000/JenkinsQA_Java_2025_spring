package school.redrover;


import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.folder.FolderConfigurationPage;

import java.util.List;


public class HealthMetricsTest extends BaseTest {

    private static final String HEALTH_METRICS = "Health metrics";
    private static final String ITEM_NAME = "Test Folder";

    @Test
    public void  testAvailabilityHealthMetricsPOM(){
        FolderConfigurationPage folderConfigurationPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ITEM_NAME)
                .selectFolderAndClickOkWithJS()
                .clickHealthMetrics();

        List<String> titlesHealthMetrics = List.of(
                folderConfigurationPage.getTitleHealthMetrics(),
                folderConfigurationPage.getTextDropdownHealthMetrics()
        );
       for (String titleHealthMetrics : titlesHealthMetrics) {
           Assert.assertEquals(titleHealthMetrics, HEALTH_METRICS);
       }
    }


}