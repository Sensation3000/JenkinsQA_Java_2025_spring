package school.redrover;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;
import school.redrover.common.TestUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

public class LogOutTest extends BaseTest {

private final String LOGOUTBUTTON = "//span[text()='log out']";

    @Test
    public void testLogoutFunctionalityExist() {
        WebElement logOutButton = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(LOGOUTBUTTON)));

        Assert.assertEquals(logOutButton.getText(),"log out");
    }

    @Test
        void testLogoutFunctionalityWork() {
        WebElement logOutbutton = getWait5().until(ExpectedConditions.elementToBeClickable
                (By.xpath(LOGOUTBUTTON)));
        logOutbutton.click();

        WebElement signInButton = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//h1[text()='Sign in to Jenkins']")));
        Assert.assertEquals(signInButton.getText(),"Sign in to Jenkins");
    }
}

