package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.credentials.GlobalCredentialsPage;
import school.redrover.page.organizationfolder.OrganizationFolderConfigurePage;

public class OrganizationFolderManagementCredentialsTest extends BaseTest {

    private static final String ORGANIZATION_FOLDER_NAME = "TestFolder";
    private static final String TEST_CREDENTIALS_NAME = "TestName";
    private static final String UPDATED_CREDENTIALS_NAME = "UpdatedTestName";
    private static final String TEST_PASSWORD = "Password2025!";
    private static final String UPDATED_TEST_PASSWORD = "UpdatedPassword2025!";

    @Test
    public void testCreateNewOrganizationFolder() {
        String testFolderName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(ORGANIZATION_FOLDER_NAME, OrganizationFolderConfigurePage.class)
                .clickSave()
                .getOrganizationFolderNameFromHeader();

        Assert.assertEquals(testFolderName, ORGANIZATION_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateNewOrganizationFolder")
    public void testConfigureOrganizationFolderCredentials() {
        String credentialsName = new GlobalCredentialsPage(getDriver())
                .clickProjectName()
                .clickLeftSideCredentials()
                .clickStoredFolderName()
                .clickDomainFolderName()
                .addCredentialsButton()
                .sendItemCredentialsName(TEST_CREDENTIALS_NAME)
                .sendItemCredentialsPassword(TEST_PASSWORD )
                .clickCreateButton()
                .getCredentialsName();

        Assert.assertEquals(credentialsName, TEST_CREDENTIALS_NAME + "/******");
    }

    @Ignore
    @Test(dependsOnMethods = "testConfigureOrganizationFolderCredentials")
    public void testUpdateOrganizationFolderCredentials() {
        String updatedCredentialsName = new GlobalCredentialsPage(getDriver())
                .clickProjectName()
                .clickLeftSideCredentials()
                .clickStoredFolderName()
                .clickDomainFolderName()
                .clickUpdateTooltip()
                .sendItemCredentialsName(UPDATED_CREDENTIALS_NAME)
                .clickChangePasswordButton()
                .sendItemCredentialsPassword(UPDATED_TEST_PASSWORD)
                .clickSaveButton()
                .getCreatedCredentialName();

        Assert.assertEquals(updatedCredentialsName, UPDATED_CREDENTIALS_NAME + "/******");
    }

    @Ignore
    @Test(dependsOnMethods = "testUpdateOrganizationFolderCredentials")
    public void testDeleteOrganizationFolderCredentials() {
        String emptyCredentialsStatus = new GlobalCredentialsPage(getDriver())
                .clickProjectName()
                .clickLeftSideCredentials()
                .clickStoreCredentialName()
                .clickDeleteCredentialButton()
                .clickYesButton()
                .getEmptyCredentialsStatus();

        Assert.assertTrue(emptyCredentialsStatus.contains("This credential domain is empty."));
    }
}
