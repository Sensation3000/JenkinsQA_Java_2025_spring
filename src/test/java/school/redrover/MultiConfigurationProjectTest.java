package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MultiConfigurationProjectTest extends BaseTest {
    @Test
    public void testCreateProjectWithoutName() {
        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        getDriver().findElement(By.className("hudson_matrix_MatrixProject")).click();

        String actualErrorMessage = getDriver().findElement(By.id("itemname-required")).getText();

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement okButton = getDriver().findElement(By.id("ok-button"));

        Assert.assertTrue(actualErrorMessage.contains("This field cannot be empty"));
        Assert.assertFalse(okButton.isEnabled());
    }
}
