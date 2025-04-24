package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import static org.testng.Assert.assertTrue;

public class DescriptionButtonTest extends BaseTest {

    @Test
    public void testAddDescriptionButton() {
        WebDriver driver = getDriver();

        WebElement addDescriptionButton = driver.findElement(By.id("description-link"));
        addDescriptionButton.click();

        WebElement descriptionTextArea = driver.findElement(By.name("description"));
        assertTrue(descriptionTextArea.isDisplayed());
    }

    @Test
    public void testAddDescription() {
        HomePage homePage = new HomePage(getDriver());

        homePage.clickAddDescriptionButton();

        assertTrue(homePage.isDescriptionFieldDisplayed());
    }
}
