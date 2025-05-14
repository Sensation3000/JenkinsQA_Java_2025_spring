package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class ManageSystemInformationTest extends BaseTest {
    private static final String TAB_SYSTEM_PROPERTIES = "System Properties";
    private static final String TAB_ENVIRONMENT_VARIABLES = "Environment Variables";
    private static final String BUTTON_SHOW_VALUES = "Show Values";

    @Test
    public void testSystemPropertiesHiddenClassAfterClick() {

        String classAttribute = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemInfo()
                .clickShowValuesButton(TAB_SYSTEM_PROPERTIES, BUTTON_SHOW_VALUES)
                .getClassFirstElementInList();

        Assert.assertEquals(classAttribute, "app-hidden-info-hide");
    }

    @Test
    public void testEnvironmentVariablesHiddenClassAfterClick() {

        String classAttribute = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemInfo()
                .clickTab(TAB_ENVIRONMENT_VARIABLES)
                .clickShowValuesButton(TAB_ENVIRONMENT_VARIABLES, BUTTON_SHOW_VALUES)
                .getClassFirstElementInList();

        Assert.assertEquals(classAttribute, "app-hidden-info-hide");
    }
}