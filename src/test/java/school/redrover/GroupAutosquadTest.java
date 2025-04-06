package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class GroupAutosquadTest extends BaseTest {

    @Test
    public void testWelcomPageJenkins() {
        WebDriver driver = getDriver();

        WebElement welcom = driver.findElement(By.cssSelector("#main-panel > div:nth-child(3) > div > h1"));
        Assert.assertEquals(welcom.getText(), "Welcome to Jenkins!");
    }
    @Test
    public void testCreateNewItem() throws InterruptedException {
        WebDriver driver = getDriver();

        WebElement newItem = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span"));
        newItem.click();

        driver.findElement(By.id("name")).sendKeys("My New Item");
        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();

        Thread.sleep(3000);

        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        WebElement headPage = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[1]/div[1]/h1"));
        Assert.assertEquals(headPage.getText(), "My New Item");
    }
}
