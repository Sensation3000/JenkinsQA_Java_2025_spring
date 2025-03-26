package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.testng.AssertJUnit.assertEquals;

@Ignore
public class ZeroToQAHeroTest {

    private WebDriver driver;

    private WebDriverWait getWait(int seconds) {

        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    @BeforeMethod
    public void initDriver() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testProductToCart() throws InterruptedException {
        driver.manage().window().maximize();

        driver.get("https://www.demoblaze.com/");
        Thread.sleep(2000);

        List <WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'col-lg-4 col-md-6')]//h4/a[contains(@href, 'prod')]"));
        Assert.assertEquals(elements.size(), 9);

        String itemTitle = elements.get(0).getText();
        elements.get(0).click();
        getWait(3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//a[@onclick='addToCart(1)']"))).click();

        getWait(3).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        WebElement cart = driver.findElement(By.xpath("//div//a[@href='cart.html']"));
        cart.click();

        String actualItemInCart =  getWait(3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//tr[@class='success']/td[2]"))).getText();
        Assert.assertEquals(actualItemInCart,itemTitle);
    }

    @Test
    public void testLogin() throws InterruptedException {
        Thread.sleep(500);
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();

        String pageName = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        assertEquals("error: wrong page or missing text", "Products", pageName);
    }

    @Test
    public void testFailedLogin() {
        String randomUserName = UUID.randomUUID().toString();
        String randomUserPswd = UUID.randomUUID().toString();

        driver.get("https://www.saucedemo.com/");

        String title = driver.getTitle();

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys(randomUserName);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(randomUserPswd);

        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();

        WebElement error = driver.findElement(By.xpath("//*[@data-test='error']"));

        assertEquals("Swag Labs", title);
        assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                error.getText());
    }

    @Test
    public void testLogIn() {
        driver.get("https://demo.applitools.com/"); //открываем главную страницу

        WebElement usernameBox = driver.findElement(By.id("username"));
        usernameBox.sendKeys("Natasha_test");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("12345678!");

        WebElement SignIn = driver.findElement(By.id("log-in"));
        SignIn.click();
    }

    @Test
    public void testTabletka() {
        driver.get("https://tabletka.by/"); //открываем главную страницу

        WebElement textBox = driver.findElement(By.className("ls-select-input"));
        textBox.sendKeys("Валидол");

        WebElement submitLupa = driver.findElement(By.className("lupa"));
        submitLupa.click();

        WebElement search = driver.findElement(By.className("tooltip-info-header"));
        String searchText = search.getText();

        Assert.assertEquals(searchText, "ВАЛИДОЛ");
    }

    @Test
    public void testSortItems() {
        driver.get("https://www.saucedemo.com/");

        String title = driver.getTitle();

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();

        WebElement sortMenu = driver.findElement(By.xpath("//*[@data-test='product-sort-container']"));
        sortMenu.click();

        WebElement sortOption = driver.findElement(By.xpath("//*[@data-test='product-sort-container']/option[3]"));
        sortOption.click();

        WebElement productName = driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div"));

        assertEquals("Swag Labs", title);
        assertEquals("Sauce Labs Onesie", productName.getText());
    }

    @Test
    public void tesGlobalsqaCom() {
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        driver.findElement(By.xpath("//button[contains(@class, 'btn btn-primary')][1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        driver.findElement(By.name("userSelect")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.findElement(By.xpath("//div[@class='form-group']/*/option[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        assertEquals(
                "Harry Potter",
                driver.findElement(By.xpath("//span[contains(text(), 'Harry')]")).getText());
    }

    @Test
    public void testSettingFilters() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.get("https://www.parasoft.com/");
        driver.manage().window().maximize();

        WebElement buttonTryMe = driver.findElement(By.linkText("Try Parasoft"));
        buttonTryMe.click();

        WebElement filterSolutions = driver.findElement(By.xpath("//h4[contains(text(),'Solutions')]"));
        actions.moveToElement(filterSolutions).click().perform();

        WebElement filter_In_Solutions = getWait(3).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Reporting & Analytics')]")));
        actions.moveToElement(filter_In_Solutions).click().perform();

        WebElement filterIndustries = getWait(3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Industries')]")));
        actions.moveToElement(filterIndustries).click().perform();

        WebElement filter_In_Industries = getWait(3).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Telecommunications')]")));
        actions.moveToElement(filter_In_Industries);
        actions.click(filter_In_Industries).perform();

        WebElement searchButton = driver.findElement(By.cssSelector("input[name='_sf_submit']"));
        actions.moveToElement(searchButton).click().perform();

        List<WebElement> searchResultNames = driver.findElements(By.cssSelector("div.b-product-icon h4"));
        ArrayList<String> searchResults = new ArrayList<>(0);
        for(WebElement name: searchResultNames){
            searchResults.add(name.getText());
        }

        Assert.assertEquals(searchResults.toString(),
                "[C/C++test, C/C++test CT, Jtest, dotTEST, DTP, CTP, Selenic, SOAtest, Virtualize]",
                "Names should be the same!");
    }

    @Test
    public void testRadio(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://www.radiodetali.perm.ru/");

        String title = driver.getTitle();
        assertEquals("Радиодетали и электронные компоненты. Интернет-магазин.", title);

        WebElement textBox = driver.findElement(By.name("search"));
        WebElement submitButton = driver.findElement(By.cssSelector(".subm"));

        textBox.sendKeys("KIT-MP700");
        submitButton.click();

        WebElement message = driver.findElement(By.cssSelector(".itemsdata0"));
        String value = message.getText();
        assertEquals("94821", value);
    }
}
