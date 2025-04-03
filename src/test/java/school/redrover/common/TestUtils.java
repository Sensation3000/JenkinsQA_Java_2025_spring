package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {

    public static void gotoHomePage(BaseTest baseTest) {
        WebDriverWait wait10 = baseTest.getWait10();
        wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("jenkins-home-link")));
        WebElement homeLink = wait10.until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link")));
        homeLink.click();
    }
}
