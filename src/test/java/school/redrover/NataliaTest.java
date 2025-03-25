package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NataliaTest {
    @Test
    public static void checkOutToContactSuccessfully() throws RuntimeException{
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
