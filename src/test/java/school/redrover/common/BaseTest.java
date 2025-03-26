package school.redrover.common;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public abstract class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod(Method method) {
        ProjectUtils.logf("Run %s.%s", this.getClass().getName(), method.getName());

        JenkinsUtils.clearData();

        driver = ProjectUtils.createDriver();
        ProjectUtils.get(driver);
        JenkinsUtils.login(driver);
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if (ProjectUtils.isRunCI() || testResult.isSuccess() || ProjectUtils.closeIfError()) {
            JenkinsUtils.logout(driver);
            driver.quit();
        }

        ProjectUtils.logf("Execution time is %.3f sec", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000.0);
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
