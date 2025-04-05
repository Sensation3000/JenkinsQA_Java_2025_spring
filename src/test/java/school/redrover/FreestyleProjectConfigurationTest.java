package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestyleProjectConfigurationTest extends BaseTest {

    private void createFreestyleProject(String projectName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("name"))).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testDisableProject() {
       createFreestyleProject("Freestyle");

       getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='enable-disable-project']"))).click();
       getDriver().findElement(By.name("Submit")).click();
       String projectIsDisabledText = getDriver().findElement(By.id("enable-project")).getText();

       Assert.assertTrue(projectIsDisabledText.contains("This project is currently disabled"));
    }
}
