package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class EnableDisableMultiConfigProjTest extends BaseTest {

    @Test
    public void testTooltipIsVisible() {

        String tooltipIsVisible = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName("MyProject1")
                .selectMultiConfigurationAndClickOk()
                .checkTooltipVisibility();

        Assert.assertEquals(tooltipIsVisible,"tippy-15");
    }

    @Test (dependsOnMethods = {"testTooltipIsVisible"})
    public void testProjectDisabledMessageIsVisible() {

        boolean projectDisabledMessageIsVisible = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName("MyProject2")
                .selectMultiConfigurationAndClickOk()
                .clickEnableToggle()
                .clickSaveButton()
                .projectDisabledMessageCheck();

        Assert.assertTrue(projectDisabledMessageIsVisible);
    }

    @Test (dependsOnMethods = {"testProjectDisabledMessageIsVisible"})
    public void testProjectDisabledMessageInvisible() {

        boolean projectDisabledMessageInvisible = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName("MyProject3")
                .selectMultiConfigurationAndClickOk()
                .clickEnableToggle()
                .clickSaveButton()
                .clickEnableButton()
                .projectDisabledMessageCheck();

        Assert.assertFalse(projectDisabledMessageInvisible);
    }
}