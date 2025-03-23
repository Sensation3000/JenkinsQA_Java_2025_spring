package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

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
}