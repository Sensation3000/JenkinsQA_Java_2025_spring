package school.redrover;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.error.ErrorPage;
import school.redrover.page.freestyle.FreestyleProjectPage;
import school.redrover.page.pipeline.PipelineConfigurationPage;
import school.redrover.page.pipeline.PipelineProjectPage;

public class PipelineTest extends BaseTest {

    private static final String PROJECT_NAME = "PipelineProjectNameTest";
    private static final String PIPELINE_DESCRIPTION = "Pipeline Project Name";

    @Test
    public void testCreatePipelineJobUsingScript() {
        String msg = new HomePage(getDriver())
                .clickCreateJob()
                .createNewItem(PROJECT_NAME, PipelineConfigurationPage.class)
                .clickPipeline()
                .clickAndSelectSamplesScript()
                .clickSave()
                .clickBuildNow()
                .clickStatus()
                .clickLastBuild()
                .clickPipelineConsole()
                .getConsoleOutput();

        Assert.assertEquals(msg, "Hello World");
    }

    @Test(dependsOnMethods = "testCreatePipelineJobUsingScript")
    public void checkAvailableTriggerBoxTest() {
        List<WebElement> BoxAvailable = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .clickTriggerMenu()
                .clickTriggerCheckbox();

        for (WebElement checkbox: BoxAvailable) {
            Assert.assertTrue(checkbox.isSelected());
        }
    }

    @Test(dependsOnMethods = "checkAvailableTriggerBoxTest")
    public void testChangeStateNewPipeline() {
        String statusToggleChange = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .checkStatusOffToggle();

        Assert.assertEquals(statusToggleChange, "Disabled");
    }

    @Test(dependsOnMethods = "testChangeStateNewPipeline")
    public void testCheckDefaultState() {
        String statusToggleDefault = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .checkStatusOnToggle();

        Assert.assertEquals(statusToggleDefault, "Enabled");
    }

    @Test(dependsOnMethods = "testCheckDefaultState")
    public void testNamePipeline() {
        Assert.assertEquals(new HomePage(getDriver()).getProjectName(), PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testNamePipeline")
    public void testCheckTriggersPipeline() {
        List<WebElement> Trigger = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .clickTriggerMenu()
                .getTrigger();

        for (WebElement checkbox : Trigger) {
            Assert.assertFalse(checkbox.isSelected());
        }
    }

    @Test(dependsOnMethods = "testCheckTriggersPipeline")
    public void testCreatePipelineWithExistingName() {
        String errorMessage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectItemType(PipelineConfigurationPage.class)
                .getItemNameInvalidMessage();

        Assert.assertEquals(errorMessage, "» A job already exists with the name ‘%s’".formatted(PROJECT_NAME));
    }

    @Test(dependsOnMethods = "testCreatePipelineWithExistingName")
    public void testCopyFromError() {
        final String projectNameB = "SecondProject";
        final String copyFrom = "No such item";

        ErrorPage error = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectNameB)
                .selectItemType(PipelineConfigurationPage.class)
                .sendTextCopyForm(copyFrom)
                .clickOkButtonWithError();

        Assert.assertEquals(error.getTitle(), "Error");
        Assert.assertEquals(error.getErrorText(), "No such job: " + copyFrom);
    }

    @Test(dependsOnMethods = "testCopyFromError")
    public void testDisableProject() {
        String messageProjectStatus = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .clickSave()
                .getProjectDisabledStatus();

        Assert.assertEquals(messageProjectStatus,"This project is currently disabled\n" + "Enable");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testCreateWithDescription() {
        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .sendDescription(PIPELINE_DESCRIPTION)
                .clickSave();

        Assert.assertEquals(pipelineProjectPage.getProjectName(), PROJECT_NAME);
        Assert.assertEquals(pipelineProjectPage.getDescription(), PIPELINE_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testCreateWithDescription")
    public void testDisableProjectErrorWhenCreating() {
        boolean isToggleDisabled = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .isToggleDisabled();

        Assert.assertTrue(isToggleDisabled, "The switch is not in an active state");
    }

    @Test(dependsOnMethods = "testDisableProjectErrorWhenCreating")
    public void testEnableProject() {
        boolean isToggleDisabled = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickConfigure()
                .switchToggle()
                .clickSave()
                .clickConfigure()
                .switchToggle()
                .isToggleEnabled();

        Assert.assertTrue(isToggleDisabled, "The switch is turned on after performing the actions");
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testDeletePipeline() {
        int numberProjects = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .deletePipeline()
                .getProjectNameList()
                .size();

        Assert.assertEquals(numberProjects, 0);
    }
}
