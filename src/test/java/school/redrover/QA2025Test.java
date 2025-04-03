package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;


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

    @Test
    public void testNewMultibranchPipelineOnDashboard() throws InterruptedException {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("Test multibranch pipeline");
        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']")).sendKeys("Super cool pipeline name");
        getDriver().findElement(By.xpath("//textarea[@name='_.description']")).sendKeys("Super cool pipeline description.");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        Thread.sleep(1000);
        getDriver().findElement(By.id("jenkins-home-link")).click();
        getDriver().findElement(By.xpath("//*[@id='job_Test multibranch pipeline']/td[3]/a/span")).click();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("h1")).getText(),
                "Super cool pipeline name"
        );
    }

    @Test
    public  void testSubHeadingsText() {
        String[] subHeadings = {"Start building your software project", "Set up a distributed build"};
        List<WebElement> homePageSubHeadings = getDriver().findElements(By.xpath("//h2[@class='h4']"));

        for (int i = 0; i < subHeadings.length; i++){
            Assert.assertEquals(
                    homePageSubHeadings.get(i).getText(),
                    subHeadings[i]
            );
        }
    }

    @Test
    public void testSubItemsText() {
        String[] subItems = {"Create a job", "Set up an agent", "Configure a cloud", "Learn more about distributed builds"};
        List<WebElement> homePageSubItems = getDriver().findElements(By.cssSelector("a.content-block__link"));

        for (int i = 0; i < subItems.length; i++) {
            Assert.assertEquals(
                    homePageSubItems.get(i).getText(),
                    subItems[i]
            );
        }
    }

    @Test
    public void testTaskItemsSize() {
        Assert.assertEquals(getDriver().findElements(By.cssSelector("#tasks > .task")).size(), 4);
    }

    @Test
    public void testTaskItemsText() {
        String[] taskItems = {"New Item", "Build History", "Manage Jenkins", "My Views"};
        List<WebElement> homePageTaskItems = getDriver().findElements(By.cssSelector("#tasks .task-link-text"));

        for (int i = 0; i < taskItems.length; i++) {
            Assert.assertEquals(
                    homePageTaskItems.get(i).getText(),
                    taskItems[i]
            );
        }
    }

    @Test
    public void testMyViewsText () {
        Assert.assertEquals(getDriver().findElement(By.xpath("//span/a[@href=\"/me/my-views\"]")).getText(), "My Views"
        );
    }

    @Test
    public void testNewItemList () {
        getDriver().findElement(By.xpath("//a[@href=\"/view/all/newJob\"]")).click();

        String[] newItems = {"Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder"};
            for (int i = 0; i < newItems.length; i++) {
                Assert.assertEquals(
                        getDriver().findElements(By.xpath("//span[@class=\"label\"]")).get(i).getText(), newItems[i]
                );
            }
    }
}