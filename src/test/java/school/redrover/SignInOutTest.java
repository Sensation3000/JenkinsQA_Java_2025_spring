package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;
import school.redrover.common.ProjectUtils;
import school.redrover.common.TestUtils;

import java.util.List;

public class SignInOutTest extends BaseTest {

    @DataProvider(name = "invalidCredentials")
    private Object[][] getData() {
        return new Object[][]{
                {TestUtils.generateRandomAlphanumeric(), ProjectUtils.getPassword()},
                {ProjectUtils.getUserName(), TestUtils.generateRandomAlphanumeric()},
                {"", ProjectUtils.getPassword()},
                {ProjectUtils.getUserName(), "" },
                {TestUtils.generateRandomAlphanumeric(), TestUtils.generateRandomAlphanumeric()},
                {"", "" }
        };
    }

    @Ignore
    @Test
    public void testSignOutSuccessfully() {
        JenkinsUtils.logout(getDriver());
        List<WebElement> logoutList = getDriver().findElements(By.xpath("//a[@href='/logout']"));

        JenkinsUtils.login(getDriver());
        TestUtils.waitForHomePageLoad(this);

        Assert.assertTrue(logoutList.isEmpty());
    }

    @Ignore
    @Test(dataProvider = "invalidCredentials")
    public void testInvalidCredentialsError(String testUserName, String testPassword) {
        JenkinsUtils.logout(getDriver());

        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("login")));
        JenkinsUtils.login(getDriver(), testUserName, testPassword);

        final String actualErrorText = getDriver().findElement(By.className("app-sign-in-register__error")).getText();

        JenkinsUtils.login(getDriver());
        TestUtils.waitForHomePageLoad(this);

        Assert.assertEquals(actualErrorText, "Invalid username or password");
    }

    @Test
    public void testSignInAsANewUser() {
        final String userName = "UserName";
        final String password = "P@ssword";
        final String newUserFullName = "User";
        final String email = "user@test.com";

        TestUtils.createNewUser(this, userName, password, newUserFullName, email);

        JenkinsUtils.logout(getDriver());

        JenkinsUtils.login(getDriver(), userName, password);
        TestUtils.waitForHomePageLoad(this);

        final String actualUserName = getDriver().findElement(By.cssSelector("#page-header a.model-link")).getText();

        Assert.assertEquals(actualUserName, newUserFullName);
    }
}
