package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class lamodaTest {

    @Test
            public void testSearchLamoda() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.lamoda.ru/");
        String titleWebBrowser = driver.getTitle();
        assertEquals(titleWebBrowser, "Интернет магазин одежды и обуви. Купить обувь, купить одежду, аксессуары в онлайн магазине Lamoda.ru");
        WebElement search = driver.findElement(By.className("_input_mh0i8_19"));
        search.click();
        search.sendKeys("куртка");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
        WebElement result = driver.findElement(By.className("_titleText_1s7by_15"));
        String resultText = result.getText();
        assertEquals(resultText, "Товары по запросу «куртка»");
    }
    @Test
    public void testShoesLamoda() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.lamoda.ru/");
        Thread.sleep(1000);
        WebElement shoes = driver.findElement(By.xpath("//a[@href='/c/15/shoes-women/?sitelink=topmenuW&l=4']"));
        shoes.click();
        Thread.sleep(3000);

//WebElement title = driver.findElement(By.xpath("//h1"));
//String titleText = title.getText();
//assertEquals(title, "Женская обувь");
    }
}
