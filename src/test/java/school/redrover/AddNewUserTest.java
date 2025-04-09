package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;



    public class AddNewUserTest extends BaseTest {

        @Test
        public void testCreateNewUser() {
            Actions actions = new Actions(getDriver());
            getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
            WebElement buttonUsers = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@href='securityRealm/']")));
            actions.moveToElement(buttonUsers).perform();
            buttonUsers.click();
            getDriver().findElement(By.xpath("//a[@href='addUser']")).click();
            getDriver().findElement(By.id("username")).sendKeys("NewHero");
            getDriver().findElement(By.name("password1")).sendKeys("11123335");
            getDriver().findElement(By.name("password2")).sendKeys("11123335");
            getDriver().findElement(By.name("fullname")).sendKeys("Alexander Privalov");
            getDriver().findElement(By.name("email")).sendKeys("lex@gmail.com");
            getDriver().findElement(By.name("Submit")).click();
            WebElement result = getDriver().findElement(By.xpath("//a[@href='user/newhero/']"));
            Assert.assertEquals(result.getText(), "NewHero");
        }
    }


