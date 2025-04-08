package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class LogOutTest extends BaseTest {

        @Test
        public void testLogoutFunctionality() {
            WebDriver driver = getDriver();
            WebDriverWait waiter5 = getWait5();

            WebElement logOutButton = waiter5.until(ExpectedConditions.elementToBeClickable
                    (By.xpath("//span[text()='log out']")));

            Assert.assertEquals(logOutButton.getText(),"log out");
        }
}

