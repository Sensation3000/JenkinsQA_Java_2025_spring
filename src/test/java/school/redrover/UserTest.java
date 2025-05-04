package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;
import school.redrover.component.HeaderComponent;
import school.redrover.page.HomePage;
import school.redrover.page.user.CreateUserPage;
import school.redrover.page.user.UsersPage;
import java.util.List;
import static org.testng.Assert.assertEquals;


public class UserTest extends BaseTest {
    private static final String USER_NAME = "testUser";
    private static final String FULL_NAME = "Test User";
    private static final String PASSWORD = "123Test";
    private static final String EMAIL = "testuser@test.com";
    private static final String WRONG_PASSWORD = "P@ssword1";

    @Test
    public void testCreateNewUser() {
        List<WebElement> users = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickUsers()
                .clickCreateUserButton()
                .sendUserName(USER_NAME)
                .sendPassword(PASSWORD)
                .sendConfirmPassword(PASSWORD)
                .sendFullName(FULL_NAME)
                .sendEmailAddress(EMAIL)
                .clickCreateUserButton(UsersPage.class)
                .getUsersList();

        UsersPage usersPage = new UsersPage(getDriver());

        assertEquals(users.size(), 2);
        assertEquals(usersPage.getUserIdTdText(), USER_NAME);
        assertEquals(usersPage.getNameTdText(), FULL_NAME);
    }

    @Test (dependsOnMethods = "testCreateNewUser")
    public void testSignInAsExistingUser(){
        String logOutButtonText = new HomePage(getDriver())
                .clickLogOutButton()
                .setUserName(USER_NAME)
                .setPassword(PASSWORD)
                .clickSignInButton()
                .getLogOutButtonText();

        assertEquals(logOutButtonText,"log out");
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testErrorForInvalidCPassword() {
        Boolean loginError = new HomePage(getDriver())
                .clickLogOutButton()
                .setPassword(WRONG_PASSWORD)
                .setUserName(USER_NAME)
                .clickSignInButtonUseWrongCredentials()
                .isErrorTextShown();

        Assert.assertEquals(loginError,true);
    }

    @Test
    public void testSearchUser() {
        String currentAdminIDText = new HeaderComponent(getDriver())
                .clickSearchButton()
                .sendSearchText("admin")
                .clickSearch()
                .getAdminIDText();

        assertEquals(currentAdminIDText, "admin");
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void deleteUser() {
        List<WebElement> users = new HomePage(getDriver())
                .clickLogOutButton()
                .setUserName(ProjectUtils.getUserName())
                .setPassword(ProjectUtils.getPassword())
                .clickSignInButton()
                .clickManageJenkinsOnLeftSidePanel()
                .clickUsers()
                .clickDeleteUserButton(USER_NAME)
                .clickSubmitDeleteUserButton()
                .getUsersList();

        assertEquals(users.size(), 1);
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testCreateExistingUser() {
        String errorText = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickUsers()
                .clickCreateUserButton()
                .sendUserName(USER_NAME)
                .sendPassword(PASSWORD)
                .sendConfirmPassword(PASSWORD)
                .sendFullName(FULL_NAME)
                .sendEmailAddress(EMAIL)
                .clickCreateUserButton(CreateUserPage.class)
                .getUserNameErrorMessage();

        Assert.assertEquals(errorText, "User name is already taken");
    }

}
