package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;
import school.redrover.page.newitem.NewItemPage;

public class MultiConfigurationProjectConfigurationTest extends BaseTest {

    private static final String PROJECT_NAME = "Project name";

    @Test
    public void testErrorForProjectWithoutName() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .selectMultiConfiguration();

        Assert.assertTrue(newItemPage.getErrorMessageOnEmptyField().contains("This field cannot be empty, please enter a valid name"));
        Assert.assertFalse(newItemPage.isOkButtonEnabled());
    }

    @Test
    public void testTooltipIsVisible() {

        String tooltipIsVisible = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectMultiConfigurationAndClickOk()
                .checkTooltipVisibility();

        Assert.assertTrue(tooltipIsVisible.contains("tippy"));
    }

    @Test
    public void testProjectDisabledMessageIsVisible() {

        boolean projectDisabledMessageIsVisible = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectMultiConfigurationAndClickOk()
                .clickEnableToggle()
                .clickSaveButton()
                .projectDisabledMessageCheck();

        Assert.assertTrue(projectDisabledMessageIsVisible);
    }

    @Test
    public void testProjectDisabledMessageInvisible() {

        boolean projectDisabledMessageInvisible = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectMultiConfigurationAndClickOk()
                .clickEnableToggle()
                .clickSaveButton()
                .clickEnableButton()
                .messageNotDisplayedCheck();

        Assert.assertTrue(projectDisabledMessageInvisible);
    }

    @Test
    public void testIfOriginalItemConfigurationIsCopied() {

        MultiConfigurationConfigurePage multiConfigurationConfigurePage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_NAME)
                .selectMultiConfigurationAndClickOk()
                .scrollToEnvironmentSectionWithJS()
                .checkEnvironmentCheckboxesAndClickOnSaveButton()
                .getHeader()
                .goToHomePage()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(TestUtils.generateRandomAlphanumeric())
                .enterValueToCopyFromInput(PROJECT_NAME)
                .redirectToMultiConfigurationConfigurePage()
                .scrollToEnvironmentSectionWithJS();

        Assert.assertTrue(multiConfigurationConfigurePage.verifyIfAllEnvironmentCheckboxesAreSelected());
    }
}