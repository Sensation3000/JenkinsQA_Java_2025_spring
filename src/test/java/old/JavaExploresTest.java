package old;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class JavaExploresTest {

    @Test
    public void testPizza() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver= new ChromeDriver();
        driver.get("https://dodopizza.ru/moscow");

        Thread.sleep(1000);

        WebElement desserts = driver.findElement(By.xpath("/html/body/div[1]/nav/div/nav/ul[1]/li[7]/a"));
        desserts.click();

        WebElement dessert = driver.findElement(By.xpath("/html/body/div[1]/main/section[7]/article[1]/div/div[2]/a/span"));
        String value = dessert.getText();
        Assert.assertEquals(value, "Тирамису");

        driver.quit();
    }
}
