package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FirstJenkinsTest extends BaseTest {

    @Test
    public void testDescriptionField() {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input   ")).sendKeys("It's my first test in Jenkins");

        getDriver().findElement(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']")).click();
        String previewText = getDriver().findElement(By.cssSelector(".textarea-preview")).getText();

        getDriver().findElement(By.cssSelector(".textarea-hide-preview")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        WebElement resultText = getDriver().findElement(By.xpath("//div[contains(text(), \"It's my first test in Jenkins\")]"));
        String actualText = resultText.getText();

        Assert.assertEquals(previewText, "It's my first test in Jenkins");
        Assert.assertEquals(actualText, "It's my first test in Jenkins");
    }
}
