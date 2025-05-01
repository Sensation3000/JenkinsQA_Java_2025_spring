package school.redrover;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.pipeline.PipelineProjectPage;

public class PipelineConfigurationPageTest extends BaseTest {

    private static final String ITEM_NAME = "Pipeline Test Item";

    @Test
    public void testDisableToggleButton() {
        boolean isDisabled = new HomePage(getDriver()).createJob()
                .sendItemName(ITEM_NAME)
                .selectPipelineAndClickOk()
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .isToggleDisabled();

        Assert.assertTrue(isDisabled);
    }

    @Test
    public void testEnableToggleButton() {
        boolean isEnabled = new HomePage(getDriver()).createJob()
                .sendItemName(ITEM_NAME)
                .selectPipelineAndClickOk()
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
        WebElement tooltip = new HomePage(getDriver()).createJob()
                .sendItemName(ITEM_NAME)
                .selectPipelineAndClickOk()
                .getToggleTooltipElementOnHover();

        Assert.assertTrue(tooltip.isDisplayed());
        Assert.assertEquals(tooltip.getText(), "Enable or disable the current project");
    }

    @Test
    public void testShowWarningMessageWhenPipelineDisabled() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver()).createJob()
                .sendItemName(ITEM_NAME)
                .selectPipelineAndClickOk()
                .switchToggle()
                .clickSave();

        Assert.assertTrue(pipelineProjectPage.isDisabledProjectWarningMessageDisplayed());
        Assert.assertTrue(pipelineProjectPage.getDisabledProjectWarningMessageText().contains("This project is currently disabled"));
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testNotShowWarningMessageWhenPipelineEnabled() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver()).createJob()
                .sendItemName(ITEM_NAME)
                .selectPipelineAndClickOk()
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .switchToggle()
                .clickSave();

        Assert.assertFalse(pipelineProjectPage.isDisabledProjectWarningMessageDisplayed());
    }
}
