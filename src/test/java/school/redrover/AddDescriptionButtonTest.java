package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import static org.testng.Assert.assertTrue;

public class AddDescriptionButtonTest extends BaseTest {

    @Test
    public void testAddDescriptionButton() {
        WebDriver driver = getDriver();

        WebElement addDescriptionButton = driver.findElement(By.id("description-link"));
        addDescriptionButton.click();

        WebElement descriptionTextArea = driver.findElement(By.name("description"));
        assertTrue(descriptionTextArea.isDisplayed());
    }
}
