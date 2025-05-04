package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import static org.testng.Assert.assertEquals;


public class LoginTest extends BaseTest {

    private static final String WRONG_PASSWORD = "P@ssword1";
    private static final String USER_NAME = "testUser";

    @Test
    public void testErrorForInvalidCPassword() {
        Boolean loginError = new HomePage(getDriver())
                .clickLogOutButton()
                .setPassword(WRONG_PASSWORD)
                .setUserName(USER_NAME)
                .clickSignInButtonUseWrongCredentials()
                .isErrorTextShown();

        assertEquals(loginError,true);
    }
}
