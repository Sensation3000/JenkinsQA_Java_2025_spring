package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class GroupClubRedroverTest {
    WebDriver driver;
    SoftAssert softAssert;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeMethod
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "pageData")
    public Object[][] providePageData() throws InterruptedException {
        return new Object[][]{
                {"Chapter 3. WebDriver Fundamentals", "web-form.html", "Web form"},
                {"Chapter 3. WebDriver Fundamentals", "navigation1.html", "Navigation example"},
                {"Chapter 3. WebDriver Fundamentals", "dropdown-menu.html", "Dropdown menu"},
                {"Chapter 3. WebDriver Fundamentals", "mouse-over.html", "Mouse over"},
                {"Chapter 3. WebDriver Fundamentals", "drag-and-drop.html", "Drag and drop"},
                {"Chapter 3. WebDriver Fundamentals", "loading-images.html", "Loading images"},
                {"Chapter 3. WebDriver Fundamentals", "slow-calculator.html", "Slow calculator"},
                {"Chapter 4. Browser-Agnostic Features", "long-page.html", "This is a long page"},
                {"Chapter 4. Browser-Agnostic Features", "infinite-scroll.html", "Infinite scroll"},
                {"Chapter 4. Browser-Agnostic Features", "shadow-dom.html", "Shadow DOM"},
                {"Chapter 4. Browser-Agnostic Features", "iframes.html", "IFrame"},
                {"Chapter 4. Browser-Agnostic Features", "cookies.html", "Cookies"},
                {"Chapter 4. Browser-Agnostic Features", "dialog-boxes.html", "Dialog boxes"},
                {"Chapter 4. Browser-Agnostic Features", "web-storage.html", "Web storage"},
                {"Chapter 5. Browser-Specific Manipulation", "geolocation.html", "Geolocation"},
                {"Chapter 5. Browser-Specific Manipulation", "notifications.html", "Notifications"},
                {"Chapter 5. Browser-Specific Manipulation", "get-user-media.html", "Get user media"},
                {"Chapter 5. Browser-Specific Manipulation", "multilanguage.html", "Multilanguage page"},
                {"Chapter 5. Browser-Specific Manipulation", "console-logs.html", "Console logs"},
                {"Chapter 7. The Page Object Model (POM)", "login-form.html", "Login form"},
                {"Chapter 7. The Page Object Model (POM)", "login-slow.html", "Slow login form"},
                {"Chapter 8. Testing Framework Specifics", "random-calculator.html", "Random calculator"},
                {"Chapter 9. Third-Party Integrations", "download.html", "Download files"},
                {"Chapter 9. Third-Party Integrations", "ab-testing.html", "A/B Testing"},
                {"Chapter 9. Third-Party Integrations", "data-types.html", "Data types"}
        };
    }

    @Test(dataProvider = "pageData", description = "Verify the functionality of all links on HomePage")
    void verifyHomePageLinks(String chapterName, String path, String title) throws InterruptedException {
        driver.findElement(By.xpath("//h5[text() = '" + chapterName + "']/../a[@href = '" + path + "']")).click();

        String actualUrl = driver.getCurrentUrl();
        String actualTitle = driver.findElement(By.className("display-6")).getText();
        assertEquals(BASE_URL + path, actualUrl, "The URLs don't match");
        assertEquals(title, actualTitle, "The titles don't match");
    }

    @Test(description = "Verify the functionality of all links on HomePage another way")
    void verifyHomePageLinksAnotherWay() throws InterruptedException {
        List<WebElement> chapters = driver.findElements(By.cssSelector(".card h5"));
        assertEquals(chapters.size(), 6);

        List<WebElement> links = driver.findElements(By.cssSelector(".card a"));
        for (WebElement link : links) {
            link.click();
            driver.navigate().back();
        }
        assertEquals(links.size(), 27);
    }

    @Test(description = "Verify that all selects in the form function correctly and allow valid user interactions")
    void verifySelects() throws InterruptedException {
        softAssert = new SoftAssert();
        driver.findElement(By.xpath("//a[@href = 'web-form.html']")).click();

        Select select = new Select(driver.findElement(By.name("my-select")));

        List<WebElement> options = select.getOptions();
        softAssert.assertEquals(options.size(), 4, "Dropdown should contain exactly 4 options.");

        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        softAssert.assertEquals(selectedOptions.size(), 1,
                "Only one option should be selected by default.");

        softAssert.assertEquals(select.getFirstSelectedOption().getText(),
                "Open this select menu", "Default option text mismatch.");

        select.selectByValue("1");
        softAssert.assertEquals(select.getFirstSelectedOption().getText(),
                "One", "Option 'One' was not selected correctly.");

        select.selectByIndex(2);
        softAssert.assertEquals(select.getFirstSelectedOption().getText(),
                "Two", "Option 'Two' was not selected correctly.");

        select.selectByValue("3");
        softAssert.assertEquals(select.getFirstSelectedOption().getText(),
                "Three", "Option 'Three' was not selected correctly.");

        select.selectByIndex(0);
        softAssert.assertEquals(select.getFirstSelectedOption().getText(),
                "Open this select menu", "Dropdown did not reset correctly.");

        softAssert.assertAll();
    }

    @Test(description = "Verify mouse moving over pictures")
    void verifyMouseMovingOverPictures() throws InterruptedException {
        softAssert = new SoftAssert();
        driver.findElement(By.xpath("//a[@href = 'mouse-over.html']")).click();

        String[] expectedText = {"Compass", "Calendar", "Award", "Landscape"};
        List<WebElement> hoverElements = driver.findElements(By.cssSelector(".figure.text-center.col-3.py-2"));

        Actions actions = new Actions(driver);
        for (int i = 0; i < hoverElements.size(); i++) {

            WebElement element = hoverElements.get(i);
            actions.moveToElement(element.findElement(By.cssSelector(".img-fluid"))).perform();
            WebElement textElement = element.findElement(By.cssSelector(".lead.py-3"));

            softAssert.assertTrue(textElement.isDisplayed(), "The text did not appear after hover.");
            softAssert.assertEquals(textElement.getText(), expectedText[i], "Text mismatch for element at index " + i);
        }
        softAssert.assertAll();
    }

    @Test
    void verifyTextOnEachPAge() throws InterruptedException {
        softAssert = new SoftAssert();
        driver.findElement(By.xpath("//a[@href = 'navigation1.html']")).click();

        List<WebElement> pages = driver.findElements(By.cssSelector(".pagination .page-link"));
        softAssert.assertEquals(pages.size(), 5, "Expected 5 pages, the Test's got " + pages.size());

        String actualText = driver.findElement(By.xpath("//p[contains(text(), 'Lorem ipsum dolor sit amet')]")).getText();
        softAssert.assertTrue(actualText.contains("Lorem ipsum dolor sit amet"),
                "Expected text not found!");

        driver.findElement(By.cssSelector("a[href*='navigation2']")).click();
        actualText = driver.findElement(By.xpath("//p[contains(text(), 'laboris nisi ut aliquip ex ea')]")).getText();
        softAssert.assertTrue(actualText.contains("laboris nisi ut aliquip ex ea"));

        driver.findElement(By.cssSelector("a[href*='navigation3']")).click();
        actualText = driver.findElement(By.xpath("//p[contains(text(), 'officia deserunt mollit anim')]")).getText();
        softAssert.assertTrue(actualText.contains("officia deserunt mollit anim"));

        softAssert.assertAll();
    }

    @Test
    void verifyDropDown() throws InterruptedException {
        Actions actions = new Actions(driver);
        softAssert = new SoftAssert();

        driver.findElement(By.cssSelector("a[href *= 'dropdown']")).click();

        driver.findElement(By.cssSelector("#my-dropdown-1")).click();
        softAssert.assertTrue(driver.findElement(By.cssSelector(".dropdown-menu.show")).isDisplayed());

        actions.contextClick(driver.findElement(By.cssSelector("#my-dropdown-2"))).perform();
        softAssert.assertTrue(driver.findElement(By.cssSelector("#context-menu-2")).isDisplayed());

        actions.doubleClick(driver.findElement(By.cssSelector("#my-dropdown-3"))).perform();
        softAssert.assertTrue(driver.findElement(By.cssSelector("#context-menu-3")).isDisplayed());
    }

    @Test
    void verifyDragAndDrop() throws InterruptedException {
        Actions actions = new Actions(driver);
        softAssert = new SoftAssert();

        driver.findElement(By.cssSelector("a[href *= 'drag-and-drop']")).click();

        actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("target"))).perform();
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
        actions.clickAndHold(driver.findElement(By.id("draggable"))).moveToElement(driver.findElement(By.id("target"))).release().build().perform();
    }
}
