package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class DeleteItemsNumber1Test extends CreateNewItemNumber2Test {
    @Test
    public void testItemDelete() {
        testItemCreation_Success();
        {
            getDriver().findElement(By.xpath("//span[normalize-space(text())='Delete Project']")).click();
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-id='ok']")));
            getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();
        }
    }
}

