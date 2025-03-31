package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MultibranchPipelineTest extends BaseTest {

    @Test
    public void testAddDescriptionCreatingMultibranch() {
        final String expectedDescription = "Add description";

        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("MultiBranch");

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        getDriver().findElement(By.cssSelector("[class$='MultiBranchProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.cssSelector("[name$='description']")).sendKeys(expectedDescription);
        getDriver().findElement(By.name("Submit")).click();

        String actualDescription = getDriver().findElement(By.id("view-message")).getText();
        Assert.assertEquals(actualDescription, expectedDescription);
    }
    @Ignore
    @Test
    public void testTryCreateProjectExistName() throws InterruptedException {
        final String projectName = "MultiBuild";
        final String errorMessage = "» A job already exists with the name " + "‘" + projectName + "’";

        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        getDriver().findElement(By.cssSelector("[class$='MultiBranchProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        JavascriptExecutor js1 = (JavascriptExecutor) getDriver();
        js1.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        getDriver().findElement(By.name("Submit")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//*[@id='jenkins-name-icon']")).click();

        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        JavascriptExecutor js2 = (JavascriptExecutor) getDriver();
        js2.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        getDriver().findElement(By.cssSelector("[class$='MultiBranchProject']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);

        String actualMessage = getDriver().findElement(By.xpath("//*[@id='itemname-invalid']")).getText();
        Assert.assertEquals(actualMessage, errorMessage);
    }
}
