package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class LogOutTest extends BaseTest {

    @Test
        void testLogOutFunctionalityWork() {
        String signInText = new HomePage(getDriver())
                .clickLogOutButton()
                .getSignIn();

        Assert.assertEquals(signInText,"Sign in to Jenkins");
    }
}

