package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
}
