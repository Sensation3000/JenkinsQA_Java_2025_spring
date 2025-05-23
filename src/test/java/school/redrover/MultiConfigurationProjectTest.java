package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.testdata.TestDataProvider;

import java.util.List;

public class MultiConfigurationProjectTest extends BaseTest {

    private static final String PROJECT_NAME = "Project name";
    private static final String PROJECT_SECOND_NAME = "Project name 2";

    @Test
    public void testErrorForProjectWithoutName() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .selectMultiConfiguration();

        Assert.assertTrue(newItemPage.getErrorMessageOnEmptyField().contains("This field cannot be empty, please enter a valid name"));
        Assert.assertFalse(newItemPage.isOkButtonEnabled());
    }

    @Test(dependsOnMethods = "testProjectDisabledMessageIsVisible")
    public void testTooltipIsVisible() {
        String tooltipIsVisible = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .checkTooltipVisibility();

        Assert.assertTrue(tooltipIsVisible.contains("tippy"));
    }

    @Test(dependsOnMethods = "testProjectDisabledMessageInvisible")
    public void testProjectDisabledMessageIsVisible() {
        boolean projectDisabledMessageIsVisible = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .clickEnableToggle()
                .clickSaveButton()
                .projectDisabledMessageCheck();

        Assert.assertTrue(projectDisabledMessageIsVisible);
    }

    @Test
    public void testProjectDisabledMessageInvisible() {
        boolean projectDisabledMessageInvisible = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(PROJECT_NAME, MultiConfigurationConfigurePage.class)
                .clickEnableToggle()
                .clickSaveButton()
                .clickEnableButton()
                .messageNotDisplayedCheck();

        Assert.assertTrue(projectDisabledMessageInvisible);
    }

    @Test(dependsOnMethods = "testTooltipIsVisible")
    public void testIfOriginalItemConfigurationIsCopied() {
        boolean isIfAllEnvironmentCheckboxesAreSelected = new HomePage(getDriver())
                .clickOnJobInListOfItems(PROJECT_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .scrollToEnvironmentSectionWithJS()
                .checkEnvironmentCheckboxesAndClickOnSaveButton()
                .getHeader()
                .goToHomePage()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(PROJECT_SECOND_NAME)
                .enterValueToCopyFromInput(PROJECT_NAME)
                .redirectToMultiConfigurationConfigurePage()
                .scrollToEnvironmentSectionWithJS()
                .verifyIfAllEnvironmentCheckboxesAreSelected();

        Assert.assertTrue(isIfAllEnvironmentCheckboxesAreSelected);
    }

    @Test(dataProvider = "projectNames", dataProviderClass = TestDataProvider.class)
    public void createWithValidName(String projectName) {
        List<String> projects= new HomePage(getDriver()).clickNewItemOnLeftSidePanel()
                .createNewItem(projectName, MultiConfigurationConfigurePage.class)
                .getHeader()
                .clickLogoIcon()
                .getProjectNameList();

        Assert.assertEquals(projects.size(), 1);
        Assert.assertEquals(projects.get(0), projectName);
    }
}