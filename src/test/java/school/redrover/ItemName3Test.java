package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ItemName3Test extends BaseTest {
    @Test
    public void testEmptyName() {

        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();

        WebElement validationMessage = getDriver().findElement(By.id("itemname-required"));

        Assert.assertEquals(validationMessage.getText(), "» This field cannot be empty, please enter a valid name");

    }

}


