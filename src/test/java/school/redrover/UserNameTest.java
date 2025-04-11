package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import static org.testng.Assert.assertEquals;

public class UserNameTest extends BaseTest {

    @Test
    public void testUserName() {
        WebDriver driver = getDriver();

        WebElement userButton = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div[3]/a[1]"));
        userButton.click();

        WebElement fullName = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[1]/div[1]/h1"));
        String fullNameText = fullName.getText();

        WebElement userButtonText = driver.findElement(By.xpath("//*[@id=\"page-header\"]/div[3]/a[1]/span"));
        String userButtonTextStored = userButtonText.getText();

        Assert.assertEquals(userButtonTextStored, fullNameText);
    }
}