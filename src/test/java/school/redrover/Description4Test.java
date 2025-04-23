package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class Description4Test extends BaseTest {

    @Test
    public void createAndEditDescriptionTest() {
        WebDriver driver = getDriver();
        driver.findElement(By.id("description-link")).click();
        driver.findElement(By.name("description")).sendKeys("Text description");
        driver.findElement(By.name("Submit")).click();

        String description = driver.findElement(By.cssSelector("#description > div:nth-child(1)")).getText();
        if (description.equals("Text description")) {
            driver.findElement(By.cssSelector("#description-link")).click();
            WebElement descriptionField = driver.findElement(By.cssSelector("#description > form > div.jenkins-form-item.tr > div.setting-main.help-sibling > textarea"));
            descriptionField.clear();
            descriptionField.sendKeys("New description text");
            driver.findElement(By.name("Submit")).click();

            String newDescription = driver.findElement(By.cssSelector("#description > div:nth-child(1)")).getText();
            Assert.assertEquals(newDescription,"New description text");
        } else {
            System.out.println("Description is not displayed correctly");
        }
    }
}
