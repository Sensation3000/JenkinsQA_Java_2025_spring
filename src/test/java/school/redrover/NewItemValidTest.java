package school.redrover;

import java.time.Duration;

import static java.sql.DriverManager.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import school.redrover.common.BaseTest;

public class NewItemValidTest extends BaseTest {

    @Test
    public void testCreateItemSameName() {
        WebDriver driver = getDriver();
        final String textName = "First item project test";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")))
            .click();

        driver.findElement(By.id("name")).sendKeys(textName);
        driver.findElement(By.className("hudson_model_FreeStyleProject")).click();
        driver.findElement(By.id("ok-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")))
            .click();
        driver.findElement(By.id("name")).sendKeys(textName);

        String validMessage = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"itemname-invalid\"]")))
            .getText();

        Assert.assertEquals(validMessage,"» A job already exists with the name ‘First item project test’");
    }

}
