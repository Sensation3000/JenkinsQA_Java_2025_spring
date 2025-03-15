package school.redrover;

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
import org.testng.annotations.Test;
import java.time.Duration;


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

    @Test
    public void btnClick() {
        driver.get("https://demoqa.com/buttons");

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement DoubleClickMe = driver.findElement(By.xpath("//*[@id='doubleClickBtn']"));
        actions.doubleClick(DoubleClickMe).perform();

        WebElement rightClickMe = driver.findElement(By.xpath("//*[@id='rightClickBtn']"));
        actions.contextClick(rightClickMe).perform();

        WebElement clickMe = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Click Me']")));
        actions.click(clickMe).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='doubleClickMessage']"))
                .isDisplayed(), "Кнопка была не нажата");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='rightClickMessage']"))
                .isDisplayed(), "Кнопка была не правым кликом");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='dynamicClickMessage']"))
                .isDisplayed(), "Кнопка была не нажата");
    }

    @Test
    public void radioButton() {
        driver.get("https://demoqa.com/radio-button");

        Actions actions = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        WebElement btnYes = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='yesRadio']"))
        );
        actions.click(btnYes).perform();

        WebElement btnImpressive = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='impressiveRadio']"))
        );
        actions.click(btnImpressive).perform();

        WebElement btnNo = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='noRadio']"))
        );
        actions.click(btnNo).perform();


        String textYes = btnYes.getText();
        Assert.assertEquals(textYes, "Yes", "Текст элемента не совпадает с 'Yes'");
        String textImp = btnImpressive.getText();
        Assert.assertEquals(textImp, "Impressive", "Текст элемента не совпадает с 'Impressive'");
        String textNo = btnNo.getText();
        Assert.assertEquals(textNo, "No", "Текст элемента не совпадает с 'No'");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}