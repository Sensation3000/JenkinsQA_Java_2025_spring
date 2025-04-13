package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MultiConfigurationProject1Test extends BaseTest {
    private void createDisabledProject(String projectName) {
        getDriver().findElement(By.linkText("Create a job")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("general")));
        getDriver().findElement(By.cssSelector("#enable-disable-project~label")).click();
        getDriver().findElement(By.name("Submit")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("no-builds")));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins-home-link"))).click();
    }

    @Test
    public void testDisabledMessage(){
        final String projectName = "TestProject1";
        final String expectedMessageText = "This project is currently disabled";

        createDisabledProject(projectName);
        getDriver().findElement(By.linkText(projectName)).click();

        final String actualMessageText = getDriver().findElement(By.cssSelector("#enable-project:not(button)")).getText();

        Assert.assertTrue(actualMessageText.contains(expectedMessageText), "DisabledMessageText does not contains 'This project is currently disabled'");
    }
}