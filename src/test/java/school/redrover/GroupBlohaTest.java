package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class GroupBlohaTest {

    private WebDriver driver;
    private static final String EMAIL = "alexx.shigaev@gmail.com";
    private static final String PASSWORD = "B2a6ig_a9Hb3cz@";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://magento.softwaretestingboard.com/");
        login(EMAIL, PASSWORD);
    }

    private void login(String email, String password) {
        driver.findElement(By.className("authorization-link")).click();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("send2")).click();
    }

    @Test
    public void addToWishlist() {

        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("ui-id-5")).click();
        driver.findElement(By.linkText("Tops")).click();
        driver.findElement(By.className("product-item-link")).click();
        driver.findElement(By.xpath("//span[text()='Add to Wish List']")).click();

        String wishlistMessage = driver.findElement(By.cssSelector(".message-success")).getText();
        Assert.assertTrue(wishlistMessage.contains("Cassius Sparring Tank has been added to your Wish List. Click here to continue shopping."), "Товар не был добавлен в виш-лист");
    }

    @Test
    public void removeItemFromWishlist() {
        driver.get("https://magento.softwaretestingboard.com/wishlist/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

          while (true) {
            List<WebElement> productItems = driver.findElements(By.xpath("//div[contains(@class, 'product-item-info')]"));
            
            if (productItems.isEmpty()) {
                System.out.println("You have no items in your wish list");
                break;
            }
    
            try {
                WebElement productItem = productItems.get(0);
                new Actions(driver).moveToElement(productItem).perform();

                productItem.findElement(By.xpath(".//a[contains(@class, 'btn-remove action delete')]")).click();
                
                wait.until(ExpectedConditions.invisibilityOf(productItem));
                System.out.println("Product deleted");
            } catch (Exception e) {
                System.out.println("Error occurred while deleting a product.");
            }
        }
    
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='wishlist-view-form']/div[1]")).isDisplayed(),
                "Message when wishlist is empty is not displayed");
        System.out.println("Message when wishlist is empty is displayed");

        driver.quit();
    }


    @Test
    public void searchAndAddToCart() {
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
       driver.findElement(By.xpath("*//input[@value='Login']")).click();


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
  
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
