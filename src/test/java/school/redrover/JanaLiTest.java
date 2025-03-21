package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JanaLiTest {

    @Test
    public void testRegisterUser()  {
        //WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.automationexercise.com/");

        WebElement homeLink = driver.findElement(By.cssSelector("a[href='/'][style='color: orange;']"));
        Assert.assertTrue(homeLink.isDisplayed());

        driver.findElement(By.cssSelector("a[href='/login")).click();

        WebElement newUserSignup = driver.findElement(By.cssSelector(".signup-form>h2"));
        Assert.assertTrue(newUserSignup.isDisplayed());

        driver.findElement(By.cssSelector("form[action='/signup']>input[name='name'][type='text']")).sendKeys("jana");
        driver.findElement(By.cssSelector("form[action='/signup']>input[type='email'][name='email']")).sendKeys("Li@gmail.com");
        driver.findElement(By.cssSelector("button[type='submit'][data-qa='signup-button']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.login-form > h2 > b")).getText(), "ENTER ACCOUNT INFORMATION");

        driver.quit();
    }
}
