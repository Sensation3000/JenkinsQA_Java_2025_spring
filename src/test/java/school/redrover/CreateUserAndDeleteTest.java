package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static org.testng.Assert.assertEquals;


    public  class CreateUserAndDeleteTest extends BaseTest {

        @Test
        public  void testCreateUser() {
            WebDriver driver  = getDriver();

            driver.findElement(By.xpath("//a[@href='/manage']")).click();
            driver.findElement(By.xpath("//a[@href='securityRealm/']")).click();
            driver.findElement(By.xpath("//a[@href='addUser']")).click();

            driver.findElement(By.xpath("//input[@name='username']")).sendKeys("TestUSer123");
            driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("Passw0rd_123");
            driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("Passw0rd_123");
            driver.findElement(By.xpath("//input[@name='fullname']")).sendKeys("Full Name");
            driver.findElement(By.xpath("//input[@name='email']")).sendKeys("mymail@mail.ru");
            driver.findElement(By.xpath("//button[@name='Submit']")).click();

            WebElement success = driver.findElement(By.xpath("//a[@href='user/testuser123/'][1]")) ;
             assertEquals(success.getText(),"TestUSer123");

             driver.findElement(By.xpath("//a[@data-url='user/testuser123/doDelete']")).click();
             getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//button[@data-id='ok']"))).click();
        }

    }

