package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MultibranchPipeline2Test extends BaseTest {

    @Test
    public void testCreate() {
        final String projectName = "First Multibranch Pipeline";

        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(projectName);
        driver.findElement(By.xpath("//span[(.='Multibranch Pipeline')]")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.cssSelector("[name*='Submit']")).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link"))).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("a[class='jenkins-table__link model-link inside'] span")).getText(), projectName);
    }
}
