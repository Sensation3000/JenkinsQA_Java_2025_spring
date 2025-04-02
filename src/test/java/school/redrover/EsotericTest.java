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
}
