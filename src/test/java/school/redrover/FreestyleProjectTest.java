package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class FreestyleProjectTest extends BaseTest {

    @Ignore
    @Test
    public void testCreate() {
        final String projectName = "FreestyleProjectTestName";

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.id("name")).sendKeys(projectName);
        driver.findElement(By.className("hudson_model_FreeStyleProject")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();
        TestUtils.gotoHomePage(this);

        Assert.assertEquals(
                driver.findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText(),
                projectName);
    }
}
