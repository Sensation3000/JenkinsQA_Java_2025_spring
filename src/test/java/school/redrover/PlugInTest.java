package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
@Ignore
public class PlugInTest extends BaseTest {

    @Test
    public void testInstallPlugIn2() {
        final String local = "Locale";

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='pluginManager']")).click();
        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("page-body")));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Available plugins"))).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")));
        getDriver().findElement(By.xpath("//span[@class='jenkins-checkbox']")).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("button-install"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[text()='Locale']/following-sibling::td")));
        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("td svg ellipse[stroke='var(--success-color)']")));
        getDriver().findElement(
                By.xpath("//a[@href='/manage/pluginManager/installed']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        String plugin = getDriver().findElement(
                By.xpath("//a[@href='https://plugins.jenkins.io/locale']")).getText();

        assertTrue(plugin.contains("Locale plugin"), "Plugin Locale is not in the installed list");
    }

    @Test(dependsOnMethods = "testInstallPlugIn2")
    public void testUnInstallPlugIn() {
        final String local = "Locale";

        getDriver().findElement(By.cssSelector("a[href='/manage']")).click();
        getDriver().findElement(By.cssSelector("a[href='pluginManager']")).click();
        getDriver().findElement(By.cssSelector("a[href='/manage/pluginManager/installed']")).click();
        getDriver().findElement(By.id("filter-box")).sendKeys(local);
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-href='plugin/locale/doUninstall']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-dialog")));
        getDriver().findElement(By.cssSelector("button[data-id='ok']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("page-body")));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("filter-box"))).sendKeys(local);

        assertEquals(getDriver().findElement(
                        By.xpath("//tr[@data-plugin-name='Locale plugin']//td[contains(@class,'uninstall')]")).getText(),
                "Uninstallation pending");
    }
}