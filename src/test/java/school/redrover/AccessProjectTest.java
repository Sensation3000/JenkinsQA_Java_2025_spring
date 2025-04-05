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
    private static final String NAMEPROJECT = "first project";
    private static final String LOCATORPROJECT = "//*[@id='job_" + NAMEPROJECT + "']/td[3]/a/span";

    @Test
    public void testVisibleProject () {
        TestUtils.createFolder(getDriver(), NAMEPROJECT);
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[2]/span/button")).click();
        TestUtils.gotoHomePage(this);

        WebElement title = getDriver().findElement(By.xpath(LOCATORPROJECT));
        Assert.assertEquals(title.getText(),NAMEPROJECT);
    }

    @Test
    public void testLinkProject () {
        TestUtils.createFolder(getDriver(), NAMEPROJECT);
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[3]/span/button")).click();
        TestUtils.gotoHomePage(this);

        getDriver().findElement(By.xpath(LOCATORPROJECT)).click();

        WebElement title = getDriver().findElement(By.cssSelector("h1"));
        Assert.assertEquals(title.getText(),NAMEPROJECT);
    }
}
