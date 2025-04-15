package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ManageJenkinsConfigurationTest extends BaseTest {
    @Test
    public void testModifyGlobalSystemParameters() {

        WebDriver driver = getDriver();

        driver.findElement(By.linkText("Manage Jenkins")).click();
        getWait10().until(f -> f.findElement(By.cssSelector("a[href*='configure']"))).click();
        getWait10().until(f -> f.getCurrentUrl().contains("/configure"));

        WebElement executorsInput = getWait10().until(a -> a.findElement(By.name("_.numExecutors")));

        String newExecutorsValue = "4";
        executorsInput.clear();
        executorsInput.sendKeys(newExecutorsValue);

        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        driver.findElement(By.linkText("Manage Jenkins")).click();
        driver.findElement(By.cssSelector("a[href*='configure']")).click();

        WebElement updatedExecutorsInput = getWait10().until(f -> f.findElement(By.name("_.numExecutors")));
        String actualExecutorsValue = updatedExecutorsInput.getDomProperty("value");

        Assert.assertEquals(actualExecutorsValue, newExecutorsValue, "# of Executors was not updated correctly");
    }
}
