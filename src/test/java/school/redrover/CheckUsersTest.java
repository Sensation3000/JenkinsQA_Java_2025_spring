package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class CheckUsersTest extends BaseTest {

    @Test
    public void testCheckUser() {
        WebDriver driver = getDriver();
        WebElement manage = driver.findElement(By.xpath("(//span[@class='task-link-wrapper '])[3]"));
        manage.click();
        String manageJenkins =  driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(manageJenkins, "Manage Jenkins");
        WebElement users = driver.findElement(By.xpath("//dt[text()='Users']"));
        users.click();
        WebElement admin = driver.findElement(By.xpath("//td/a[text()='admin']"));
        String adminJenkins = admin.getText();
        Assert.assertEquals(adminJenkins, "admin");

        WebElement adminLink = driver.findElement(
                By.xpath("//a[@class='jenkins-table__link model-link inside' and contains(., 'admin')]")
        );

// Смещаем курсор влево от центра и кликаем
        new Actions(driver)
                .moveToElement(adminLink)
                .moveByOffset(-10, 0)  // Смещение влево (подбирайте значение)
                .click()
                .perform();
        Assert.assertEquals( driver.findElement(By.xpath("//div[contains(text(), 'Jenkins User ID: admin')]")).getText(), "Jenkins User ID: admin");
    }

    @Test
    public void testCreateUser() {
        WebDriver driver = getDriver();

        final String userName = "TestName";
        final String password = "123456";
        final String confirmPassword = "123456";
        final String fullName = "TestFullName";
        final String email = "1@tut.by";

        driver.findElement(By.cssSelector("a.task-link[href='/manage']")).click();
        driver.findElement(By.xpath("//dt[text()='Users']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-panel a[href*='addUser']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#username"))).sendKeys(userName);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='password1']"))).sendKeys(password);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='password2']"))).sendKeys(confirmPassword);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='fullname']"))).sendKeys(fullName);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']"))).sendKeys(email);

        driver.findElement(By.cssSelector("button.jenkins-submit-button")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("a[href*='user/testname']")).getText(),
                userName);
        Assert.assertEquals(getDriver().findElement(By.xpath("//td[text()='TestFullName']")).getText(),
                fullName);
    }
}
