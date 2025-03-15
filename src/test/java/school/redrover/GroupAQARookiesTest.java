package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class GroupAQARookiesTest {

    @Test
    public void testOnliner() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.onliner.by/");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Onlíner");

        WebElement textBox = driver.findElement(By.xpath("//*[@id=\"fast-search\"]/div/input"));
        textBox.sendKeys("Iphone");

        WebElement iframe = driver.findElement(By.cssSelector("iframe.modal-iframe"));
        driver.switchTo().frame(iframe);
        Thread.sleep(2000);

        WebElement iphoneLink = driver.findElement(By.xpath("//a[contains(text(), 'Телефон Apple iPhone 16e 128GB (белый)')]"));
        iphoneLink.click();

        String title2 = driver.getTitle();
        Assert.assertEquals(title2, "iPhone 16e 128GB белый (Айфон 16е) купить в Минске");

        driver.quit();
    }

}
