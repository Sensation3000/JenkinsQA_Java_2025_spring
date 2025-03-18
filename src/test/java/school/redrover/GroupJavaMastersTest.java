package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupJavaMastersTest {

    @Test
    public void loginTest() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        WebElement userNameTextField = driver.findElement(By.id("user-name"));
        userNameTextField.sendKeys("standard_user");

        WebElement passwordTextField = driver.findElement(By.cssSelector(".input_error.form_input#password"));
        passwordTextField.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.xpath("//form/input[@type='submit']"));
        loginButton.click();

        WebElement products = driver.findElement(By.xpath("//span[text()='Products']"));

        Assert.assertEquals(products.getText(), "Products");

        driver.quit();
    }

}
