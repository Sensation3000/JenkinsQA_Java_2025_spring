package school.redrover;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.multibranch.MultibranchConfigurationPage;
import school.redrover.page.multibranch.MultibranchProjectPage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;

import school.redrover.testdata.TestDataProvider;

import static org.testng.Assert.assertEquals;


public class MultibranchPipelineTest extends BaseTest {

    private static final String MULTIBRANCH_NAME = "Multibranch Pipeline Job Test";
    private static final String PROJECT_DESCRIPTION = "This is a NEW MultibranchPipeline description";
    private static final String MULTIBRANCH_NEW_NAME = "New Multibranch Name";

    @Test
    public void testCreate() {
        String projectName = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(MULTIBRANCH_NAME)
                .selectMultibranchPipelineAndClickOkWithJS()            
                .clickSaveButton()
                .getProjectName();

        Assert.assertTrue(projectName.contains(MULTIBRANCH_NAME));
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

    @Test(dependsOnMethods = "testAddDescription")
    public void testChangeDisplayName() {
        String actualName = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_NAME, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .sendMultibranchName(MULTIBRANCH_NEW_NAME)
                .clickSaveButton()
                .getProjectName();

        Assert.assertEquals(actualName, MULTIBRANCH_NEW_NAME);
    }

    @Test(dataProvider = "provideInvalidCharacters", dataProviderClass = TestDataProvider.class)
    public void testCreateWithSpecialSymbols(String invalidSymbol) {
        String errorMessage  = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(MULTIBRANCH_NAME.concat(invalidSymbol))
                .selectItemType(MultibranchConfigurationPage.class)
                .getItemNameInvalidMessage();

        Assert.assertEquals(errorMessage, String.format("» ‘%s’ is an unsafe character", invalidSymbol));
    }

    @Test
    public void testTryCreateProjectExistName() {
        String errorMessage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(MULTIBRANCH_NAME, MultiConfigurationConfigurePage.class)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(MULTIBRANCH_NAME).selectMultiConfiguration()
                .getItemNameInvalidMessage();

        assertEquals(errorMessage, "» A job already exists with the name " + "‘" + MULTIBRANCH_NAME + "’");
    }

    @Test(dependsOnMethods = "testTryCreateProjectExistName")
    public void testVerifySectionHasTooltip() {
        int numberHelpButtons = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .clickAdvanced()
                .numberHelpTooltips();

        assertEquals(numberHelpButtons, 22);
    }
}
