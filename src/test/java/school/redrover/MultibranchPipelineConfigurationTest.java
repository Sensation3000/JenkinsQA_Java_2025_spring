package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MultibranchPipelineConfigurationTest extends BaseTest {
    @Test
    public void testEnableDisablePipeline() {
        getDriver().findElement(By.xpath("//a[@data-task-success='Done.']")).click();
        getDriver().findElement(By.id("name")).sendKeys("TEST MULTIBRANCH PIPELINE");
        getDriver().findElement(By.xpath("//span[text()='Multibranch Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        Assert.assertEquals(
                getDriver().findElement(By.className("jenkins-toggle-switch__label__checked-title")).getText(),
                "Enabled");
    }
}
