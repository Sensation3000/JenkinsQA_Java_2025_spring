package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class DisplaySystemInfoTest extends BaseTest {

    @Test
    public void testButtonsNames() {
        WebDriver driver = getDriver();

        String manageJ = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/span/a/span[2]")).getText();
        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]")).click();
        String systemSet = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[1]/a/dl/dt")).getText();

        Assert.assertEquals(manageJ, "Manage Jenkins");
        Assert.assertEquals(systemSet, "System Information");
    }

    @Test
    public void testShowSystemProperties() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[3]/div/button[1]")).click();

        WebElement vendor = driver.findElement(By.xpath("//*[.='java.specification.vendor']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", vendor);
        String javaVendor = driver.findElement(By.xpath("//*[.='java.specification.vendor']/following-sibling::td/div[2]")).getText();

        Assert.assertEquals(javaVendor, "Oracle Corporation");
    }

    @Test
    public void testShowEnviromentalVariables() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div[2]/a")).click();

        WebElement example = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[4]/table/tbody/tr/td/div[1]"));
        String infoHiddenClass = example.getDomAttribute(("class"));

        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[4]/div/button[1]")).click();

        String infoShownClass = example.getDomAttribute(("class"));

        Assert.assertEquals(infoHiddenClass, "app-hidden-info-reveal");
        Assert.assertEquals(infoShownClass, "app-hidden-info-reveal jenkins-hidden");
    }

    @Test
    public void testPluginsInfo() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div[3]/a")).click();

        String plugin1 = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[5]/table/tbody/tr[1]/td[2]")).getText();
        String plugin2 = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[5]/table/tbody/tr[2]/td[2]")).getText();

        Assert.assertNotNull(plugin1);
        Assert.assertNotNull(plugin2);
    }
}

