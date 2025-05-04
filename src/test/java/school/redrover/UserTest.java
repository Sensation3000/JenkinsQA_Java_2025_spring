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

    @Test(priority = 1)
    public void testCreateNewUser() {
        List<WebElement> users = new HomePage(getDriver())
                .getHeader()
                .goToHomePage()
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

        assertEquals(users.size(), 2);

    }

    @Test(priority = 2)
    public void testSignInAsExistingUser(){
        String logOutButtonText = new HomePage(getDriver())
                .getHeader()
                .goToHomePage()
                .clickLogOutButton()
                .setUserName(ProjectUtils.getUserName())
                .setPassword(ProjectUtils.getPassword())
                .clickSignInButton()
                .getLogOutButtonText();

        assertEquals(logOutButtonText,"log out");
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
                .getHeader()
                .goToHomePage()
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
                .getHeader()
                .goToHomePage()
                .clickManageJenkinsOnLeftSidePanel()
                .clickUsers()
                .clickCreateUserButton()
                .sendUserName(ProjectUtils.getUserName())
                .sendPassword(ProjectUtils.getPassword())
                .sendConfirmPassword(ProjectUtils.getPassword())
                .sendFullName(FULL_NAME)
                .sendEmailAddress(EMAIL)
                .clickCreateUserButton(CreateUserPage.class)
                .getUserNameErrorMessage();

        Assert.assertEquals(errorText, "User name is already taken");
    }

}
