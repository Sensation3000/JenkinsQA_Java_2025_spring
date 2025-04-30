package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.pipeline.PipelineProjectPage;

import java.time.Duration;

public class Pipeline1Test extends BaseTest {

    @Test
    public void testCreatePOM() {
        final String projectName = "MyPipeline";
        final String projectDescription = "This is a test pipeline";

        PipelineProjectPage pipelineProjectPage = new HomePage(getDriver())
                .createJob()
                .sendItemName(projectName)
                .selectPipelineAndClickOk()
                .sendDescription(projectDescription)
                .clickSave();

        Assert.assertEquals(pipelineProjectPage.getProjectName(), projectName);
        Assert.assertEquals(pipelineProjectPage.getDescription(), projectDescription);
    }
}
