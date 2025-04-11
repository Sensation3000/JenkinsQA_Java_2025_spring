package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestyleProject3Test extends BaseTest {
    @Test
    public void testCreate (){
        final String projectName = "TestProject1";

        getDriver().findElement(By.linkText("Create a job")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.className("label")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("general")));
        getDriver().findElement(By.id("jenkins-home-link")).click();

        String actualName = getDriver().findElement(By.xpath(
               "//table[@id='projectstatus']//tbody//a[@class='jenkins-table__link model-link inside']"))
               .getText();

        Assert.assertEquals(actualName, projectName);
    }
}
