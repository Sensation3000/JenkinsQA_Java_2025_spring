package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.time.Duration;
import java.util.*;

public class SunFlowerTest {

    private WebDriver driver;
    private final String BASE_URL = "https://www.selenium.dev/selenium/web/web-form.html";

    @BeforeMethod
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().driverVersion("135").setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Elements locators (без изменений)
    private WebElement textInput() { return driver.findElement(By.cssSelector("input[name='my-text']")); }
    private WebElement passwordInput() { return driver.findElement(By.cssSelector("input[name='my-password']")); }
    private WebElement textArea() { return driver.findElement(By.cssSelector("textarea[name='my-textarea']")); }
    private WebElement disabledInput() { return driver.findElement(By.cssSelector("input[name='my-disabled']")); }
    private WebElement readOnlyInput() { return driver.findElement(By.cssSelector("input[name='my-readonly']")); }
    private WebElement submitButton() { return driver.findElement(By.cssSelector("button[type='submit']")); }
    private List<WebElement> radioButtons() { return driver.findElements(By.cssSelector("input[type='radio'][name='my-radio']")); }
    private List<WebElement> checkboxes() { return driver.findElements(By.cssSelector("input[type='checkbox'][name='my-check']")); }
    private WebElement colorDropdown() { return driver.findElement(By.cssSelector("input[name='my-colors']")); }
    private WebElement fileInput() { return driver.findElement(By.cssSelector("input[type='file'][name='my-file']")); }

    // Тесты (остались без изменений кроме одного)
    @Test
    public void testTextInputAndLabel() { Assert.assertTrue(textInput().isDisplayed()); Assert.assertTrue(textInput().isEnabled()); }
    @Test
    public void testPasswordInputAndLabel() { Assert.assertTrue(passwordInput().isDisplayed()); Assert.assertEquals(passwordInput().getAttribute("type"), "password"); }
    @Test
    public void testTextareaAndLabel() { Assert.assertTrue(textArea().isDisplayed()); Assert.assertEquals(textArea().getTagName(), "textarea"); }
    @Test
    public void testDisabledInput() { WebElement input = disabledInput(); Assert.assertTrue(input.isDisplayed()); Assert.assertFalse(input.isEnabled()); }
    @Test
    public void testReadOnlyInput() { WebElement input = readOnlyInput(); Assert.assertTrue(input.isDisplayed()); Assert.assertEquals(input.getAttribute("readonly"), "true"); }
    @Test
    public void testSubmitButton() { WebElement button = submitButton(); Assert.assertTrue(button.isDisplayed()); Assert.assertEquals(button.getText(), "Submit"); Assert.assertTrue(button.isEnabled()); }
    @Test
    public void testRadioButtons() { List<WebElement> radios = radioButtons(); Assert.assertEquals(radios.size(), 2); radios.forEach(radio -> { Assert.assertTrue(radio.isDisplayed()); Assert.assertTrue(radio.isEnabled()); }); }

    @Test
    public void firstTest () throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://ingamejob.com/en");
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.isEmpty(), "Page title should not be empty");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement professionInput = driver.findElement(By.xpath("//div[@class='home-hero-section']//button"));
        professionInput.click();

        WebElement searchBox = driver.findElement(By.xpath("//div[@class='bs-searchbox']/input"));
        searchBox.sendKeys("qa");

        WebElement professionOption = driver.findElement(By.xpath("//ul[@class='dropdown-menu inner show']//span[contains(text(), 'QA')]"));
        professionOption.click();

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        Thread.sleep(1000);

        WebElement jobTitle = driver.findElement(By.xpath("//div[contains(@class, 'listing-job-info')]//h5/a"));
        String jobTitleText = jobTitle.getText();
        Assert.assertTrue(!jobTitleText.isEmpty(), "Job title should not be empty");

        driver.quit();
    }

    @Test
    public void testSearchProfession (){
        WebDriver driver = new ChromeDriver();

        driver.get("https://ingamejob.com/en");

        WebElement navigationPage = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul[1]/li[1]/a"));
        navigationPage.click();

        WebElement searchKeywords = driver.findElement(By.xpath("//*[@id=\"filterCollapse\"]/input"));
        searchKeywords.sendKeys("qa manual");

        WebElement cookies = driver.findElement(By.xpath("//*[@id=\"termly-code-snippet-support\"]/div/div/div/div/div[2]/button[3]"));
        cookies.click();

        WebElement button = driver.findElement(By.xpath("//*[@id=\"app-all\"]/div[1]/div/div[2]/div/div[1]/div[1]/form/div[5]/button"));
        button.click();

        WebElement nameCompany = driver.findElement(By.xpath("//*[@id=\"app-all\"]/div[1]/div/div[2]/div/div[2]/div/div[2]/div/div/div[2]/p[1]/strong"));
        String nameCompanyText = nameCompany.getText();
        Assert.assertFalse(nameCompanyText.isEmpty(), "Company should not be empty");

        driver.quit();
    }

    @Test
    public void testCheckboxesStateChange() {
        List<WebElement> checkboxes = checkboxes();
        Assert.assertEquals(checkboxes.size(), 2, "Количество чекбоксов должно быть 2");

        List<Boolean> initialStates = new ArrayList<>();
        for (int i = 0; i < checkboxes.size(); i++) {
            WebElement checkbox = checkboxes.get(i);
            Assert.assertTrue(checkbox.isDisplayed(), "Чекбокс " + (i + 1) + " не отображается");
            boolean isSelected = checkbox.isSelected();
            initialStates.add(isSelected);
        }

        for (int i = 0; i < checkboxes.size(); i++) {
            checkboxes.get(i).click();
        }

        for (int i = 0; i < checkboxes.size(); i++) {
            boolean expectedState = !initialStates.get(i);
            boolean actualState = checkboxes.get(i).isSelected();
            Assert.assertEquals(actualState, expectedState, "Состояние чекбокса " + (i + 1) + " не изменилось как ожидалось");
        }
    }

    @Test
    public void testColorDropdown() { Assert.assertTrue(colorDropdown().isDisplayed()); }
    @Test
    public void testFileInput() {
        WebElement fileInput = fileInput();
        Assert.assertTrue(fileInput.isDisplayed());
        Assert.assertEquals(fileInput.getAttribute("type"), "file");
    }

    /**
     * Основной тест с улучшениями: таблица + XPath + сохранение отчётов
     */
    @Test
    public void testParseVisibleElementsWithText() {
        List<WebElement> elementsWithText = parseVisibleElementsWithText();

        // Генерация ASCII-таблицы
        String table = buildTable(elementsWithText);

        // Печать в консоль
        System.out.println(table);

        // Сохранение в файл
        saveReportToFile("visible_elements_report.txt", table);
        saveCsvReport("visible_elements_report.csv", elementsWithText);

        // Тестовая проверка
        Assert.assertTrue(elementsWithText.size() > 0, "Не найдено ни одного видимого элемента с текстом.");
    }

    /**
     * Находит все видимые элементы с текстом
     */
    private List<WebElement> parseVisibleElementsWithText() {
        List<WebElement> allElements = driver.findElements(By.xpath("//*"));
        List<WebElement> result = new ArrayList<>();

        for (WebElement element : allElements) {
            try {
                if (!element.isDisplayed()) continue;
                String text = element.getText().trim();
                if (!text.isEmpty()) result.add(element);
            } catch (Exception e) {
                System.out.println("Ошибка при обработке элемента: " + e.getMessage());
            }
        }

        return result;
    }

    /**
     * Генерация CSS селектора или Xpath, если нет id/class/name
     */
    private String getElementSelector(WebElement element) {
        try {
            String id = element.getAttribute("id");
            if (id != null && !id.isEmpty()) return "#" + id;

            String classes = element.getAttribute("class");
            if (classes != null && !classes.isEmpty()) return element.getTagName() + "." + classes.split("\\s+")[0];

            String name = element.getAttribute("name");
            if (name != null && !name.isEmpty()) return element.getTagName() + "[name='" + name + "']";

            // Если ничего нет, генерируем XPath
            return generateXPath(element);
        } catch (Exception e) {
            return element.toString();
        }
    }

    /**
     * Генерация XPath выражения для элемента
     */
    private String generateXPath(WebElement element) {
        String js = "function absoluteXPath(element) {" +
                "var comp, comps = [];" +
                "var parent = null;" +
                "var xpath = '';" +
                "var getPos = function(element) {" +
                "var position = 1, curNode;" +
                "if (element.nodeType == Node.ATTRIBUTE_NODE) {" +
                "return null;" +
                "}" +
                "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {" +
                "if (curNode.nodeName == element.nodeName) {" +
                "++position;" +
                "}" +
                "}" +
                "return position;" +
                "};" +

                "if (element instanceof Document) {" +
                "return '/';" +
                "}" +

                "for (; element && !(element instanceof Document); element = element.nodeType ==Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {" +
                "comp = comps[comps.length] = {};" +
                "switch (element.nodeType) {" +
                "case Node.TEXT_NODE:" +
                "comp.name = 'text()';" +
                "break;" +
                "case Node.ATTRIBUTE_NODE:" +
                "comp.name = '@' + element.nodeName;" +
                "break;" +
                "case Node.PROCESSING_INSTRUCTION_NODE:" +
                "comp.name = 'processing-instruction()';" +
                "break;" +
                "case Node.COMMENT_NODE:" +
                "comp.name = 'comment()';" +
                "break;" +
                "case Node.ELEMENT_NODE:" +
                "comp.name = element.nodeName;" +
                "break;" +
                "}" +
                "comp.position = getPos(element);" +
                "}" +

                "for (var i = comps.length - 1; i >= 0; i--) {" +
                "comp = comps[i];" +
                "xpath += '/' + comp.name.toLowerCase();" +
                "if (comp.position !== null && comp.position > 1) {" +
                "xpath += '[' + comp.position + ']';" +
                "}" +
                "}" +

                "return xpath;" +
                "}" +
                "return absoluteXPath(arguments[0]);";

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript(js, element);
    }

    /**
     * Строит ASCII-таблицу
     */
    private String buildTable(List<WebElement> elements) {
        StringBuilder table = new StringBuilder();

        String format = "| %-3s | %-10s | %-40s | %-30s |%n";
        String separator = "+-----+------------+------------------------------------------+--------------------------------+%n";

        table.append(String.format(separator));
        table.append(String.format(format, "№", "Tag Name", "Selector / XPath", "Text"));
        table.append(String.format(separator));

        int index = 1;
        for (WebElement element : elements) {
            String tagName = element.getTagName();
            String selector = getElementSelector(element);
            String text = element.getText().trim().replaceAll("\\s+", " ");

            String truncatedText = text.length() > 28 ? text.substring(0, 25) + "..." : text;

            table.append(String.format(format, index++, tagName, selector, truncatedText));
        }

        table.append(String.format(separator));
        table.append(String.format("Всего видимых элементов с текстом: %d%n", elements.size()));

        return table.toString();
    }

    /**
     * Сохраняем ASCII-таблицу в txt файл
     */
    private void saveReportToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Отчёт сохранён в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении отчёта: " + e.getMessage());
        }
    }

    /**
     * Сохраняем список элементов в CSV
     */
    private void saveCsvReport(String filename, List<WebElement> elements) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("№,Tag Name,Selector / XPath,Text\n");

            int index = 1;
            for (WebElement element : elements) {
                String tagName = element.getTagName();
                String selector = getElementSelector(element).replace(",", ";");
                String text = element.getText().trim().replaceAll("[\\r\\n]+", " ").replace(",", ";");

                writer.write(String.format("%d,%s,%s,%s%n", index++, tagName, selector, text));
            }

            System.out.println("CSV отчёт сохранён в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении CSV: " + e.getMessage());
        }
    }

    @Test
    public void testW3School() throws InterruptedException {
        WebDriverManager.chromedriver().setup();    // Настройка WebDriverManager для автоматической загрузки драйвера

        WebDriver driver = new ChromeDriver();      // Создаем экземпляр ChromeDriver, который будет управлять браузером

        // Установка конкретного размера окна
        Dimension dimension = new Dimension(1920, 1080); // Ширина 1920px, высота 1080px
        driver.manage().window().setSize(dimension); // Установка размера окна
        driver.get("https://www.w3schools.com/");          // Переход на страницу w3schools

        Thread.sleep(5000);

        // Нахождение элемента "XML Tutorial" по атрибуту title
        WebElement xmlLink = driver.findElement(By.xpath("//a[text()='XML']"));
        xmlLink.click();

        // Явное ожидание заголовка страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // вместо Thread.sleep(2000);
        wait.until(ExpectedConditions.titleContains("XML Tutorial"));

        // Проверка, что мы находимся на странице "XML Tutorial"
        String pageTitle = driver.getTitle(); // Метод возвращает заголовок текущей страницы, который затем сохраняется в переменной pageTitle
        System.out.println("Page Title: " + pageTitle);
        assert pageTitle.contains("XML Tutorial");      // Проверка, что заголовок содержит "xml"

        WebElement xmlXpathLink = driver.findElement(By.xpath("//a[@href='xml_xpath.asp']"));
        xmlXpathLink.click();

        driver.quit();
    }
}
