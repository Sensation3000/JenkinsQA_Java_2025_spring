package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;

public class AddPluginsTest extends BaseTest {
    @Ignore
    @Test
    public void plugin() {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Clicking the 'Manage Jenkins' button
        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/span/a")).click();

        // Clicking the 'Plugins' button
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[2]/div/div[3]/a")).click();

        // Clicking the 'Available Plugins' button
        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[2]/span/a")).click();

        // Clicking on the checkbox
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for=\"plugin.pipeline-rest-api.default\"]")));
        driver.findElement(By.xpath("//label[@for=\"plugin.pipeline-rest-api.default\"]")).click();

        // Clicking the 'Install' button
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-install")));
        driver.findElement(By.id("button-install")).click();

        // Verifying the 'Success' status
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//tr[td[text()='Pipeline: REST API']]/td[2]")), "Success"));
        Assert.assertEquals(driver.findElement(By.xpath("//tr[td[text()='Pipeline: REST API']]/td[2]")).getText(), "Success");

        // Clicking the 'Installed plugins' button
        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/span/a")).click();

        // Locating and entering "Pipeline: REST API" text in the text field
        driver.findElement(By.id("filter-box")).sendKeys("Pipeline: REST API Plugin");

        // Verifying the "Pipeline: REST API Plugin" presence in the list
        List<WebElement> plugins = driver.findElements(By.xpath("//tr[@data-plugin-name=\"Pipeline: REST API Plugin\"]"));
        Assert.assertTrue(!plugins.isEmpty());
    }
}