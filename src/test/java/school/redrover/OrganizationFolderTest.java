package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.page.organizationfolder.OrganizationFolderConfigurePage;
import school.redrover.page.organizationfolder.OrganizationFolderPage;

import java.util.List;

public class OrganizationFolderTest extends BaseTest {

    private static final String ORGANIZATION_FOLDER_NAME = "OrganizationFolder";
    private static final String ORGANIZATION_FOLDER_NAME_2 = "OrganizationFolder2";
    private static final String DISPLAY_NAME = "OrganizationFolderDisplayName";

    @Test
    public void testCreateOrganizationFolder() {
        List<String> projectNameList = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ORGANIZATION_FOLDER_NAME)
                .selectOrganizationFolderAndClickOk()
                .getHeader()
                .clickLogoIcon()
                .getProjectNameList();

        Assert.assertListContainsObject(
                projectNameList, ORGANIZATION_FOLDER_NAME, "Organization Folder is not created");
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testCreateOrganizationFolderWithDisplayName() {
        List<String> projectNameList = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ORGANIZATION_FOLDER_NAME_2)
                .selectOrganizationFolderAndClickOk()
                .setOrganizationFolderDisplayName(DISPLAY_NAME)
                .clickApply()
                .getHeader()
                .clickLogoIcon()
                .getProjectNameList();

        Assert.assertListContainsObject(
                projectNameList, DISPLAY_NAME, "Organization Folder is not created");
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolderWithDisplayName")
    public void testEmptyDisplayName() {
        List<String> projectNameList = new HomePage(getDriver())
                .clickOnJobInListOfItems(DISPLAY_NAME, new OrganizationFolderPage(getDriver())
                .clickConfigureOnLeftSidePanel())
                .clearOrganizationFolderDisplayName()
                .clickApply()
                .getHeader()
                .clickLogoIcon()
                .getProjectNameList();

        Assert.assertListNotContainsObject(projectNameList, ORGANIZATION_FOLDER_NAME_2,
                "Organization folder is not available");
    }

    @Test (dependsOnMethods = "testCreateOrganizationFolder")
    public void testIconHelp() {
        OrganizationFolderConfigurePage organizationFolderConfigurePage = new HomePage(getDriver())
                .clickOnJobInListOfItems(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigureOnLeftSidePanel()
                .clickAppearance();

        boolean isIconHelpBlockDisplayed = organizationFolderConfigurePage
                .clickIconHelp()
                .isIconHelpBlockDisplayed();

        Assert.assertEquals(organizationFolderConfigurePage.getIconHelpTooltip(), "Help for feature: Icon");
        Assert.assertTrue(isIconHelpBlockDisplayed);
    }

    @Test (dependsOnMethods = "testIconHelp")
    public void testAvailableIconsForOrganizationFolder() {
        List<String> availableIcons = new HomePage(getDriver())
                .clickOnJobInListOfItems(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigureOnLeftSidePanel()
                .clickAppearance()
                .getAvailableIcons();

        Assert.assertEquals(availableIcons, List.of("Default Icon", "Metadata Folder Icon"));
    }

    @Test (dependsOnMethods = "testAvailableIconsForOrganizationFolder")
    public void testSelectDefaultIconForOrganizationFolder() {
        OrganizationFolderPage organizationFolderPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigureOnLeftSidePanel()
                .clickAppearance()
                .selectIcon("Default Icon")
                .clickSave();

        Assert.assertEquals(organizationFolderPage.getOrganizationFolderIconTitleNameFromHeader(), "Folder");
    }

    @Test
    public void testTryToCreateOrganizationFolderWithEmptyName() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .selectItemByName("Organization Folder");

        Assert.assertFalse(newItemPage.isOkButtonEnabled());
        Assert.assertEquals(
                newItemPage.getEmptyNameMessage(), "» This field cannot be empty, please enter a valid name");
    }

    @Test(dependsOnMethods = "testSelectDefaultIconForOrganizationFolder")
    public void testCancelOrganizationFolderDeletion(){
        String orgFolderPageHeader = new HomePage(getDriver())
                .clickOnJobInListOfItems(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickDeleteOrganizationFolderOnLeftSidePanel()
                .clickCancelOnDeletionConfirmationPopup()
                .getOrganizationFolderNameFromHeader();

        List<String> projectNameList = new HomePage(getDriver())
                .getHeader()
                .clickLogoIcon()
                .getProjectNameList();

        Assert.assertEquals(orgFolderPageHeader, ORGANIZATION_FOLDER_NAME);
        Assert.assertListContainsObject(projectNameList, ORGANIZATION_FOLDER_NAME,"Organization folder is deleted");
    }

    @Test (dependsOnMethods = "testEmptyDisplayName")
    public void testDeleteEmptyOrganizationFolderFromFolderPage() {
        List<String> projectNameList = new HomePage(getDriver())
                .clickOnJobInListOfItems(ORGANIZATION_FOLDER_NAME_2, new OrganizationFolderPage(getDriver()))
                .clickDeleteOrganizationFolderOnLeftSidePanel()
                .clickYesOnDeletionConfirmationPopup()
                .getHeader()
                .clickLogoIcon()
                .getProjectNameList();

        Assert.assertListNotContainsObject(projectNameList, ORGANIZATION_FOLDER_NAME_2,
                "Organization folder is NOT deleted");
    }

    @Test(dependsOnMethods = "testCancelOrganizationFolderDeletion")
    public void testDeleteOrganizationFolderFromDropDownMenuOnDashboard() {
        List<String> projectNameList = new HomePage(getDriver())
                .showDropdownOnHoverByJobName(ORGANIZATION_FOLDER_NAME)
                .clickDeleteItemFromDropdown(ORGANIZATION_FOLDER_NAME)
                .clickYesOnDeletionConfirmationPopup()
                .getProjectNameList();

        Assert.assertFalse(projectNameList.contains(ORGANIZATION_FOLDER_NAME));
    }
}
