package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import java.util.List;
import java.util.stream.Collectors;

public class SignInTest extends BaseTest {

    private static final String USERNAME = "UserName";
    private static final String PASSWORD = "P@ssword";
    private static final String WRONG_USERNAME = "UserName1";
    private static final String WRONG_PASSWORD = "P@ssword1";
    private static final String EMAIL = "test@gmail.com";
    private static final String FULLNAME = "User Full Name";

    @Test
    public void testCreateNewUser() {
        List<String> userIds = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickUsers()
                .clickCreateUserButton()
                .sendUserName(USERNAME)
                .sendPassword(PASSWORD)
                .sendConfirmPassword(PASSWORD)
                .sendFullName(FULLNAME)
                .sendEmailAddress(EMAIL)
                .clickCreateUserButton()
                .getUserIdLinks().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertTrue(userIds.contains(USERNAME));
    }

    @Test (dependsOnMethods = "testCreateNewUser")
    public void testSignInAsExistingUser(){
        String logOutButtonText = new HomePage(getDriver())
                .clickLogOutButton()
                .setUserName(USERNAME)
                .setPassword(PASSWORD)
                .clickSignInButton()
                .getLogOutButtonText();

        Assert.assertEquals(logOutButtonText,"log out");
    }

    @Test(dependsOnMethods = "testSignInAsExistingUser")
    public void testErrorForInvalidCPassword() {
        Boolean loginError = new HomePage(getDriver())
                .clickLogOutButton()
                .setPassword(WRONG_PASSWORD)
                .setUserName(USERNAME)
                .clickSignInButtonUseWrongCredentials()
                .isErrorTextShown();

        Assert.assertEquals(loginError,true);
    }
}
