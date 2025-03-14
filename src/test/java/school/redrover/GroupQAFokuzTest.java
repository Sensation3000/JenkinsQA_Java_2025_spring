package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class GroupQAFokuzTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testWebForm() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement textInput = driver.findElement(By.xpath("//*[@id='my-text-id']"));
        textInput.sendKeys("Denis N");
        Assert.assertEquals(textInput.getAttribute("value"),
                "Denis N", "Текстовое поле содержит неверное значение");

        WebElement password = driver.findElement(By.xpath("//*[@name='my-password']"));
        password.sendKeys("123456");
        Assert.assertEquals(password.getAttribute("value"),
                "123456", "Поле пароля содержит неверное значение");

        WebElement textArea = driver.findElement(By.xpath("//*[@name='my-textarea']"));
        textArea.sendKeys("This is a text");
        Assert.assertEquals(textArea.getAttribute("value"),
                "This is a text", "Текстовая область содержит неверное значение");

        WebElement radioButton1 = driver.findElement(By.xpath("//*[@id='my-radio-2']"));
        radioButton1.click();
        Assert.assertTrue(radioButton1.isSelected(), "Радио-кнопка не выбрана");

        WebElement btnSubmit = driver.findElement(By.xpath("//*[@class='btn btn-outline-primary mt-3']"));
        btnSubmit.click();

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl != null;
        Assert.assertTrue(currentUrl.contains("submitted"), "Форма не была отправлена");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}