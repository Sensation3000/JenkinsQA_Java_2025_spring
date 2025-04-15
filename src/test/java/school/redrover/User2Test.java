package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

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

    @Test
    public void testCreateUserForm() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a[href='/manage']")).click();
        driver.findElement(By.cssSelector("a[href='securityRealm/']")).click();
        driver.findElement(By.cssSelector("a[href='addUser']")).click();

        WebElement createUserButton = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
        Assert.assertTrue(createUserButton.isDisplayed(), "'Create User' button is not visible.");
        List<WebElement> inputFields = getDriver().findElements(By.cssSelector(".jenkins-input"));
        Assert.assertEquals(inputFields.size(), 5, "Expected 5 input fields in the Create User form");
    }
}
