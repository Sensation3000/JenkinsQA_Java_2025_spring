package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.awt.*;

public class AddDescriptionTest extends BaseTest {

    @Test
    public void testAddDescriptionButton() {
        WebDriver driver = getDriver();

        WebElement buttonElement = driver.findElement(By.cssSelector("#description-link"));
        buttonElement.click();

        WebElement inputText = driver.findElement(By.cssSelector("#description > form > div.jenkins-form-item.tr > div.setting-main.help-sibling > textarea"));
        String text = "new project";
        if (inputText.isEnabled()){
            inputText.sendKeys(text);
        } else
            Assert.assertFalse(true);

        WebElement buttonElementSave = driver.findElement(By.cssSelector("#description > form > div:nth-child(3) > button"));
        buttonElementSave.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#description > div:nth-child(1)")).getText(), text);
    }
}
