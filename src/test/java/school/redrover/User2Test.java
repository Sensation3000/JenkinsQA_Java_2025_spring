package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class User2Test extends BaseTest {

    @Test
    public void testUserItemOnManageJenkinsPage() {
        final String expectedPath = "/manage/securityRealm/";
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a[href='/manage']")).click();
        driver.findElement(By.cssSelector("a[href='securityRealm/']")).click();

        getWait5().until(ExpectedConditions.urlContains(expectedPath));
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedPath),
                "Current URL doesn't contain expected path: " + expectedPath);
    }

    @Test
    public void testCreateUserButton() {
        final String expectedTitle = "Create User [Jenkins]";
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a[href='/manage']")).click();
        driver.findElement(By.cssSelector("a[href='securityRealm/']")).click();
        driver.findElement(By.cssSelector("a[href='addUser']")).click();

        getWait5().until(ExpectedConditions.titleIs(expectedTitle));
        Assert.assertEquals(driver.getTitle(), expectedTitle,
                "Page title does not match expected, actual title: " + driver.getTitle());
        WebElement usernameField = driver.findElement(By.id("username"));
        Assert.assertTrue(usernameField.isDisplayed(),
                "Username field is not visible on the Create User page");
    }
}
