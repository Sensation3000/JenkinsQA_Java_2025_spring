package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;
import school.redrover.common.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Properties;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static org.testng.Assert.assertTrue;

public class PlugInTest extends BaseTest {
    private String userName;
    private String password;

    @Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @Target({METHOD, TYPE})
    @interface SkipConfiguration {
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(Method method) {
        SkipConfiguration skip = method.getAnnotation(SkipConfiguration.class);
        if (skip != null) {
            ProjectUtils.log("Skipping AfterMethod for " + method.getName());
            ProjectUtils.log("Logging into Jenkins.");
            setCredentials();
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
        setCredentials();
        getDriver().findElement(By.name("j_username")).sendKeys(this.userName);
        getDriver().findElement(By.name("j_password")).sendKeys(this.password);
        getDriver().findElement(By.name("Submit")).click();
        TestUtils.waitForHomePageLoad(this);
    }

    @Test
    public void testInstallPlugIn2() {
        final String local = "Locale";

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='pluginManager']")).click();
        getDriver().findElement(By.xpath("//a[@href='/manage/pluginManager/available']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")));
        getDriver().findElement(By.xpath("//span[@class='jenkins-checkbox']")).click();
        getDriver().findElement(By.id("button-install")).click();

        WebElement success = getDriver().findElement(
                By.xpath("//td[text()='Locale']/following-sibling::td"));
        getWait10().until(ExpectedConditions.textToBePresentInElement(success, "Success"));
        getDriver().findElement(
                By.xpath("//a[@href='/manage/pluginManager/installed']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        String plugin = getDriver().findElement(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")).getText();

        assertTrue(plugin.contains("Locale plugin"), "Plugin Locale is not in the installed list");
    }

    @Test
    @SkipConfiguration
    public void testUnInstallPlugIn() throws InterruptedException {
        final String local = "Locale";

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='pluginManager']")).click();
        getDriver().findElement(By.xpath("//a[@href='/manage/pluginManager/installed']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-href='plugin/locale/doUninstall']"))).click();
        getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();

        Thread.sleep(500);
        getDriver().get("http://localhost:8080/safeRestart");
        Thread.sleep(500);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        Thread.sleep(1000);
        getDriver().navigate().refresh();
        Thread.sleep(10000);
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.className("app-sign-in-register__content-inner")));
        loginToJenkins();

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='pluginManager']")).click();
        getDriver().findElement(By.xpath("//a[@href='/manage/pluginManager/available']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")));
        String plugin = getDriver().findElement(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")).getText();

        assertTrue(plugin.contains(local), "Plugin Locale is not in the available list");
    }
}

