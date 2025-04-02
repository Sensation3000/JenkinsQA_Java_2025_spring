package school.redrover.common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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

        if (!testResult.isSuccess()) {
            try {
                File screenshotDir = new File("screenshots");
                if (!screenshotDir.exists() && !screenshotDir.mkdirs()) {
                    throw new RuntimeException("Failed to create a folder for screenshots");
                }
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                String className = testResult.getTestClass().getRealClass().getSimpleName();
                String testName = testResult.getTestName();
                String fileName = className + "_" + testName + ".png";

                File destination = new File(screenshotDir, fileName);
                Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ProjectUtils.logf("Execution time is %.3f sec", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000.0);
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
