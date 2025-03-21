package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GroupAutoamigosTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testWebFormLink() {
        WebElement link = driver.findElement(By.linkText("Web form"));
        link.click();
        Assert.assertNotEquals(driver.getTitle(), "Страница не загрузилась: Web form");
    }

    @Test
    public void testNavigationLink() {
        WebElement link = driver.findElement(By.linkText("Navigation"));
        link.click();
        Assert.assertNotEquals(driver.getTitle(), "Страница не загрузилась: Navigation");
    }

    @Test
    public void testDropdownMenuLink() {
        WebElement link = driver.findElement(By.linkText("Dropdown menu"));
        link.click();
        Assert.assertNotEquals(driver.getTitle(), "Страница не загрузилась: Dropdown menu");
    }

    @Test
    public void testMouseOverLink() {
        WebElement link = driver.findElement(By.linkText("Mouse over"));
        link.click();
        Assert.assertNotEquals(driver.getTitle(), "Страница не загрузилась: Mouse over");
    }

    @Test
    public void testDragAndDropLink() {
        WebElement link = driver.findElement(By.linkText("Drag and drop"));
        link.click();
        Assert.assertNotEquals(driver.getTitle(), "Страница не загрузилась: Drag and drop");
    }

    @Test
    public void testDrawInCanvasLink() {
        WebElement link = driver.findElement(By.linkText("Draw in canvas"));
        link.click();
        Assert.assertNotEquals(driver.getTitle(), "Страница не загрузилась: Draw in canvas");
    }

    @Test
    public void testLoadingImagesLink() {
        WebElement link = driver.findElement(By.linkText("Loading images"));
        link.click();
        Assert.assertNotEquals(driver.getTitle(), "Страница не загрузилась: Loading images");
    }

    @Test
    public void testSlowCalculatorLink() {
        WebElement link = driver.findElement(By.linkText("Slow calculator"));
        link.click();
        Assert.assertNotEquals(driver.getTitle(), "Страница не загрузилась: Slow calculator");
    }
}