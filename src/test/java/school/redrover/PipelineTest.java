package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.PipelineProjectPage;

public class PipelineTest extends BaseTest {
    private static final String PROJECT_NAME = "New Pipeline";
    private static final String PIPELINE_DESCRIPTION = "Pipeline Project Name";

    @Test
    public void testCreatePipeline() {

        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .createJob()
                .sendItemName(PROJECT_NAME)
                .selectPipelineAndClickOk()
                .sendDescription(PIPELINE_DESCRIPTION)
                .clickSave();

        Assert.assertTrue(pipelineProjectPage.getProjectName().contains(PROJECT_NAME),
                "The project name does not contain the expected value");
        Assert.assertTrue(pipelineProjectPage.getDescription().contains(PIPELINE_DESCRIPTION),
                "Project description does not contain expected value");
    }

}