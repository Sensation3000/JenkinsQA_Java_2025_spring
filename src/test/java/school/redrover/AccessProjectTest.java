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
    private final String nameProject = "first project";
    private final String locatorProject = "//*[@id='job_first project']/td[3]/a/span";

    @BeforeMethod
    protected void createJob () {

        getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/section[1]/ul/li/a")).click();
        getDriver().findElement(By.id("name")).sendKeys(nameProject);
        getDriver().findElement(By.id("j-add-item-type-nested-projects")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[6]/span/button")).click();
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button[1]")).click();

        TestUtils.gotoHomePage(this);
    }

    @Test
    public void testVisibleProject () {

        WebElement title = getDriver().findElement(By.xpath(locatorProject));
        Assert.assertEquals(title.getText(),nameProject);
    }

    @Test
    public void testLinkProject () {
        getDriver().findElement(By.xpath(locatorProject)).click();

        WebElement title = getDriver().findElement(By.cssSelector("h1"));
        Assert.assertEquals(title.getText(),nameProject);
    }
}
