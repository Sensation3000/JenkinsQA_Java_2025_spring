package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertTrue;

public class PlugInTest extends BaseTest {

    @Test
    public void testInstallPlugIn() {
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
        getDriver().findElement(By.xpath("//a[@href='/manage/pluginManager/installed']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        String plugin = getDriver().findElement(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")).getText();

        assertTrue(plugin.contains("Locale plugin"), "Plugin Locale is not in the installed list");
    }
    @Ignore
    @Test
    public void testUnInstallPlugIn() throws InterruptedException {
        final String local = "Locale";

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='pluginManager']")).click();
        getDriver().findElement(By.xpath("//a[@href='/manage/pluginManager/installed']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        getDriver().findElement(By.xpath("//button[@data-href='plugin/locale/doUninstall']")).click();
        getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/manage/pluginManager/updates/']"))).click();

        getDriver().findElement(By.xpath("//label[@class='attach-previous ']")).click();

        WebElement signIn = getDriver().findElement(
                By.xpath("//div[@class='app-sign-in-register__content-inner']/h1"));
        getWait10().until(ExpectedConditions.textToBePresentInElement(
                signIn,"Sign in"));

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

        getDriver().findElement(By.xpath("//a[@href='/manage/pluginManager/available']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")));

        String plugin = getDriver().findElement(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")).getText();

        assertTrue(plugin.contains(local), "Plugin Locale is not in the available list");
    }
}
