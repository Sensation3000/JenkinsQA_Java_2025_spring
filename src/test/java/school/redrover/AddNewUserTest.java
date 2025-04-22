package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.Map;

public class AddNewUserTest extends BaseTest {
        private final Map<String, String> userData = Map.of(
                "username", "testusername",
                "password", "Tpp88CIjEkih",
                "fullname", "Test User",
                "email", "test@test.com"
        );

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

        @Ignore
        @Test
        public void testCreateExistingUser() {
            WebDriver driver = getDriver();

            //create first user
            getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@href='/manage']"))).click();
            driver.findElement(By.xpath("//a[@href='securityRealm/']")).click();
            driver.findElement(By.className("jenkins-button--primary")).click();
            fillForm(userData);
            System.out.println("firs user created");

            //create second user
            driver.findElement(By.xpath("//a[@href='addUser']")).click();
            fillForm(userData);
            String errortext = driver.findElement(By.xpath(
                    "//*[@id=\"main-panel\"]/form/div[1]/div[2]")).getText();

            Assert.assertEquals(errortext, "User name is already taken");
        }

        public void fillForm(Map<String, String> userData) {
            WebDriver driver = getDriver();
            driver.findElement(By.id("username")).sendKeys(userData.get("username"));
            driver.findElement(By.name("password1")).sendKeys(userData.get("password"));
            driver.findElement(By.name("password2")).sendKeys(userData.get("password"));
            driver.findElement(By.name("fullname")).sendKeys(userData.get("fullname"));
            driver.findElement(By.name("email")).sendKeys(userData.get("email"));
            driver.findElement(By.name("Submit")).click();
        }

    }
