package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class UsernameFieldTest extends BaseTest {

    public void setUp() throws Exception {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href=\"/manage\"]")).click();
        driver.findElement(By.xpath("//a[@href=\"securityRealm/\"]")).click();
        driver.findElement(By.xpath("//a[@href=\"addUser\"]")).click();
    }

    @Test
    //@Description("The ability to click on the field.")
    public void testUsernameFieldFocusAndEditable() throws Exception {
        WebDriver driver = getDriver();
        setUp();
        WebElement usernameField = driver.findElement(By.id("username"));

        assertTrue(usernameField.isEnabled());
        usernameField.click(); // The ability to click on the field.

    }


    @Test
    //@Description("If invalid values are entered")
    public void testUsernameFieldAcceptsOnlyValidChars() throws Exception {
        WebDriver driver = getDriver();
        setUp();
        WebElement usernameField = driver.findElement(By.id("username"));

        // invalid values are entered
        usernameField.sendKeys("invalid user@name");
        WebElement submitButton = driver.findElement(By.name("Submit"));
        submitButton.click();

        //WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/div[2]"));
        WebElement errorMessage = driver.findElement(By.xpath("/html/body/div[3]/div/form/div[1]/div[2]"));
        assertEquals(errorMessage.getText(),
                "User name must only contain alphanumeric characters, underscore and dash");
    }

    @Test
    //@Description("If the field is empty and the Create button is pressed")
    public void testEmptyUsernameValidation() throws Exception {
        WebDriver driver = getDriver();
        setUp();
        WebElement submitButton = driver.findElement(By.name("Submit"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(
                By.xpath("/html/body/div[3]/div/form/div[1]/div[2]")
        );
        assertEquals(errorMessage.getText(),
                "\"\" is prohibited as a username for security reasons.");
    }

}
