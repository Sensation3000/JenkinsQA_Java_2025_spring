package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewJob2Test extends BaseTest {

    @Test
    public void testNewJob() {
        getDriver().findElement(By.className("content-block__link")).click();
        getDriver().findElement(By.className("label")).click();
        String message = "» This field cannot be empty, please enter a valid name";
        Assert.assertEquals(getDriver().findElement(By.id("itemname-required")).getText(), message);
    }

}
