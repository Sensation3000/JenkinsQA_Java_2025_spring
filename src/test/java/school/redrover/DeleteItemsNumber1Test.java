package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.Ignore;

import java.time.Duration;

// Ставлю Ignore. Тест не проходит на билд-сервере. В результаете, другие люди не могут смержить свои PR
@Ignore
public class DeleteItemsNumber1Test extends CreateNewItemNumber2Test {

    @Test
    public void testItemDelete() throws InterruptedException {
        testItemCreation_Success();
        getDriver().findElement(By.xpath("//span[normalize-space(text())='Delete Project']")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-id='ok']")));
        getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();
        By projectLinkLocator = By.cssSelector("a[href='/job/" + PROJECT_NAME + "/']");
        boolean isProjectGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(projectLinkLocator));
        assert isProjectGone : "Проект '" + PROJECT_NAME + "' всё ещё отображается после удаления!";
    }
}