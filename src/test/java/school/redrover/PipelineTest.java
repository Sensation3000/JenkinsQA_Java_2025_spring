package school.redrover;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.error.ErrorPage;
import school.redrover.page.pipeline.PipelineConfigurationPage;
import school.redrover.page.pipeline.PipelineProjectPage;

public class PipelineTest extends BaseTest {

    private static final String PROJECT_NAME = "PipelineProjectNameTest";
    private static final String PIPELINE_DESCRIPTION = "Pipeline Project Name";

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

    @Test
    public void testCreateWithDescription() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(PROJECT_NAME)
                .selectPipelineAndClickOk()
                .sendDescription(PIPELINE_DESCRIPTION)
                .clickSave();

        Assert.assertEquals(pipelineProjectPage.getProjectName(), PROJECT_NAME);
        Assert.assertEquals(pipelineProjectPage.getDescription(), PIPELINE_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testCreateWithDescription")
    public void testDeletePipeline() {
        HomePage homePage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .deletePipeline();

        Assert.assertEquals(homePage.getProjectNameList().size(), 0);
    }

    @Test
    public void testDisableProjectErrorWhenCreating() {
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

    @Test
    public void checkAvaliableTriggerBoxTest() {
        List<WebElement> BoxAvaliable = new HomePage(getDriver())
            .clickNewItemOnLeftSidePanel()
            .sendItemName(PROJECT_NAME)
            .selectPipelineAndClickOk()
            .clickTriggerMenu()
            .clickTriggerCheckbox();
        for (WebElement checkbox: BoxAvaliable) {
            Assert.assertTrue(checkbox.isSelected());
        }
    }
  
    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testCheckTriggesPipeline() {
        List<WebElement> Trigger = new HomePage(getDriver())
            .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
            .clickConfigure()
            .clickTriggerMenu()
            .getTrigger();
        for (WebElement checkbox : Trigger) {
            Assert.assertFalse(checkbox.isSelected());
        }
    }

}
