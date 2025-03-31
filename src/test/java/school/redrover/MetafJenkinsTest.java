package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class MetafJenkinsTest extends BaseTest {

    private static final String newItemTitle = "Test Pipeline";

    @Test
    void mainPageShouldDisplayWelcomeMessageOnLoginTest() {
        WebDriver driver = getDriver();
        WebElement titleText = driver.findElement(By.xpath("//div[@class='empty-state-block']/h1"));
        Assert.assertEquals(titleText.getText(), "Welcome to Jenkins!");
    }

    @Test
    void checkTasksMenuItemsTest() {
        WebDriver driver = getDriver();

        List<WebElement> menuItems =
                driver.findElements(By.xpath("//div[@id='tasks']//a[starts-with(@class, 'task-link')]"));

        List<String> menuTexts = new ArrayList<>();
        for(WebElement menuItem : menuItems) {
            menuTexts.add(menuItem.getText());
        }

        Assert.assertEquals(List.of("New Item", "Build History", "Manage Jenkins", "My Views"), menuTexts);
    }

    @Test
    void createNewFreestyleProjectTest() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']"))
                .click();
        driver.findElement(By.xpath("//input[@id='name']"))
                .sendKeys(newItemTitle);
        driver.findElement(By.xpath("(//ul[@class='j-item-options'])[1]/li[@class='hudson_model_FreeStyleProject']"))
                .click();
        driver.findElement(By.xpath("//button[@id='ok-button']"))
                .click();
        driver.findElement(By.xpath("//button[@name='Submit']"))
                .click();

        Assert.assertEquals(
                driver.findElement(By.xpath("//h1[@class='job-index-headline page-headline']"))
                        .getText(),
                newItemTitle
        );
    }
}