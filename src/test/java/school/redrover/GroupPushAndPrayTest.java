package school.redrover;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupPushAndPrayTest {
//    @Test
    public void firstTryToMakeTest() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.goodfon.ru/");

        WebElement strokaPoiska = driver.findElement(By.className("js-search"));
        strokaPoiska.sendKeys(Keys.CONTROL + "a");
        strokaPoiska.sendKeys(Keys.DELETE);
        strokaPoiska.sendKeys("Дворцовая площадь");

        WebElement knopka = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[1]/form/button"));
        knopka.click();

        String ssilka = driver.getCurrentUrl();

        Assert.assertEquals(ssilka, "https://www.goodfon.ru/search/?q=%D0%94%D0%B2%D0%BE%D1%80%D1%86%D0%BE%D0%B2%D0%B0%D1%8F+%D0%BF%D0%BB%D0%BE%D1%89%D0%B0%D0%B4%D1%8C");

        driver.quit();
    }

    @Test
    public void iWantToBuyTheScrewdrivers() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://emex.ru/");

        WebElement burger = driver.findElement(By.xpath("//button[@data-testid='Header:button:openMenuButton']"));

        Thread.sleep(2000);
        burger.click();

        Thread.sleep(1000);

        WebElement tools = driver.findElement(By.xpath("//a[text()='Инструменты']"));
        tools.click();

        WebElement screwdrivers = driver.findElement(By.xpath("//a[text()='Отвёртки']"));
        screwdrivers.click();
        Thread.sleep(1000);

        WebElement sortByPrice = driver.findElement(By.xpath("//span[@data-text='Цена']"));
        sortByPrice.click();
        Thread.sleep(1000);
        sortByPrice.click();
        Thread.sleep(1000);

        WebElement expensiveTool = driver.findElement(By.xpath("//div[contains(text(),'CrMo') and contains(text(),'Gross')]"));
        expensiveTool.click();

        Thread.sleep(1000);

        String ssilka = driver.getCurrentUrl();
        Assert.assertEquals(ssilka, "https://emex.ru/catalogs2/248/165254157?productCode=12949&makeName=Gross&filters=");

        driver.quit();
    }
}