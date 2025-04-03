package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class EsotericTest extends BaseTest {

    @Test
    public void testVersionText() {

        WebDriver driver = getDriver();

        WebElement version_button = driver.findElement(By.cssSelector("button.jenkins_ver"));
        Assert.assertEquals(version_button.getText(), "Jenkins 2.492.2");
    }


    @Test
    public void testNaughtySpinnerWait() {

        WebDriver driver = getDriver();
        Actions builder = new Actions(driver);

        driver.findElement(By.cssSelector("button.jenkins_ver")).click();
        builder.moveToElement(driver.findElement(By.cssSelector("a[href='/manage/about']"))).click().perform();

        WebElement jenkinsVersion = driver.findElement(By.cssSelector("p.app-about-version"));
        Assert.assertEquals(jenkinsVersion.getText(), "Version 2.492.2");
    }
}
