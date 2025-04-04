package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static void gotoHomePage(BaseTest baseTest) {
        baseTest.getWait10()
                .until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link")))
                .click();
    }

    public static WebElement waitForHomePageLoad(BaseTest baseTest) {
        return baseTest
                .getWait5()
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/logout']")));
    }

    public static String generateRandomAlphanumeric() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void moveAndClickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('click'));", element);
    }

    public static void scrollAndClickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Creates a new item in Jenkins with specified name and type.
     *
     * @param baseTest   current test instance (this)
     * @param itemName   name for the new item (must be unique)
     * @param itemTypeId numeric identifier for item type (1-6)
     *                   <p>1 = Freestyle project</p>
     *                   <p>2 = Pipeline</p>
     *                   <p>3 = Multi-configuration project</p>
     *                   <p>4 = Folder</p>
     *                   <p>5 = Multibranch Pipeline</p>
     *                   <p>6 = Organization Folder</p>
     * @throws IllegalArgumentException if:
     *                                  <p>itemTypeId is not between 1-6<p/>
     *                                  <p>itemName is empty or already exists<p/>
     * @example newItemCreate(this, " MyPipeline ", 2); // Creates a Pipeline
     */
    public static void newItemCreate(BaseTest baseTest, String itemName, int itemTypeId) {
        String itemTypeName = getItemTypeName(itemTypeId);

        if (itemName.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty or whitespace");
        }

        TestUtils.gotoHomePage(baseTest);

        uniqueItemNameCheck(baseTest.getDriver(), itemName);

        baseTest.getWait5().until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='New Item']/preceding-sibling::span")))
                .click();
        baseTest.getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input")))
                .sendKeys(itemName);

        WebElement itemBox = baseTest.getWait5().until(ExpectedConditions.elementToBeClickable(
                (By.xpath("//span[contains(text(), '" + itemTypeName + "')]"))));
        scrollAndClickWithJS(baseTest.getDriver(), itemBox);

        WebElement okButton = baseTest.getWait5().until(ExpectedConditions.elementToBeClickable
                (By.id("ok-button")));
        scrollAndClickWithJS(baseTest.getDriver(), okButton);
        TestUtils.gotoHomePage(baseTest);
    }

    private static String getItemTypeName(int typeId) {
        return switch (typeId) {
            case 1 -> "Freestyle project";
            case 2 -> "Pipeline";
            case 3 -> "Multi-configuration project";
            case 4 -> "Folder";
            case 5 -> "Multibranch Pipeline";
            case 6 -> "Organization Folder";
            default -> throw new IllegalArgumentException("Invalid item type: " + typeId);
        };
    }

    public static void uniqueItemNameCheck(WebDriver driver, String itemName) {
        if (!driver.findElements(By.xpath("//td/a/span")).isEmpty()) {
            List<WebElement> existingNameOfItems = driver.findElements
                    (By.xpath("//td/a/span"));
            List<String> itemsNames = new ArrayList<>();
            for (WebElement element : existingNameOfItems) {
                itemsNames.add(element.getText());
            }
            for (String str : itemsNames) {
                if (str.equals(itemName)) {
                    throw new IllegalArgumentException("Name '" + itemName + "' already exists");
                }
            }
        }
    }

}
