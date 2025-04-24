package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;


public class Description3Test extends BaseTest {

    String newDescription = "Test";
    public WebDriverWait wait;

    @Ignore
    @Test
    public void SaveDescriptionWithoutChanges(){
        WebDriver driver = getDriver();

        // Prepare

        //Open Description
        driver.findElement(By.xpath("//*[@id=\"description-link\"]")).click();
        driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(newDescription);
        driver.findElement(By.xpath("//*[@id=\"description\"]/form/div[2]/button")).click();

        TestUtils.gotoHomePage(this);

        //Second Open Description
        driver.findElement(By.xpath("//*[@id=\"description-link\"]")).click();

        // Act

        driver.findElement(By.xpath("//*[@id=\"description\"]/form/div[2]/button")).click();

        // Assert

        WebElement descriptionSaveWithoutChangeElement = driver.findElement(By.cssSelector("#description > div:nth-child(1)"));
        Assert.assertEquals(descriptionSaveWithoutChangeElement.getText(), newDescription);
    }
}
