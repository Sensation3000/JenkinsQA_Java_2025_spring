package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class DescriptionTest extends BaseTest {

    @Test
    public void testAddDescription() {
        String textDescription = "Text description";
        getDriver().findElement(By.xpath("//a[text()='Add description']")).click();
        getDriver().findElement(By.name("description")).sendKeys(textDescription);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[text()='" + textDescription + "']")).isDisplayed());
    }

    @Test
    public void testRemoveDescription() {
        String textDescription = "Text description";
        getDriver().findElement(By.xpath("//a[text()='Add description']")).click();
        getDriver().findElement(By.name("description")).sendKeys(textDescription);
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.xpath("//a[text()='Edit description']")).click();
        getDriver().findElement(By.name("description")).clear();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//a[text()='Add description']")).isDisplayed());
    }

}
