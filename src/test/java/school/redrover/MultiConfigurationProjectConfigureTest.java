package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;

import static org.testng.Assert.assertEquals;

public class MultiConfigurationProjectConfigureTest extends BaseTest {

    private static final String PROJECT_NAME = "Project name";
    private static final String QUIET_PERIOD_DEFAULT = "5";

    @Test
    public void testQuietPeriodDefaultValue() {
        MultiConfigurationConfigurePage multiConfigurationConfigurePage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectMultiConfigurationAndClickOk()
                .clickAdvancedProjectOptions()
                .clickAdvancedButton()
                .clickQuietPeriodCheckbox();

        Assert.assertEquals(multiConfigurationConfigurePage.checkQuietPeriodDefaultValue(), QUIET_PERIOD_DEFAULT);
    }

    @Test(dependsOnMethods = "testQuietPeriodDefaultValue")
    public void testQuietPeriodValueSet() {
        MultiConfigurationConfigurePage multiConfigurationConfigurePage = new HomePage(getDriver())
                .








    }
}
