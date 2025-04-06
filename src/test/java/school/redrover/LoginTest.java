package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;
import school.redrover.common.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

public class LoginTest extends BaseTest {
    private String userName;
    private String password;


    @BeforeMethod(alwaysRun = true)
    private void beforeMethod() {
        WebElement logoutLink = TestUtils.waitForHomePageLoad(this);
        logoutLink.click();
    }

    @Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @Target({METHOD, TYPE})
    private @interface SkipConfiguration {
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(Method method) {
        SkipConfiguration skip = method.getAnnotation(SkipConfiguration.class);
        if (skip != null) {
            ProjectUtils.log("Skipping AfterMethod for " + method.getName());
            ProjectUtils.log("Logging into Jenkins.");
            setCredentials();
            loginToJenkins();
        }
    }

    private void setLocalCredentials() throws IOException {
        final Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/.properties");
        properties.load(fileInputStream);
        this.userName = properties.getProperty("jenkins.username");
        this.password = properties.getProperty("jenkins.password");
    }

    private void setCICredentials() {
        this.userName = System.getenv("JENKINS_USERNAME");
        this.password = System.getenv("JENKINS_PASSWORD");
    }

    private void setCredentials() {
        try {
            if (System.getenv("RUN_CI") == null) {
                setLocalCredentials();
            } else {
                setCICredentials();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loginToJenkins() {
            getDriver().findElement(By.name("j_username")).sendKeys(this.userName);
            getDriver().findElement(By.name("j_password")).sendKeys(this.password);
            getDriver().findElement(By.name("Submit")).click();
            TestUtils.waitForHomePageLoad(this);
    }

    private void loginToJenkins(String userName, String password) {
        getDriver().findElement(By.name("j_username")).sendKeys(userName);
        getDriver().findElement(By.name("j_password")).sendKeys(password);
        getDriver().findElement(By.name("Submit")).click();
    }

    @DataProvider(name = "invalidCredentials")
    private Object[][] getData() {
        setCredentials();

        return new Object[][] {
                {TestUtils.generateRandomAlphanumeric(), this.password},
                {this.userName, TestUtils.generateRandomAlphanumeric()},
                {"", this.password},
                {this.userName, ""},
                {TestUtils.generateRandomAlphanumeric(), TestUtils.generateRandomAlphanumeric()},
                {"", ""}
        };
    }

    @Test
    @SkipConfiguration
    public void testLogoutSuccessfully() {
        List<WebElement> logoutList = getDriver().findElements(By.xpath("//a[@href='/logout']"));

        Assert.assertTrue(logoutList.isEmpty());
    }

    @Test(dataProvider = "invalidCredentials")
    @SkipConfiguration
    public void testInvalidCredentialsError(String testUserName, String testPassword) {
        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("login")));
        loginToJenkins(testUserName, testPassword);

        final String actualErrorText = getDriver().findElement(By.className("app-sign-in-register__error")).getText();

        Assert.assertEquals(actualErrorText, "Invalid username or password");
    }
}
