package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DMKoroTest {

    @Test
    public void testCheckBox(){
        WebDriver driver = new ChromeDriver() ;
        driver.get("https://the-internet.herokuapp.com/") ;

        WebElement checkBoxLink = driver.findElement(By.xpath("//a[@href='/checkboxes']"));
        checkBoxLink.click();

        WebElement checkBox2 = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")) ;
        assertTrue(checkBox2.isSelected());

        driver.quit() ;
    }
}
