package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import static org.testng.Assert.assertEquals;

public class CreateNewItemTest extends BaseTest {

    @Test
    public void testCreateNewItem() throws InterruptedException {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Test item");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//input[@id='cb6']/following-sibling::label")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//a[@href='/']")).click();

        assertEquals(getDriver().findElement(By.xpath(
                "//a[@href='job/Test%20item/']")).getText(),
                "Test item"
        );
    }
}
