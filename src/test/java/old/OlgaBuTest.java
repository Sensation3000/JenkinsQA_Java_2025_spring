package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class OlgaBuTest {

    @Test
    public void testProMixAcademy() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://promixacademy.com");

        driver.findElement(By.className("fusion-button-text")).click();
        driver.findElement(By.cssSelector("#pma_course_genre > option:nth-child(8)")).click();
        Thread.sleep(10000);

        driver.findElement(By.cssSelector("#content > div > div > div > div > div > div.fusion-text.fusion-text-2 > table > tbody > tr:nth-child(1) > td.o-table__pl.o-table__pb.u-fade-out-links.u-p-0 > div > div > h4 > a")).click();
        driver.findElement(By.xpath("//a[@ class = 'o-button o-subtext']")).click();

        WebElement count = driver.findElement(By.xpath("//span[@class='ms-1 ps-3 ps-md-0 product-name']"));
        String nameOfCourse = count.getText();

        Assert.assertEquals(nameOfCourse, "In the Mix with Motenko and Joe Carrell");
        
        driver.quit();


    }


}
