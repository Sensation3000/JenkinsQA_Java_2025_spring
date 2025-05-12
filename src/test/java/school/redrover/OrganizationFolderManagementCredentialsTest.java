package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.organizationfolder.OrganizationFolderPage;

public class OrganizationFolderManagementCredentialsTest extends BaseTest {
    private static final String ORGANIZATION_FOLDER_NAME = "TestFolder";
    private static final String TEST_CREDENTIALS_NAME = "TestName";
    private static final String TEST_PASSWORD = "password";

    @Test
    public void testCreateNewOrganizationFolder() {
        String testFolderName = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(ORGANIZATION_FOLDER_NAME)
                .selectOrganizationFolderAndClickOk()
                .clickSave()
                .getOrganizationFolderNameFromHeader();

        Assert.assertEquals(testFolderName, ORGANIZATION_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateNewOrganizationFolder")
    public void testConfigureOrganizationFolderCredentials() {
        String credentialsName = new OrganizationFolderPage(getDriver())
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
}
