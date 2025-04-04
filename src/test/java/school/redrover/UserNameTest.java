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

    public static class FolderTest extends BaseTest {

        @Test
        public void testCreateFolder () {
            final String folderName = "ProjectFolder";

            WebDriver driver = getDriver();

            driver.findElement(By.linkText("New Item")).click();
            driver.findElement(By.id("name")).sendKeys(folderName);
            driver.findElement(By.xpath("//span[text() = 'Folder']")).click();
            driver.findElement(By.id("ok-button")).click();
            driver.findElement(By.name("Submit")).click();

            assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), folderName);

            TestUtils.gotoHomePage(this);

            Assert.assertEquals(
                    driver.findElement(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).getText(),
                    folderName);
        }
    }
}