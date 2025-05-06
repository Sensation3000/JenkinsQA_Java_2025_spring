package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.error.ErrorPage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.page.pipeline.PipelineProjectPage;

public class PipelineTest extends BaseTest {

    private static final String PROJECT_NAME = "PipelineProjectNameTest";

    @Test
    public void testCreateNewPipeline() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .clickCreateJob()
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

    @Test
    public void testEmptyItemNamePOM() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickCreateJob()
                .selectPipeline();

        Assert.assertEquals(newItemPage.getEmptyNameMessage(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(newItemPage.isOkButtonEnabled());
        Assert.assertEquals(newItemPage.getEmptyNameMessageColor(), "rgba(230, 0, 31, 1)");
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testDisableProject() {

        PipelineProjectPage disabledProject = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .clickSave();

        Assert.assertEquals(disabledProject.getProjectDisabledStatus(),"This project is currently disabled\n" +
                "Enable");
    }
}
