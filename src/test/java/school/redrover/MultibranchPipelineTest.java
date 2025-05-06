package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.multibranch.MultibranchProjectPage;


public class MultibranchPipelineTest extends BaseTest {

    private static final String MULTIBRANCH_NAME = "Multibranch Pipeline Job Test";
    private static final String PROJECT_DESCRIPTION = "This is a NEW MultibranchPipeline description";

    @Test
    public void testCreate() {
        MultibranchProjectPage multibranchProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(MULTIBRANCH_NAME)
                .selectMultibranchAndClickOk()
                .clickSaveButton();

        Assert.assertEquals(multibranchProjectPage.getProjectName(), MULTIBRANCH_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testAddDescription() {
        String actualDescription = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_NAME, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .sendDescription(PROJECT_DESCRIPTION)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescription, PROJECT_DESCRIPTION);
    }

}
