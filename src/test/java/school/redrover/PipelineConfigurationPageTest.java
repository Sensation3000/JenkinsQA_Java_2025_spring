package school.redrover;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;
import school.redrover.page.pipeline.PipelineConfigurationPage;
import school.redrover.page.pipeline.PipelineProjectPage;

public class PipelineConfigurationPageTest extends BaseTest {

    private static final String PROJECT_NAME = "Pipeline Test Item";
    private static final String BUILD_NOW_OPTION = "Build Now";
    private static final String DESCRIPTION = "Pipeline Config Description Test";

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

    @Test(dependsOnMethods = "testBuildNowOptionIsShownForEnabledPipeline")
    public void testBuildNowOptionIsHiddenForDisabledPipeline() {
        boolean isOptionPresented = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .clickSave()
                .getHeader()
                .goToHomePage()
                .showDropdownOnHoverByJobName(PROJECT_NAME)
                .isOptionPresentedInDropdown(BUILD_NOW_OPTION);

        Assert.assertFalse(isOptionPresented);
    }

    @Test(dependsOnMethods = "testBuildNowOptionIsHiddenForDisabledPipeline")
    public void testEnableDisableToggleVisibility() {
        boolean isToggleDisplayed = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .isToggleDisplayed();

        Assert.assertTrue(isToggleDisplayed);
    }

    @Test(dependsOnMethods = "testEnableDisableToggleVisibility")
    public void testEnableToggleButton() {
        boolean isEnabled = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .isToggleEnabled();

        Assert.assertTrue(isEnabled);
    }

    @Test(dependsOnMethods = "testEnableToggleButton")
    public void testDisableToggleButton() {
        boolean isDisabled = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .isToggleDisabled();

        Assert.assertTrue(isDisabled);
    }

    @Test(dependsOnMethods = "testDisableToggleButton")
    public void testPreviewIsDisplayedAfterClickingPreviewButton() {
        boolean isPreviewDisplayed = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .sendDescription(DESCRIPTION)
                .clickPreviewButton()
                .isPreviewDisplayed();

        Assert.assertTrue(isPreviewDisplayed);
    }

    @Test(dependsOnMethods = "testPreviewIsDisplayedAfterClickingPreviewButton")
    public void testPreviewIsHiddenAfterClickingHidePreview() {
        boolean isPreviewDisplayed = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .sendDescription(DESCRIPTION)
                .clickPreviewButton()
                .clickHidePreviewButton()
                .isPreviewDisplayed();

        Assert.assertFalse(isPreviewDisplayed);
    }

    @Test(dependsOnMethods = "testPreviewIsHiddenAfterClickingHidePreview")
    public void testShowTooltipMessageOnHover() {
        WebElement tooltip = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .getToggleTooltipElementOnHover();

        Assert.assertTrue(tooltip.isDisplayed());
        Assert.assertEquals(tooltip.getText(), "Enable or disable the current project");
    }

    @Test(dependsOnMethods = "testShowTooltipMessageOnHover", expectedExceptions = NoSuchElementException.class)
    public void testNotShowWarningMessageWhenPipelineEnabled() {
        boolean isDisabledProjectWarningMessageDisplayed = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .clickSave()
                .isDisabledProjectWarningMessageDisplayed();

        Assert.assertFalse(isDisabledProjectWarningMessageDisplayed);
    }

    @Test(dependsOnMethods = "testNotShowWarningMessageWhenPipelineEnabled")
    public void testShowWarningMessageWhenPipelineDisabled() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .clickSave();

        Assert.assertTrue(pipelineProjectPage.isDisabledProjectWarningMessageDisplayed());
        Assert.assertTrue(pipelineProjectPage.getDisabledProjectWarningMessageText().contains("This project is currently disabled"));
    }
}
