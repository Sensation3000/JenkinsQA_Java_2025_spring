package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class GroupAutoamigosTest {
    private WebDriver driver;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        js = (JavascriptExecutor) driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "inputData")
    public Object[][] provideInputData() {
        return new Object[][]{
                {"A"},
                {" "},
                {"a 1"},
                {"12345"},
                {" h L 1 !"},
                {"String!#$%^&&*"}
        };
    }

    @DataProvider(name = "dropdownOptions")
    public Object[][] provideDropdownOptions() {
        return new Object[][]{
                {"1", "One"},
                {"2", "Two"},
                {"3", "Three"}
        };
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

    @Test
    public void testHeader() {
        WebElement title = driver.findElement(By.xpath("//h1[@class='display-4']"));

        Assert.assertEquals(title.getText(), "Hands-On Selenium WebDriver with Java");
    }

    @Test
    public void testNavigationPageLink() {
        WebElement navigationButton = driver.findElement(By.xpath("//a[@href='navigation1.html']"));
        navigationButton.click();

        WebElement backToIndexLink = driver.findElement(By.xpath("//a[@href='index.html']"));
        backToIndexLink.click();
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, "https://bonigarcia.dev/selenium-webdriver-java/index.html");
    }

    @Test(dataProvider = "inputData")
    public void testSuccessInput(String inputData) {
        driver.findElement(By.linkText("Web form")).click();
        WebElement textInput = driver.findElement(By.name("my-text"));
        textInput.sendKeys(inputData);

        String realValue = (String) js.executeScript("return arguments[0].value;", textInput);

        Assert.assertEquals(realValue, inputData, "the data is not displayed");
    }

    @Test
    public void testCheckPasswordIsHidden() {
        driver.findElement(By.linkText("Web form")).click();
        WebElement passwordInput = driver.findElement(By.name("my-password"));
        passwordInput.sendKeys("12345");

        String realValue = (String) js.executeScript("return arguments[0].value;", passwordInput);
        String inputType = passwordInput.getDomAttribute("type");

        Assert.assertEquals(realValue, "12345", "The password is not displayed");
        Assert.assertEquals(inputType, "password", "Input type is wrong");
    }

    @Test
    public void testDisabledInput() {
        driver.findElement(By.linkText("Web form")).click();
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));

        Assert.assertFalse(disabledInput.isEnabled(), "The input is not disabled");
    }

    @Test
    public void testDropDown() {
        driver.findElement(By.linkText("Web form")).click();
        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);

        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Open this select menu");
        Assert.assertEquals(select.getOptions().size(), 4);
    }

    @Test(dataProvider = "dropdownOptions")
    public void testSelectFromDropdown(String item, String itemValue) {
        driver.findElement(By.linkText("Web form")).click();
        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);
        select.selectByValue(item);

        Assert.assertEquals(select.getFirstSelectedOption().getText(), itemValue, item + " wasn't selected");
    }

    @Test
    public void testDataList() {
        driver.findElement(By.linkText("Web form")).click();
        String placeholder = driver.findElement(By.name("my-datalist")).getDomAttribute("placeholder");
        WebElement dataList = driver.findElement(By.name("my-options"));
        Select dl = new Select(dataList);

        Assert.assertEquals(placeholder, "Type to search...");
        Assert.assertEquals(dl.getOptions().size(), 5);
    }

    @Test
    public void testFileInput() {
        driver.findElement(By.linkText("Web form")).click();
        File uploadFile = new File("src/test/resources/uploadFiles/java.png");

        WebElement fileInput = driver.findElement(By.name("my-file"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());

        String currentName = (String) js.executeScript("return arguments[0].value", fileInput);

        Assert.assertNotNull(currentName, "File wasn't uploaded");
        Assert.assertTrue(currentName.contains("java.png"), "Input doesn't contain name");
    }

    @Test
    public void testCheckboxes() {
        driver.findElement(By.linkText("Web form")).click();

        boolean isCheckedCheckboxSelected = driver.findElement(By.id("my-check-1")).isSelected();
        boolean isDefaultCheckboxSelected = driver.findElement(By.id("my-check-2")).isSelected();

        Assert.assertTrue(isCheckedCheckboxSelected, "Checked Checkbox is not selected");
        Assert.assertFalse(isDefaultCheckboxSelected, "Default Checkbox is selected");
    }

    @Test
    public void testUnselectCheckedCheckbox() {
        driver.findElement(By.linkText("Web form")).click();

        WebElement checkedCheckbox = driver.findElement(By.id("my-check-1"));
        checkedCheckbox.click();

        Assert.assertFalse(checkedCheckbox.isSelected(), "Checked Checkbox wasn't unselected");
    }

    @Test
    public void testSelectDefaultCheckbox() {
        driver.findElement(By.linkText("Web form")).click();

        WebElement defaultCheckbox = driver.findElement(By.id("my-check-2"));
        defaultCheckbox.click();

        Assert.assertTrue(defaultCheckbox.isSelected(), "Default Checkbox was not selected");
    }

    @Test
    public void testDataPicker() {
        driver.findElement(By.linkText("Web form")).click();
        WebElement dataPicker = driver.findElement(By.name("my-date"));
        dataPicker.click();

        driver.findElement(By.xpath("//td[@data-date='1741910400000']")).click();
        String DatePickerValue = dataPicker.getDomProperty("value");

        Assert.assertEquals(DatePickerValue, "03/14/2025", "Date is not displayed");
    }

    @Test
    public void testExampleRangeMoveForward() {
        driver.findElement(By.linkText("Web form")).click();

        WebElement exampleRange = driver.findElement(By.name("my-range"));
        String value = exampleRange.getDomAttribute("value");

        Actions actions = new Actions(driver);
        actions.clickAndHold(exampleRange)
                .moveByOffset(50, 0)
                .release()
                .perform();

        String newValue = (String) js.executeScript("return arguments[0].value", exampleRange);

        Assert.assertNotNull(newValue, newValue + " is null");
        Assert.assertNotNull(value, value + " is null");
        Assert.assertNotEquals(value, newValue, "Slider didn't move");
        Assert.assertTrue(Integer.parseInt(value) < Integer.parseInt(newValue));
    }

    @Test
    public void testExampleRangeMoveBack() {
        driver.findElement(By.linkText("Web form")).click();

        WebElement exampleRange = driver.findElement(By.name("my-range"));
        String value = exampleRange.getDomAttribute("value");

        js.executeScript("arguments[0].value= 1; arguments[0].dispatchEvent(new Event('input'))", exampleRange);

        String newValue = (String) js.executeScript("return arguments[0].value", exampleRange);

        Assert.assertNotNull(newValue, newValue + " is null");
        Assert.assertNotNull(value, value + " is null");
        Assert.assertNotEquals(value, newValue, "Slider didn't move");
        Assert.assertTrue(Integer.parseInt(newValue) < Integer.parseInt(value));
    }
}