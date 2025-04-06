package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import static org.testng.Assert.assertTrue;

public class PlugInTest extends BaseTest {

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
}

