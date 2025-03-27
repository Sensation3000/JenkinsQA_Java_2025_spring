package school.redrover;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        }

        WebElement buttonElementSave = driver.findElement(By.cssSelector("#description > form > div:nth-child(3) > button"));
        buttonElementSave.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#description > div:nth-child(1)")).getText(), text);
    }

    @Test
    public void testAddDescription () throws InterruptedException {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("FirstJob");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("New description");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        assertEquals(getDriver().findElement(By.xpath(
                        "//div[@id='description']/div")).getText(),
                "New description"
        );
    }
}

