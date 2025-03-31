package school.redrover;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import school.redrover.common.BaseTest;
import org.testng.annotations.Test;

import javax.swing.*;

public class CreateJobTest extends BaseTest {

    @Test
    public void testCreateJob () {

        getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/section[1]/ul/li/a")).click();
        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys("first project");
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[2]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[6]/span/button")).click();
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button[1]")).click();

        WebElement title = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1"));
        Assert.assertEquals(title.getText(),"first project");
    }

}
