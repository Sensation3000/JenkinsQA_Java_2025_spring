package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

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

    @BeforeMethod
    private void beforeMethod() {
        getDriver().findElement(By.xpath("//a[@href='/logout']")).click();
    }

    @Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @Target({METHOD, TYPE})
    private @interface SkipConfiguration {
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(Method method) {
        SkipConfiguration skip = method.getAnnotation(SkipConfiguration.class);
        if (skip != null) {
            System.err.println("Skipping AfterMethod for " + method.getName());
            System.err.println("Login to the Jenkins.");
            try {
                final Properties properties = new Properties();
                String userName = "";
                String password = "";

                if (System.getenv("RUN_CI") == null) {
                    FileInputStream fileInputStream = new FileInputStream("src/test/resources/.properties");
                    properties.load(fileInputStream);
                    userName = properties.getProperty("jenkins.username");
                    password = properties.getProperty("jenkins.password");
                } else {
                    userName = System.getenv("JENKINS_USERNAME");
                    password = System.getenv("JENKINS_PASSWORD");
                }
                getDriver().findElement(By.name("j_username")).sendKeys(userName);
                getDriver().findElement(By.name("j_password")).sendKeys(password);
                getDriver().findElement(By.name("Submit")).click();
                Thread.sleep(2000);

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.err.println("Login succeed.");
        System.err.println("Running AfterMethod for " + method.getName());
    }

    @Test
    @SkipConfiguration
    public void testLogoutSuccessfully() {
        List<WebElement> logoutList = getDriver().findElements(By.xpath("//a[@href='/logout']"));

        Assert.assertTrue(logoutList.isEmpty());
    }
}
