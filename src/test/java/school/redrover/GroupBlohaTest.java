package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class GroupBlohaTest {

    @Test
    public void magentoTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.id("ui-id-5")).click();
        driver.findElement(By.linkText("Tops")).click();
        driver.findElement(By.className("product-item-link")).click();
        driver.findElement(By.xpath("//span[text()='Add to Wish List']")).click();

        driver.quit();

    }

    @Test
    public void magentoSearchAndAddToCartTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://magento.softwaretestingboard.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.findElement(By.id("ui-id-5")).click();
        driver.findElement(By.linkText("Tops")).click();
        driver.findElement(By.className("product-item-link")).click(); 

        driver.findElement(By.id("option-label-color-93-item-50")).click();
        driver.findElement(By.id("option-label-size-143-item-166")).click();
        driver.findElement(By.xpath("//*[@id='product-addtocart-button']")).click();
        
        String successMessage = driver.findElement(By.cssSelector(".message-success")).getText();
        Assert.assertTrue(successMessage.contains("You added Cassius Sparring Tank to your shopping cart."), "Товар не был добавлен в корзину");

        driver.quit();
    }
    @Test
    public void hpTest() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://195.91.225.98:81/login.php");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        String title = driver.getTitle();
        Assert.assertEquals(title, "xigmanas.internal");

        driver.findElement(By.id("username")).sendKeys("guest");
        driver.findElement(By.id("password")).sendKeys("redrover");
        driver.findElement(By.xpath( "*//input[@value='Login']")).click();


        Assert.assertEquals(title, "xigmanas.internal");
        driver.findElement(By.id("system"));

        WebElement modelByxpath = driver.findElement(By.xpath("/html/body/main/div/div[2]/table/tbody/tr[6]/td[2]/span"));
        WebElement model = driver.findElement(By.id("system"));
        String modelByx = modelByxpath.getText();
        String modelName = model.getText();
        Assert.assertEquals(modelName, "HP ProLiant MicroServer");
        Assert.assertEquals(modelByx, "HP ProLiant MicroServer");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.quit();
    }
}