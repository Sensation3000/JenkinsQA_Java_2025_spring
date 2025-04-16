package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import school.redrover.common.BaseTest;
import org.testng.annotations.Test;
import school.redrover.common.JenkinsUtils;
import school.redrover.common.ProjectUtils;
import school.redrover.common.TestUtils;

public class  AccessProjectTest extends BaseTest {
    private static final String NAME_PROJECT = "first project";

    @Test
    public void testLinkProject () {
        TestUtils.newItemCreate(this, NAME_PROJECT, 4);

        getDriver().findElement(By.xpath("//*[@id='job_" + NAME_PROJECT + "']/td[3]/a/span")).click();

        WebElement title = getDriver().findElement(By.cssSelector("h1"));
        Assert.assertEquals(title.getText(),NAME_PROJECT);
    }
}
