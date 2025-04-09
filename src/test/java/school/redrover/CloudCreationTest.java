package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    final String pluginName = "Windows cloud";

    @Test
    public void testCreateNewCloud() {
        WebDriver driver = getDriver();
        goToClouds(driver);
        installCloudPlugin(driver);
        goToClouds(driver);
        createNewCloud(driver);
        assertCloudCreated(driver);
        deleteCloud(driver);
        assertCloudDeleted(driver);
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

    private void installCloudPlugin(WebDriver driver) {
        List<WebElement> buttons1 = driver.findElements(By.cssSelector(".empty-state-section-list li a"));
        for (WebElement button : buttons1) {
            if (button.getText().equals("Install a plugin")) {
                button.click();
                break;
            }
        }

        this.getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-search--app-bar input")));
        WebElement searchInput = driver.findElement(By.cssSelector(".jenkins-search--app-bar input"));
        searchInput.clear();
        searchInput.sendKeys(pluginName);
        searchInput.sendKeys(Keys.ENTER);
        driver.navigate().refresh();

        boolean pluginVisible = false;
        int retryCount = 0;
        while (!pluginVisible && retryCount < 10) {
            try {
                WebElement pluginLink = this.getWait10().until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//a[@href='https://plugins.jenkins.io/windows-cloud']")
                        )
                );
                pluginVisible = pluginLink != null && pluginLink.isDisplayed();
            } catch (Exception e) {
                Assert.fail("Waiting for plugin to appear... Retry #" + retryCount);
            }
            retryCount++;
        }

        if (!pluginVisible) {
            Assert.fail("❌ Plugin '" + pluginName + "' did not appear in time.");
        }

        List<WebElement> pluginRows = driver.findElements(By.cssSelector("tbody tr"));
        for (WebElement row : pluginRows) {
            WebElement tableLink = row.findElement(By.cssSelector(".jenkins-table__link"));
            if (tableLink.getText().contains(pluginName)) {
                WebElement checkbox = row.findElement(By.cssSelector(".jenkins-checkbox label"));
                if (!checkbox.isSelected()) {
                    this.getWait10().until(ExpectedConditions.elementToBeClickable(checkbox));
                    checkbox.click();
                    break;
                }
            }
        }

        driver.findElement(By.id("button-install")).click();
        driver.navigate().refresh();
        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("#main-panel > h1"), "Download progress"
        ));
        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("[id^='status']"), "Success"
        ));

        List<WebElement> buttons = driver.findElements(By.cssSelector(".jenkins-breadcrumbs__list-item a"));
        for (WebElement button : buttons) {
            if (button.getText().equals("Dashboard")) {
                button.click();
                break;
            }
        }
        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Welcome to Jenkins!"));
    }

    private void createNewCloud(WebDriver driver) {
        driver.navigate().refresh();
        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".empty-state-section p"),
                "There are no clouds currently set up. Create one, or install a plugin for more cloud options."));
        List<WebElement> button3 = driver.findElements(By.cssSelector(".empty-state-section-list li a"));
        for (WebElement button : button3) {
            if (button.getText().equals("New cloud")) {
                this.getWait5().until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                break;
            }
        }

        this.getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "New cloud"));
        driver.findElement(By.id("name")).sendKeys(projectName);
        driver.findElement(By.cssSelector(".jenkins-radio__label")).click();
        driver.findElement(By.id("ok")).click();

        this.getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@value='" + projectName + "']")));
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

    private void deleteCloud(WebDriver driver) {
        List<WebElement> buttons2 = driver.findElements(By.cssSelector(".task"));
        for (WebElement button : buttons2) {
            if (button.getText().equals("Delete Cloud")) {
                button.click();
                break;
            }
        }
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
}