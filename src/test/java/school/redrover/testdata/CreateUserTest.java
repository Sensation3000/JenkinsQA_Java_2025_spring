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




    }


}
