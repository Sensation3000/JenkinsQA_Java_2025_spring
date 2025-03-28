package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class QA2025Test extends BaseTest {

    @Test
    public void testAddDescriptionLink() {
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div/a[@class=\"jenkins-button\"]")).getText(),
                "Add description");
    }

    @Test
    public void testAddDescriptionLinkAvailable() {
        Assert.assertTrue(getDriver().findElement(By.xpath("//div/a[@class=\"jenkins-button\"]")).isEnabled());
    }

    @Test
    public void testGreetingsIsPresented() {
        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".empty-state-block h1")).getText(),
                "Welcome to Jenkins!"
        );
    }

    @Test
    public void testVersionButtonIsPresented() {
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).getText(),
                "Jenkins 2.492.2"
        );
    }

    @Test
    public void testJenkinsVersion() {
        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();
        getDriver().findElement(By.xpath("//a[@href='/manage/about']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.className("app-about-version")).getText(),
                "Version 2.492.2"
        );
    }
}
