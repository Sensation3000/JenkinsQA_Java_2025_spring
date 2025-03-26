package old;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Locale;

import static org.testng.Assert.assertEquals;

@Ignore
public class StudentUnionTest {
    @Test
    public void testEmex() throws InterruptedException {
        WebDriverManager.chromedriver().setup(); //нужно писать в каждом тесте для запуска браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver= new ChromeDriver(options);  //Создание экземпляра WebDriver
        driver.get("https://emex.ru/"); //Открытие браузера и переход на страницу

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label=\"Кнопка раскрытия меню\"]")));
        button.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Шины и диски"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"/catalogs2/201\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"sc-bbd2add2-5 bLTFtG\"]"))).click();
        Thread.sleep(2000);
        WebElement h1= driver.findElement(By.xpath("//h1"));
        String result= h1.getText();
        Assert.assertEquals(result, "Шина летняя легковая 155/70R13 75T");

        driver.quit();

    }

    @Test
    public void testFitnessAnswers() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://ppbasis.tutorplace.ru");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement sex = driver.findElement(By.xpath("//label[@for=\"1\"]"));
        sex.click();

        WebElement answer1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for=\"4\"]")));
        answer1.click();

        WebElement answer2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for=\"9\"]")));
        answer2.click();

        WebElement answer3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for=\"12\"]")));
        answer3.click();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"quiz-item__next-button button button--main\"]")));
        String result = button.getText();

        Assert.assertEquals(result, "Продолжить");

        driver.quit();
    }

    @Test
    public void testFitnessRegistration() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://tutorplace.ru/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement reg = driver.findElement(By.linkText("Зарегистрироваться"));
        reg.click();

        WebElement name = driver.findElement(By.name("name"));
        name.sendKeys("Anna");

        WebElement number = driver.findElement(By.xpath("//input[@type=\"text\"]"));
        number.sendKeys("123");

        WebElement telegram = driver.findElement(By.name("telegram"));
        telegram.sendKeys("@telegram");

        WebElement buttonNext = driver.findElement(By.cssSelector("button"));
        buttonNext.click();

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        email.sendKeys("anna@tutorplace.ru");

        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        password.sendKeys("password");

        WebElement repeatPassword = wait.until(ExpectedConditions.elementToBeClickable(By.name("repeatPassword")));
        repeatPassword.sendKeys("password");

        WebElement buttonRegistration = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".register-button")));
        buttonRegistration.click();

        WebElement spanError = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/form/div[2]/div[1]/div/div/div[1]/span")));
        String result = spanError.getText();

        Assert.assertEquals(result, "E-mail уже зарегистрирован!");

        driver.quit();
    }

    @Test
    public static void checkOutToContactSuccessfully() throws RuntimeException{
        // Наталья Миронова.
        String url = "https://www.xn----8sbeh6debq.xn--p1ai/";

        // 1. Автоматическая загрузка chromedriver (не нужно указывать путь вручную)
        WebDriverManager.chromedriver().setup();

        // 2. Настройки браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Открыть на полный экран

        WebDriver driver = new ChromeDriver(options);

        // 3. Открываем сайт
        driver.get(url);
        System.out.println("Сайт открыт: " + url);

        // 4. Находим элемент (правильный способ)
        WebElement button = driver.findElement(By.xpath("//*[@id='menu-item-8556']"));

        // 5. Кликаем по кнопке
        button.click();

        String xpathContact = "//*[@id='obernut']/h1";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Note. Это не действие, которое мы проверяем. Это то, что есть на каждой странице сайта
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathContact)));

        // Проверка. Убеждаемся что мы на странице "контакты"
        assertEquals(element.getText().toUpperCase(Locale.ROOT),"КОНТАКТЫ");

        driver.quit();
    }
}