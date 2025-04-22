package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class QuickAccessToBuildHistoryTest extends BaseTest {

    @Test
    public void testquickAccessToTheBuildHistorySection() {
        final String text = "Build History of Jenkins";

        String buildHistoryPage = new HomePage(getDriver())
                .clickBuildHistoryTab()
                .isBuildHistoryText();

        Assert.assertEquals(buildHistoryPage, text);
    }
}
