package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class GroupRedRoverJavaUTC3Test {

    private WebDriver driver;

    private WebDriverWait wait5;
    private WebDriverWait wait10;

    protected WebDriverWait getWait5() {

        if (wait5 == null) {
            synchronized (WebDriverWait.class) {
                wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            }
        }

        return wait5;
    }

    protected WebDriverWait getWait10() {

        if (wait10 == null) {
            synchronized (WebDriverWait.class) {
                wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            }
        }

        return wait10;
    }

    protected WebDriver getDriver() {

        if (driver == null) {
            synchronized (WebDriver.class) {
                driver = new ChromeDriver();
            }
        }

        return driver;
    }

    @BeforeMethod
    protected void start() {

        getDriver();

        getDriver().manage().window().maximize();
    }

    @AfterMethod
    protected void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAuthorization() {

        getDriver().get("https://school.qa.guru/cms/system/login?required=true");

        getDriver().findElement(By.name("email")).sendKeys("gjcjavxusj@zvvzuv.com");

        getDriver().findElement(By.name("password")).sendKeys("E3&i&d1B");

        getDriver().findElement(By.id("xdget33092_1_1")).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='gcAccountUserMenu']/div/ul/li[1]/a"))).click();

        getDriver().findElement(
                By.xpath("//*[@id='gcAccountUserMenu']/div/div/ul/li[1]/a")).click();

        String getName = getDriver().findElement(
                By.xpath("/html/body/div[2]/div/div[1]/div/div[3]/h1")).getText();

        Assert.assertEquals(getName, "Мой профиль");
    }
}