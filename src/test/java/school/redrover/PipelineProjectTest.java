package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.pipeline.PipelineConfigurationPage;
import school.redrover.page.pipeline.PipelineProjectPage;

public class PipelineProjectTest extends BaseTest {

    private static final String PROJECT_NAME = "New Pipeline";
    private static final String PIPELINE_DESCRIPTION = "Pipeline Project Name";

    @Test
    public void testCreatePipeline() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectPipelineAndClickOk()
                .sendDescription(PIPELINE_DESCRIPTION)
                .clickSave();

        Assert.assertEquals(pipelineProjectPage.getProjectName(), PROJECT_NAME);
        Assert.assertEquals(pipelineProjectPage.getDescription(), PIPELINE_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testDeletePipeline() {
        HomePage homePage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .deletePipeline();

        Assert.assertEquals(homePage.getProjectNameList().size(), 0);
    }

    @Test
    public void testDisableProject() {
        PipelineConfigurationPage pipelineConfigurationPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectPipelineAndClickOk()
                .switchToggle();

        Assert.assertTrue(pipelineConfigurationPage.isToggleDisabled(), "The switch is not in an active state");
    }

    @Test
    public void testEnableProject() {
        PipelineConfigurationPage pipelineConfigurationPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectPipelineAndClickOk()
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .switchToggle();

        Assert.assertTrue(pipelineConfigurationPage.isToggleEnabled(), "The switch is turned on after performing the actions");
    }

    @Test
    public void testCheckDefaultState() {
        String statusToggleDefault = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectPipelineAndClickOk()
                .checkStatusOnToggle();

        Assert.assertEquals(statusToggleDefault, "Enabled");
    }

    @Test
    public void testChangeStateNewPipeline() {
        String statusToggleChange = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectPipelineAndClickOk()
                .switchToggle()
                .checkStatusOffToggle();

        Assert.assertEquals(statusToggleChange, "Disabled");

    }

}
