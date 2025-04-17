package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class FreestyleProjectTest extends BaseTest {

    @Test
    public void testCreate() {
        final String projectName = "Freestyle Project";

        getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        TestUtils.openHomePage(this);
        String actualItem = getWait10()
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[@class='jenkins-table__link model-link inside']"))).getText();

        Assert.assertEquals(actualItem, projectName);
    }
}