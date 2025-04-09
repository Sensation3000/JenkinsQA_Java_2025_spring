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
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
       WebElement manageButton = driver.findElement(By.xpath("//a[@href='/manage']"));
       Assert.assertEquals(manageButton.getText(),"Manage Jenkins");
       Thread.sleep(1000);




    }


}
