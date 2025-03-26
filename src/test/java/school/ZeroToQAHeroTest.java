package school;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;



public class ZeroToQAHeroTest extends BaseTest {
    @Test
    public void testAddDescription() {

       WebElement addDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));
       addDescription.click();

       WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name='description']"));
                      textArea.sendKeys("Hello");

       getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        WebElement actualTextElement = getDriver().findElement(By
                .xpath("//*[@id='description']/div[1]"));
        String actualText = actualTextElement.getText();

        Assert.assertEquals(actualText, "Hello");

    }
}
