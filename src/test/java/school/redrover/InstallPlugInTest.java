package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;

import static org.testng.Assert.assertTrue;

public class InstallPlugInTest extends BaseTest {

    @Test
    public void testInstallPlugIn() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//dt[text()='Plugins']")).click();
        getDriver().findElement(By.xpath("//a[@href='/manage/pluginManager/available']")).click();
        getDriver().findElement(By.xpath("//input[@id='filter-box']")).sendKeys("Locale");

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://plugins.jenkins.io/locale']")));
        getDriver().findElement(By.xpath("//span[@class='jenkins-checkbox']")).click();
        getDriver().findElement(By.xpath("//button[@id='button-install']")).click();

        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//a[@href='/manage/pluginManager/installed']")).click();
        getDriver().findElement(By.xpath("//input[@id='filter-box']")).sendKeys("Locale");

        String plugin= getDriver().findElement(By.xpath("//a[@href='https://plugins.jenkins.io/locale']")).getText();
        assertTrue(plugin.contains("Locale plugin"), "Plugin Locale is not in the installed list");
    }
}
