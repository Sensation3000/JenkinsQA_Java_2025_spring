package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    public  void testSubHeadingsText(){
        String[] subheadings = {"Start building your software project", "Set up a distributed build"};
        for (int i = 0; i < subheadings.length; i++){
            Assert.assertEquals(
                    getDriver().findElements(By.xpath("//h2[@class='h4']")).get(i).getText(),
                    subheadings[i]
            );
        }
    }

    @Test
    public void testSubItemsText() {
        String[] subItems = {"Create a job", "Set up an agent", "Configure a cloud", "Learn more about distributed builds"};
        for (int i = 0; i < subItems.length; i++){
            Assert.assertEquals(
                    getDriver().findElements(By.cssSelector("a.content-block__link")).get(i).getText(),
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
        for (int i = 0; i < taskItems.length; i++){
            Assert.assertEquals(
                    getDriver().findElements(By.cssSelector("#tasks .task-link-text")).get(i).getText(),
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

    @Test
    public void testSubmitWhenFieldIsEmpty() {
        Actions actions = new Actions(getDriver());
        String expectedErrorMessage = "» This field cannot be empty, please enter a valid name";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        List<WebElement> newItemTypes = getDriver().findElements(By.cssSelector("input[type=radio]"));

        for (int i = 0; i < newItemTypes.size(); i++) {
            actions.scrollByAmount(0, 300).perform();
            getDriver().findElements(By.xpath("//li[@role='radio']")).get(i).click();

            Assert.assertEquals(getDriver().findElement(By.cssSelector(".input-validation-message")).getText(), expectedErrorMessage);
            Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
        }
    }
}