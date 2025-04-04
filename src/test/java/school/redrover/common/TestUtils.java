package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.UUID;

public class TestUtils {

    public static void gotoHomePage(BaseTest baseTest) {
        baseTest.getWait10()
                .until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link")))
                .click();
    }

    public static WebElement waitForHomePageLoad(BaseTest baseTest) {
        return baseTest
                .getWait5()
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/logout']")));
    }

    public static String generateRandomAlphanumeric() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void moveAndClickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('click'));", element);
    }
}
