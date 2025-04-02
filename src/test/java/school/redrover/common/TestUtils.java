package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestUtils {

    public static void gotoHomePage(BaseTest baseTest) {
        baseTest.getWait10()
                .until(ExpectedConditions.presenceOfElementLocated(By.id("jenkins-name-icon")))
                .click();
    }
}
