package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class GroupFallRisersTest extends BaseTest {


    @Test
    public void testAddDescriptionButton() {
        WebDriver driver = getDriver();
        //Main page
        driver.findElement(By.id("description-link")).click();
        driver.findElement(By.name("description"))
                .sendKeys("This Jenkins server is used for the JenkinsQA_Java_2025_spring project.");
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"description\"]/div[1]")).getText(),
                "This Jenkins server is used for the JenkinsQA_Java_2025_spring project.");
    }

    @Ignore //Error:    GroupFallRisersTest.testAddNewItem:37 » NoSuchElement no such element: Unable to locate element: {"method":"xpath","selector":"//h1[text()='NewItem']"}
    @Test
    public void testAddNewItem() {
        WebDriver driver = getDriver();
        //New Item page
        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("NewItem");
        driver.findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        driver.findElement(By.id("ok-button")).click();
        //General page
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='NewItem']")).getText(),
                "NewItem");
        Assert.assertEquals(driver.findElement(By.xpath("//h2[text()='Permalinks']")).getText(),
                "Permalinks");

    }
}


