package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OshTest {
    @Test
    public void LearningEnglish() throws InterruptedException {
        WebDriver driver = new ChromeDriver();


        driver.get("https://learningenglish.voanews.com/p/5610.html");


        driver.findElement(By.xpath("//*[@id=\"page\"]/div[1]/div/div/div[1]/label[2]")).click();


        WebElement textBox = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div/form/input"));
        textBox.sendKeys("learning english");

        Thread.sleep(1000);

        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"form-topSearchHeader\"]/button"));
        searchButton.click();

        Thread.sleep(1000);

        WebElement title = driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/ul/li[1]/div/div/a/h4"));
        String titleText = title.getText();

        assertEquals(titleText, "Learning English Podcast");

        driver.quit();
    }
}