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
        driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals( driver.findElement(By.xpath("//h1")).getText(), "Ирина Соколова");
    }
}
