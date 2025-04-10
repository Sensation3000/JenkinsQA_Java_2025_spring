package school.redrover.testdata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class CreateUserTest  extends BaseTest {

    @Test
    public  void testCreateUser() throws InterruptedException {
       WebDriver driver  = getDriver() ;
       getWait5();
       WebElement manageButton = driver.findElement(By.xpath("//a[@href='/manage']"));
       manageButton.click();
       //Assert.assertEquals(manageButton.getText(),"Manage Jenkins");

       getWait10();
       WebElement userButton = driver.findElement(By.xpath("//a[@href='securityRealm/']"));
       userButton.click();
        getWait10();

        WebElement createUserButton = driver.findElement(By.xpath("//a[@href='addUser']"));
        createUserButton.click();

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("TestUSer123");
        driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("Passw0rd_123");
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("Passw0rd_123");
        driver.findElement(By.xpath("//input[@name='fullname']")).sendKeys("Full Name");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("mymail@mail.ru");
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
        WebElement success = driver.findElement(By.xpath("//a[@href='user/testuser123/'][1]"));
        Assert.assertEquals(success.getText(),"TestUSer123");
        Thread.sleep(1333);





    }


}
