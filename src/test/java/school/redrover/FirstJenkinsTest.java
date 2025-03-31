package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

@Ignore
public class FirstJenkinsTest extends BaseTest {

    @Test
    public void testDescriptionField() throws InterruptedException {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input   ")).sendKeys("It's my first test in Jenkins");

        getDriver().findElement(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']")).click();
        String previewText = getDriver().findElement(By.cssSelector(".textarea-preview")).getText();

        getDriver().findElement(By.cssSelector(".textarea-hide-preview")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        Thread.sleep(200);

        String resultText = getDriver().findElement(By.xpath("//*[@class='jenkins-!-margin-bottom-0']/div[1]")).getText();

        Assert.assertEquals(previewText, "It's my first test in Jenkins");
        Assert.assertEquals(resultText, "It's my first test in Jenkins");
    }
}
