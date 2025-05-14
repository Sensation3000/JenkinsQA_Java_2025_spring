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
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.multibranch.MultibranchProjectPage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;

import static org.testng.Assert.assertEquals;


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

    @Test(dataProvider = "provideInvalidCharacters", dataProviderClass = TestDataProvider.class)
    public void testCreateWithSpecialSymbols(String invalidSymbol) {
        String errorMessage  = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(MULTIBRANCH_NAME.concat(invalidSymbol))
                .selectMultibranchPipeline()
                .getItemNameInvalidMessage();

        Assert.assertEquals(errorMessage, String.format("» ‘%s’ is an unsafe character", invalidSymbol));
    }

    @Test(dependsOnMethods = "testTryCreateProjectExistName")
    public void testVerifySectionHasTooltip() {
        new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .clickAdvanced();

        assertEquals(new FreestyleConfigurationPage(getDriver()).numberHelpTooltips(), 22);
    }

    @Test
    public void testTryCreateProjectExistName() {
        String errorMessage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(MULTIBRANCH_NAME)
                .selectMultiConfigurationAndClickOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(MULTIBRANCH_NAME).selectMultiConfiguration()
                .getItemNameInvalidMessage();

        assertEquals(errorMessage, "» A job already exists with the name " + "‘" + MULTIBRANCH_NAME + "’");
    }
}
