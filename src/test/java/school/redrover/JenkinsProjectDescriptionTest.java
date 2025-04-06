package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class JenkinsProjectDescriptionTest extends BaseTest {

    public void preliminaryProjectCreation() {
        getDriver().findElement(By.xpath("//a[@href ='newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("YourPipelineName");
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        // Использование getWait5() из BaseTest для ожидания элемента
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("description")));
    }

    @Test
    public void UpdateProjectDescription() {
        final String DescriptionName = "Description";

        preliminaryProjectCreation();

        // Обновление описания проекта
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .cssSelector("#tasks > div:nth-child(6) > span > a"))).click();
        getDriver().findElement(By.className("jenkins-input")).sendKeys(DescriptionName);
        getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]")).click();

        // Проверка, что описание обновилось
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By
                .xpath("//*[@id=\"description\"]"),DescriptionName));
        Assert.assertTrue(getDriver().findElement(By.xpath("//*[@id=\"description\"]"))
                        .getText().contains(DescriptionName),
                "Description does not contain expected text!");
    }
}


