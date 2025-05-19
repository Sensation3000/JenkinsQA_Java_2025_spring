package school.redrover;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.pipeline.PipelineConfigurationPage;
import school.redrover.page.pipeline.PipelineProjectPage;

public class PipelineConfigurationPageTest extends BaseTest {

    private static final String PROJECT_NAME = "Pipeline Test Item";
    private static final String BUILD_NOW_OPTION = "Build Now";
    private static final String DESCRIPTION = "Pipeline Config Description Test";

    @Test
    public void testDisableToggleButton() {
        boolean isDisabled = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .isToggleDisabled();

        Assert.assertTrue(isDisabled);
    }

    @Test
    public void testEnableToggleButton() {
        boolean isEnabled = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .isToggleEnabled();

        Assert.assertTrue(isEnabled);
    }

    @Test
    public void testShowTooltipMessageOnHover() {
        WebElement tooltip = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .getToggleTooltipElementOnHover();

        Assert.assertTrue(tooltip.isDisplayed());
        Assert.assertEquals(tooltip.getText(), "Enable or disable the current project");
    }

    @Test
    public void testShowWarningMessageWhenPipelineDisabled() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .switchToggle()
                .clickSave();

        Assert.assertTrue(pipelineProjectPage.isDisabledProjectWarningMessageDisplayed());
        Assert.assertTrue(pipelineProjectPage.getDisabledProjectWarningMessageText().contains("This project is currently disabled"));
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testNotShowWarningMessageWhenPipelineEnabled() {
        boolean isDisabledProjectWarningMessageDisplayed = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .switchToggle()
                .clickSave()
                .isDisabledProjectWarningMessageDisplayed();

        Assert.assertFalse(isDisabledProjectWarningMessageDisplayed);
    }

    @Test
    public void testEnableDisableToggleVisibility() {
        boolean isToggleDisplayed = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .isToggleDisplayed();

        Assert.assertTrue(isToggleDisplayed);
    }

    @Test
    public void testBuildNowOptionIsHiddenForDisabledPipeline() {
        boolean isOptionPresented = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .switchToggle()
                .clickSave()
                .getHeader()
                .goToHomePage()
                .showDropdownOnHoverByJobName(PROJECT_NAME)
                .isOptionPresentedInDropdown(BUILD_NOW_OPTION);

        Assert.assertFalse(isOptionPresented);
    }

    @Test
    public void testBuildNowOptionIsShownForEnabledPipeline() {
        boolean isOptionPresented = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .clickSave()
                .getHeader()
                .goToHomePage()
                .showDropdownOnHoverByJobName(PROJECT_NAME)
                .isOptionPresentedInDropdown(BUILD_NOW_OPTION);

        Assert.assertTrue(isOptionPresented);
    }

    @Test
    public void testPreviewIsDisplayedAfterClickingPreviewButton() {
        boolean isPreviewDisplayed = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .sendDescription(DESCRIPTION)
                .clickPreviewButton()
                .isPreviewDisplayed();

        Assert.assertTrue(isPreviewDisplayed);
    }

    @Test
    public void testPreviewIsHiddenAfterClickingHidePreview() {
        boolean isPreviewDisplayed = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .sendDescription(DESCRIPTION)
                .clickPreviewButton()
                .clickHidePreviewButton()
                .isPreviewDisplayed();

        Assert.assertFalse(isPreviewDisplayed);
    }
}
