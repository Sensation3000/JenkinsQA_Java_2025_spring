package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.error.ErrorPage;
import school.redrover.page.pipeline.PipelineProjectPage;

public class Pipeline2Test extends BaseTest {

    private static final String PROJECT_NAME = "PipelineProjectNameTest";

    @Test
    public void testCreateNewPipeline() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .createJob()
                .sendItemName(PROJECT_NAME)
                .selectPipelineAndClickOk()
                .clickSave();

        Assert.assertEquals(pipelineProjectPage.getProjectName(), PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testCopyFromError() {

        final String projectNameB = "SecondProject";
        final String copyFrom = "No such item";

        ErrorPage error = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectNameB)
                .selectPipeline()
                .sendTextCopyForm(copyFrom)
                .clickOkButtonWithError();

        Assert.assertEquals(error.getTitle(), "Error");
        Assert.assertEquals(error.getErrorText(), "No such job: " + copyFrom);
    }
}
