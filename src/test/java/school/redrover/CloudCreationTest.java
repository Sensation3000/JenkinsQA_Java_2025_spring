package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CloudCreationTest extends BaseTest {

    final String projectName = "CloudCreationTestName";

    @Test
    public void testCreateNewCloud() {
        WebDriver driver = getDriver();
        goToClouds(driver);
        createNewCloud(driver);
        assertCloudCreated(driver);
    }

    @Test
    public void testDeleteCloud() {
        WebDriver driver = getDriver();
        goToClouds(driver);
        deleteCloud(driver);
        assertCloudDeleted(driver);
    }

    private void assertCloudDeleted(WebDriver driver) {
        driver.findElement(By.cssSelector(".jenkins-button--primary")).click();
        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Clouds"));
        List<WebElement> listItems = driver.findElements(By.cssSelector(".empty-state-section-list li"));

        List<String> actualTexts = listItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> expectedTexts = Arrays.asList("New cloud", "Install a plugin", "Learn more about distributed builds");

        Assert.assertEquals(actualTexts, expectedTexts);
    }

    private void deleteCloud(WebDriver driver) {
        driver.findElement(By.cssSelector(".jenkins-table__link")).click();
        Assert.assertEquals(
                driver.findElement(By.tagName("h1")).getText(),
                "Cloud " + projectName);
        List<WebElement> buttons2 = driver.findElements(By.cssSelector(".task"));
        for (WebElement button : buttons2) {
            if (button.getText().equals("Delete Cloud")) {
                button.click();
                break;
            }
        }
    }

    private void goToClouds(WebDriver driver) {
        List<WebElement> buttons = driver.findElements(By.cssSelector(".task"));
        for (WebElement button : buttons) {
            if (button.getText().equals("Manage Jenkins")) {
                button.click();
                break;
            }
        }
        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Manage Jenkins"));
        List<WebElement> buttons1 = driver.findElements(By.cssSelector(".jenkins-section__item dt"));
        for (WebElement button : buttons1) {
            if (button.getText().equals("Clouds")) {
                button.click();
                break;
            }
        }
        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Clouds"));
    }

    private void createNewCloud(WebDriver driver) {
        driver.findElement(By.cssSelector("li:nth-child(1) .trailing-icon")).click();
        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "New cloud"));
        driver.findElement(By.id("name")).sendKeys(projectName);
        driver.findElement(By.cssSelector("label[for=\"com.microsoft.azure.vmagent.AzureVMCloud\"]")).click();
        driver.findElement(By.id("ok")).click();
        this.getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@value=\"CloudCreationTestName\"]")));
        driver.findElement(By.cssSelector(".jenkins-submit-button")).click();
    }

    private void assertCloudCreated(WebDriver driver) {
        this.getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href=\"/manage/cloud/CloudCreationTestName/\"]")));
        Assert.assertEquals(
                driver.findElement(By.xpath("//a[@href=\"/manage/cloud/CloudCreationTestName/\"]")).getText(),
                projectName);

        driver.findElement(By.cssSelector(".jenkins-table__link")).click();
        Assert.assertEquals(
                driver.findElement(By.tagName("h1")).getText(),
                "Cloud " + projectName);
    }
}
