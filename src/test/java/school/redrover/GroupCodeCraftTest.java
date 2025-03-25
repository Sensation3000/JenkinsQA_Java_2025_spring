package school.redrover;

import net.datafaker.Faker;
import net.datafaker.providers.base.Text;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static java.time.temporal.ChronoUnit.SECONDS;
import static net.datafaker.providers.base.Text.*;
import static org.testng.Assert.*;

public class GroupCodeCraftTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private Random random;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        random = new Random();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "wikiArticles")
    public Object[][] provideArticles() {
        return new Object[][] {
                {"Artificial Intelligence"},
                {"Machine Learning"},
                {"Quantum Computing"},
        };
    }

    @Test
    public void testSwagLabs() {
        driver.get("https://www.saucedemo.com");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        WebElement removeBackpack = driver.findElement
                (By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory ']"));
        String color = removeBackpack.getCssValue("border");
        int rgbIndex = color.indexOf("rgb");
        String removeBackpackText = removeBackpack.getText();

        WebElement cart = driver.findElement
                (By.xpath("//span[@class='shopping_cart_badge']"));

        driver.findElement
                (By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']"))
                .click();
        String cartShopNumbers = cart.getText();

        cart.click();

        WebElement firstItem = driver.findElement
                (By.xpath("//a[@id='item_4_title_link']/div"));
        String firstItemText = firstItem.getText();
        WebElement secondItem = driver.findElement
                (By.xpath("//a[@id='item_5_title_link']/div"));
        String secondItemText = secondItem.getText();

        String firstPrice = driver.findElement
                        (By.xpath("//a[@id='item_4_title_link']/following-sibling::div[2]/div")).
                getText().substring(1);
        double firstPriceDouble = Double.parseDouble(firstPrice);

        String secondString = driver.findElement
                        (By.xpath("//a[@id='item_5_title_link']/following-sibling::div[2]/div")).
                getText().substring(1);
        double secondPriceDouble = Double.parseDouble(secondString);

        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        driver.findElement(By.xpath("//input[@id='first-name']")).
                sendKeys("Jane");
        driver.findElement(By.xpath("//input[@id='last-name']")).
                sendKeys("Doe");
        driver.findElement(By.xpath("//input[@id='postal-code']")).
                sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='continue']")).
                click();

        WebElement shippingInformation = driver.findElement
                (By.xpath("//div[@data-test='shipping-info-value']"));
        String shippingInformationText = shippingInformation.getText();

        double taxSum = (firstPriceDouble + secondPriceDouble) * 0.08;
        double totalPriceWithTax = firstPriceDouble + secondPriceDouble + taxSum;
        double totalPriceWithoutTax = firstPriceDouble + secondPriceDouble;

        double roundedTax = Math.round(taxSum * 100.0) / 100.0;
        double roundedPriceWithTax = Math.round(totalPriceWithTax * 100.0) / 100.0;
        double roundedPriceWithoutTax = Math.round(totalPriceWithoutTax * 100.0) / 100.0;

        WebElement itemTotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        WebElement tax = driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
        WebElement total = driver.findElement(By.xpath("//div[@data-test='total-label']"));
        String itemTotalText = itemTotal.getText();
        String taxText = tax.getText();
        String totalText = total.getText();

        driver.findElement(By.xpath("//button[@id='finish']")).
                click();
        driver.findElement(By.xpath("//button[@id='back-to-products']")).
                click();

        WebElement swagLabsPageLogo = driver.findElement
                (By.xpath("//div[@class='app_logo']"));

        assertEquals(removeBackpackText, "Remove");
        assertEquals(color.substring(rgbIndex), "rgb(226, 35, 26)");
        assertEquals(cartShopNumbers, "2");
        assertEquals(firstItemText, "Sauce Labs Backpack");
        assertEquals(secondItemText, "Sauce Labs Fleece Jacket");
        assertEquals(firstPrice, "29.99");
        assertEquals(secondString, "49.99");
        assertEquals(shippingInformationText, "Free Pony Express Delivery!");
        assertEquals(itemTotalText, "Item total: $" + roundedPriceWithoutTax);
        assertEquals(taxText, "Tax: $" + roundedTax + "0");
        assertEquals(totalText, "Total: $" + roundedPriceWithTax);
        assertEquals(swagLabsPageLogo.getText(), "Swag Labs");
    }


    @Test
    public void testToolsQATestBox() {
        driver.get("https://demoqa.com");

        WebElement card = driver.findElement
                (By.xpath("//div[1][@class='card mt-4 top-card']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        card.click();

        WebElement textBox = driver.findElement
                (By.xpath("//div[@class='element-list collapse show']/descendant::li[@id='item-0']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", textBox);
        textBox.click();

        String fullName = "Jane Doe";
        String eMail = "janedoe@yahoo.com";
        String currentAddress = "Pushkin's street, Kolotushkin's house";
        String permAddress = "Same as current";

        driver.findElement(By.xpath("//input[@placeholder='Full Name']"))
                .sendKeys(fullName);
        driver.findElement(By.xpath("//input[@id='userEmail']"))
                .sendKeys(eMail);
        driver.findElement(By.xpath("//textarea[@id='currentAddress']"))
                .sendKeys(currentAddress);
        driver.findElement(By.xpath("//textarea[@id='permanentAddress']"))
                .sendKeys(permAddress);

        WebElement submit = wait.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        submit.click();

        assertEquals(wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='name']"))).getText(),
                "Name:" + fullName);
        assertEquals(wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='email']"))).getText(),
                "Email:" + eMail);
        assertEquals(wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='currentAddress']"))).getText(),
                "Current Address :" + currentAddress);
        assertEquals(wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='permanentAddress']"))).getText(),
                "Permananet Address :" + permAddress);
    }

    @Test
    public void testWebForm() {
        var faker = new Faker(new Locale("en"));

        String fullName = faker.name().fullName();
        String password = faker.internet().password();
        String text = faker.lorem().sentence();

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement name = driver.findElement(By.id("my-text-id"));
        name.sendKeys(fullName);

        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys(password);

        WebElement textArea = driver.findElement(By.tagName("textarea"));
        textArea.sendKeys(text);

        assertEquals(name.getDomProperty("value"), fullName);
        assertEquals(passwordField.getDomProperty("value"), password);
        assertEquals(textArea.getDomProperty("value"), text);
        assertFalse(driver.findElement
                (By.xpath("//input[@name='my-disabled']")).isEnabled());
        assertNotNull(driver.findElement
                        (By.xpath("//input[@name='my-readonly']")).
                getDomAttribute("readonly"));
    }

    @Test
    public void testWebFormSelect() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement dropDownMenu = driver.findElement
                (By.xpath("//select[@name='my-select']"));

        Select select = new Select(dropDownMenu);

        select.selectByContainsVisibleText("Open this select menu");
        String firstCheck = dropDownMenu.getDomProperty("value");

        select.selectByValue("1");
        String secondCheck = dropDownMenu.getDomProperty("value");

        select.selectByIndex(2);
        String thirdCheck = dropDownMenu.getDomProperty("value");

        select.selectByContainsVisibleText("Three");
        String fourthCheck = dropDownMenu.getDomProperty("value");

        assertEquals(firstCheck, "Open this select menu");
        assertEquals(secondCheck, "1");
        assertEquals(thirdCheck, "2");
        assertEquals(fourthCheck, "3");
    }

    @Test
    public void testCheckboxAndFileInput() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement fileInput = driver.findElement(By.xpath("//input[@name='my-file']"));

        File tempFile = File.createTempFile("temp", ".txt");
        FileUtils.writeStringToFile(tempFile, "This is a test file content.", "UTF-8");
        fileInput.sendKeys(tempFile.getAbsolutePath());
        tempFile.deleteOnExit();

        WebElement checkedCheckbox = wait.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='my-check-1']")));
        WebElement defaultCheckbox = wait.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='my-check-2']")));
        WebElement checkedRadio = wait.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='my-radio-1']")));
        WebElement defaultRadio = wait.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='my-radio-2']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", defaultRadio);

        checkedCheckbox.click();
        defaultCheckbox.click();
        defaultRadio.click();
        checkedRadio.click();

        assertFalse(checkedCheckbox.isSelected());
        assertTrue(defaultCheckbox.isSelected());
        assertTrue(checkedRadio.isSelected());
        assertFalse(defaultRadio.isSelected());
        assertFalse(Objects.requireNonNull(fileInput.getDomProperty("value")).isEmpty());
    }

    @Test
    public void testLumaCreateAccount() throws InterruptedException {
        var faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName.toLowerCase() + lastName.toLowerCase() + "@gmail.com";
        String password = faker.text().text(Text.TextSymbolsBuilder.builder()
                .len(14)
                .with(EN_UPPERCASE, 3)
                .with(EN_LOWERCASE, 4)
                .with(DEFAULT_SPECIAL, 2)
                .with(DIGITS, 3).build());

        driver.get("https://magento.softwaretestingboard.com/");

        WebElement createAccount = wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath("//header/div[1]/div/ul/li[3]/a")));
        createAccount.click();

        WebElement firstname = wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));
        firstname.sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password-confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector(".action.submit.primary")).click();

        WebElement accountCreated = wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")));
        assertEquals(accountCreated.getText(), "Thank you for registering with Main Website Store.");
    }

    @Test
    public void demoQAFormTest() throws InterruptedException {
        //заходим на сайт
        driver.get("https://demoqa.com");

        //выбираем кнопку по её xPath и нажимаем
        WebElement elementButton = driver.findElement(By.xpath(("//*[@id=\"app\"]/div/div/div[2]/div/div[1]/div/div[2]")));
        elementButton.click();

        Thread.sleep(1000); //подождем немного

        WebElement webTables = driver.findElement(By.xpath("//*[@id=\"item-3\"]/span"));
        webTables.click();

        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        addButton.click();

        //заполняем поля формы по очереди, ищем их по id
        WebElement inputFirstName = driver.findElement(By.id("firstName"));
        inputFirstName.sendKeys("Emiel Regis Rohellec");

        WebElement inputLastName = driver.findElement(By.id("lastName"));
        inputLastName.sendKeys("Terzieff-Godefroy");

        WebElement inputEmail = driver.findElement(By.id("userEmail"));
        inputEmail.sendKeys("garlic@forever.va");

        WebElement inputAge = driver.findElement(By.id("age"));
        inputAge.sendKeys("99");

        WebElement inputSalary = driver.findElement(By.id("salary"));
        inputSalary.sendKeys("50000");

        WebElement inputDepartment = driver.findElement(By.id("department"));
        inputDepartment.sendKeys("Geralt's company");

        //отправляем данные в форму
        WebElement submitButton1 = driver.findElement(By.id("submit"));
        submitButton1.click();

        //получаем текстовые данные из последней строки таблицы
        WebElement tableName4 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[1]"));
        String addedName4 = tableName4.getText();

        WebElement tableLastName4 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[2]"));
        String addedLastName4 = tableLastName4.getText();

        WebElement tableEmail4 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[4]"));
        String addedEmail4 = tableEmail4.getText();

        WebElement tableAge4 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[3]"));
        String addedAge4 = tableAge4.getText();

        WebElement tableSalary4 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[5]"));
        String addedSalary4 = tableSalary4.getText();

        WebElement tableDepartment4 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[6]"));
        String addedDepartment4 = tableDepartment4.getText();

        //проверяем соответствие сохранённых данных введённым ранее
        Assert.assertEquals(addedName4, "Emiel Regis Rohellec");
        Assert.assertEquals(addedLastName4, "Terzieff-Godefroy");
        Assert.assertEquals(addedEmail4, "garlic@forever.va");
        Assert.assertEquals(addedAge4, "99");
        Assert.assertEquals(addedSalary4, "50000");
        Assert.assertEquals(addedDepartment4, "Geralt's company");

        //изменяем данные имеющейся строки
        WebElement changeButton = driver.findElement(By.id("edit-record-3"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", changeButton);
        changeButton.click();

        WebElement editFirstName = driver.findElement(By.id("firstName"));
        editFirstName.clear();
        editFirstName.sendKeys("Jane");
        WebElement submitButton2 = driver.findElement(By.id("submit"));
        submitButton2.click();

        //проверяем, что данные изменились
        WebElement tableName3 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[3]/div/div[1]"));
        String addedName3 = tableName3.getText();
        Assert.assertEquals(addedName3, "Jane");

        //удаляем созданную ранее строку
        WebElement deleteButton = driver.findElement(By.id("delete-record-4"));
        deleteButton.click();

        //проверяем что строка пустая
        tableName4 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[1]"));
        addedName4 = tableName4.getText();
        Assert.assertEquals(addedName4, " ");
    }

    @Test
    public void testDemoQa() throws InterruptedException {

        driver.get("https://demoqa.com/");

        WebElement buttForms = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div[2]/div/div[1]"));
        buttForms.click();
        Thread.sleep(400);

        WebElement buttElements = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[1]/div/div/div[1]/span/div/div[1]"));
        buttElements.click();
        Thread.sleep(200);

        WebElement textBox = driver.findElement(By.xpath("//*[@id=\"item-0\"]/span"));
        textBox.click();

        WebElement fullNameBox = driver.findElement(By.xpath("//*[@id=\"userName\"]"));
        fullNameBox.click();
        fullNameBox.sendKeys("Ivan Ivanov");

        WebElement emailBox = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[2]/div[2]/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", emailBox);
        emailBox.click();
        emailBox.sendKeys("ivanovich@yahoo.com");

        WebElement adresBox = driver.findElement(By.xpath("//*[@id=\"currentAddress\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", adresBox);
        adresBox.click();
        adresBox.sendKeys("Улица пушкина, Город Колотушкино");

        WebElement perAdresBox = driver.findElement(By.xpath("//*[@id=\"permanentAddress\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", perAdresBox);
        perAdresBox.click();
        perAdresBox.sendKeys("Темная");

        WebElement submitBox = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[5]/div/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBox);
        submitBox.click();
        Thread.sleep(300);

        WebElement answerName = driver.findElement(By.xpath("//div/p[text()=\"Ivan Ivanov\"]"));
        String getName = answerName.getText();
        Assert.assertEquals(getName, "Name:Ivan Ivanov");

        WebElement answerEmail = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[6]/div/p[2]"));
        String getEmail = answerEmail.getText();
        Assert.assertEquals(getEmail, "Email:ivanovich@yahoo.com");
    }

    @Test
    public void testBonigarciaWebFormXpath() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.findElement(By.xpath("//div/a[@href='web-form.html']")).click();
        driver.findElement(By.xpath("//input[@name='my-text']")).sendKeys("Adelya");
        driver.findElement(By.xpath("//input[@name='my-password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//textarea[@name='my-textarea']")).sendKeys("something important");

        WebElement dropDownSelect = driver.findElement(By.xpath("//select"));
        Select openThisSelectMenu = new Select(dropDownSelect);
        openThisSelectMenu.selectByVisibleText("Three");

        driver.findElement(By.xpath("//input[@list='my-options']")).sendKeys("Saint-Petersburg");

        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        String relativeFilePath = "src/test/resources/uploadFiles/java.png";
        File fileToUpload = new File(relativeFilePath);
        String absoluteFilePath = fileToUpload.getAbsolutePath();
        fileInput.sendKeys(absoluteFilePath);

        driver.findElement(By.xpath("//input[@id='my-check-1']")).click();
        driver.findElement(By.xpath("//input[@id='my-check-2']")).click();
        driver.findElement(By.xpath("//input[@id='my-radio-2']")).click();
        driver.findElement(By.xpath("//input[@name='my-colors']")).sendKeys("#ff0099");
        driver.findElement(By.xpath("//input[@class='form-control' and @name='my-date']")).sendKeys("25/03/2025");

        WebElement exampleRange = driver.findElement(By.xpath("//input[@class='form-range']"));
        Thread.sleep(1000);
        actions.clickAndHold(exampleRange)
                .moveByOffset(60, 0)
                .release()
                .perform();

        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        actions.moveToElement(submitButton).perform();
        submitButton.click();

        WebElement formSubmitted = driver.findElement(By.xpath("//h1[text()='Form submitted']"));
        assertTrue(formSubmitted.isDisplayed(), "Verifying if the confirmation page is displayed");
    }

    @Test
    public void testTitleEnPodcast() {

        driver.get("https://learningenglish.voanews.com/p/5610.html");
        driver.findElement(By.xpath("//div[@id='page']//label[contains(@class, 'top-srch-trigger')]")).click();

        driver.findElement(By.id("txtHeaderSearch")).sendKeys("learning english");
        driver.findElement(By.tagName("button")).click();

        WebElement title = driver.findElement(By.xpath("//div[@id='search-results']//li[1]//h4"));
        String titleText = title.getText();

        Assert.assertEquals(titleText, "Learning English Podcast");

    }

    @Test
    public void testSearch() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://krasivoe.by/");

        WebElement input = driver.findElement(By.xpath("//*[@id=\"search\"]/input"));
        input.click();
        input.sendKeys("браслет");

        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"search\"]/button")).click();

        String citeText = driver.findElement(By.xpath("//*[@id=\"mfilter-content-container\"]/h1")).getText();

        Assert.assertEquals(citeText, "ПОИСК - БРАСЛЕТ");
    }

    @Test
    public void testClickAndNewWindowTitle() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        Thread.sleep(1000);

        driver.findElement(By.tagName("button")).click();
        Thread.sleep(1000);

        String newWindowTitle = driver.findElement(By.className("display-6")).getText();

        Assert.assertEquals(newWindowTitle, "Form submitted");
    }

    @Test
    public void testSelectSimpleDropDown() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/selectPage.html");
        WebElement selectWithoutMultiple = driver.findElement(By.id("selectWithoutMultiple"));
        Thread.sleep(1000);

        Select simpleDropDown = new Select(selectWithoutMultiple);
        simpleDropDown.selectByValue("two");
        Thread.sleep(1000);

        String value = selectWithoutMultiple.getAttribute("value");

        Assert.assertEquals(value, "two");
    }

    @Test
    public void testLongList() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/selectPage.html");
        Thread.sleep(1000);
        WebElement selectElement = driver.findElement(By.id("selectWithMultipleLongList"));

        Select select = new Select(selectElement);
        select.selectByVisibleText("six");
        Thread.sleep(1000);

        String value = selectElement.getAttribute("value");

        Assert.assertEquals(value, "six");
    }

    @Test
    public void testDemoQARadioButton() throws InterruptedException {
        driver.get("https://demoqa.com");

        WebElement firstBlock =
                driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstBlock);
        firstBlock.click();

        Thread.sleep(500);
        driver.findElement(By.id("item-2")).click();

        WebElement radioButton = new WebDriverWait(driver, Duration.of(5, SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='impressiveRadio']")));
        radioButton.click();

        Thread.sleep(500);
        Assert.assertEquals(
                driver.findElement(By.xpath("//div[2]/div[2]/p/span")).getText(),
                "Impressive");
    }

    @Test
    public void testBGDropDown() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/index.html");

        WebElement dropDown =
                driver.findElement(By.xpath("//div[1]/div/div/a[3]"));
        dropDown.click();

        driver.findElement(By.id("my-dropdown-1")).click();

        Assert.assertEquals(
                driver.findElement(By.xpath("//div[1]/div/ul/li[3]/a")).getText(),
                "Something else here");
    }

    @Test
    public void inputArrowUpTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));
        inputField.sendKeys(Keys.ARROW_UP);
        String fieldNumber = inputField.getAttribute("value");
        Assert.assertEquals(fieldNumber, "1");
    }

    @Test
    public void inputArrowDownTest() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));
        inputField.sendKeys(Keys.ARROW_DOWN);
        String fieldNumber = inputField.getAttribute("value");
        Assert.assertEquals(fieldNumber, "-1");
    }

    @Test
    public void testFakeStoreShopping() throws InterruptedException, AWTException {
        driver.get("https://letcode.in/home");

        driver.findElement(By.xpath("//*[@id=\"toggle-theme\"]")).click();

        Thread.sleep(500);
        WebElement monitor =
                driver.findElement(By.xpath("//div[14]/div/div[1]/figure/img"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", monitor);
        monitor.click();

        WebElement inCart = wait.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button is-primary mt-4' and span[text()='Add to Cart']]")));
        inCart.click();
        Thread.sleep(500);
        inCart.click();

        driver.findElement(By.xpath("//a[text()='Work-Space']")).click();

        driver.findElement(By.xpath("//a[text()=' Page Object Model ']")).click();

        driver.findElement(By.xpath("//i[contains(@class, 'fas') and contains(@class, 'fa-cart-shopping')]")).click();

        driver.findElement(By.xpath("//button[text()='-']")).click();

        WebElement checkoutButton = new WebDriverWait(driver, Duration.of(5, SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Checkout']")));
        checkoutButton.click();

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(5000);
        Assert.assertEquals(
                driver.findElement(By.xpath("//p[@class='title is-4']")).getText(),
                "Your cart is empty");
    }

    @Test(dataProvider = "wikiArticles")
    public void testWikipedia(String article) {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        driver.findElement(By.id("searchInput")).sendKeys(article);

        wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//form[@id='searchform']/descendant::button")))
                .click();

        assertEquals(article.toLowerCase(),
                wait.until(ExpectedConditions.visibilityOfElementLocated
                                (By.xpath("//h1/child::span")))
                        .getText()
                        .toLowerCase());
    }

    @Test
    public void testSlowCalculator() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");

        String[] operator = {"+", "-", "÷", "x"};
        int operatorRandom = random.nextInt(4);
        int firstNumber = random.nextInt(10);
        int secondNumber = random.nextInt(1,10);
        int delaySeconds = random.nextInt(6);

        WebElement delay = wait.until
                (ExpectedConditions.visibilityOfElementLocated(By.id("delay")));
        delay.clear();
        delay.sendKeys(String.valueOf(delaySeconds));

        wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='" + firstNumber + "']")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='" + operator[operatorRandom] + "']")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='" + secondNumber + "']")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='=']")))
                .click();

        Thread.sleep(delaySeconds * 1000 + 500);

        Number result = switch (operatorRandom) {
            case 0 -> firstNumber + secondNumber;
            case 1 -> firstNumber - secondNumber;
            case 2 -> (firstNumber % secondNumber == 0)
                    ? (Number) (firstNumber / secondNumber)
                    : (Number) ((double) firstNumber / (double) secondNumber);
            case 3 -> firstNumber * secondNumber;
            default -> 0;
        };

        assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//div[@class='screen']"))).getText(),
                String.valueOf(result));
    }

    @Test
    public void testDropDownMenu() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");

        WebElement leftClickDropDown = driver.findElement(By.id("my-dropdown-1"));

        actions.doubleClick(driver.findElement(By.id("my-dropdown-3"))).perform();
        actions.contextClick(driver.findElement(By.id("my-dropdown-2"))).perform();
        leftClickDropDown.click();

        assertTrue(Boolean.parseBoolean(leftClickDropDown.getDomProperty("ariaExpanded")));
        assertTrue(driver.findElement(By.id("context-menu-2")).isEnabled());
        assertTrue(driver.findElement(By.id("context-menu-3")).isEnabled());
    }

    @Test
    public void testMouseOver() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");

        WebElement elementForMouseEscape = driver.findElement(By.tagName("h5"));

        actions.moveToElement(driver.findElement
                (By.xpath("//img[@src='img/compass.png']"))).perform();
        boolean compassCheckVisible = wait.until
                        (ExpectedConditions.visibilityOfElementLocated
                                (By.xpath("//img[@src='img/compass.png']/ancestor::div[@class='figure text-center col-3 py-2']/descendant::p")))
                .isDisplayed();
        actions.moveToElement(elementForMouseEscape).perform();
        boolean compassOffCheck = wait.until
                (ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath("//img[@src='img/compass.png']/ancestor::div[@class='figure text-center col-3 py-2']/descendant::p")));

        actions.moveToElement(driver.findElement
                (By.xpath("//img[@src='img/calendar.png']"))).perform();
        boolean calendarCheckVisible = wait.until
                        (ExpectedConditions.visibilityOfElementLocated
                                (By.xpath("//img[@src='img/calendar.png']/ancestor::div[@class='figure text-center col-3 py-2']/descendant::p")))
                .isDisplayed();
        actions.moveToElement(elementForMouseEscape).perform();
        boolean calendarOffCheck = wait.until
                (ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath("//img[@src='img/calendar.png']/ancestor::div[@class='figure text-center col-3 py-2']/descendant::p")));

        actions.moveToElement(driver.findElement
                (By.xpath("//img[@src='img/award.png']"))).perform();
        boolean awardCheckVisible = wait.until
                        (ExpectedConditions.visibilityOfElementLocated
                                (By.xpath("//img[@src='img/award.png']/ancestor::div[@class='figure text-center col-3 py-2']/descendant::p")))
                .isDisplayed();
        actions.moveToElement(elementForMouseEscape).perform();
        boolean awardOffCheck = wait.until
                (ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath("//img[@src='img/award.png']/ancestor::div[@class='figure text-center col-3 py-2']/descendant::p")));

        actions.moveToElement(driver.findElement
                (By.xpath("//img[@src='img/landscape.png']"))).perform();
        boolean landscapeCheckVisible = wait.until
                        (ExpectedConditions.visibilityOfElementLocated
                                (By.xpath("//img[@src='img/landscape.png']/ancestor::div[@class='figure text-center col-3 py-2']/descendant::p")))
                .isDisplayed();
        actions.moveToElement(elementForMouseEscape).perform();
        boolean landscapeOffCheck = wait.until
                (ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath("//img[@src='img/landscape.png']/ancestor::div[@class='figure text-center col-3 py-2']/descendant::p")));

        assertTrue(compassCheckVisible);
        assertTrue(compassOffCheck);
        assertTrue(calendarCheckVisible);
        assertTrue(calendarOffCheck);
        assertTrue(awardCheckVisible);
        assertTrue(awardOffCheck);
        assertTrue(landscapeCheckVisible);
        assertTrue(landscapeOffCheck);
    }

    @Test
    public void testAddRemoveElements () {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        int clickCount = random.nextInt(3,20);

        for (int i = 0; i < clickCount; i++) {
            wait.until(ExpectedConditions.elementToBeClickable
                    (By.xpath("//button[text()='Add Element']"))).click();
        }

        int elementsCount = driver.findElements
                (By.xpath("//button[@class='added-manually']")).size();

        for (int i = clickCount; i > 0; i--) {
            wait.until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//button[" + i + "][@class='added-manually']")))
                    .click();
        }

        assertEquals(clickCount, elementsCount);
        assertTrue(driver.findElements
                (By.xpath("//button[@class='added-manually']")).isEmpty());
    }
    @Test
    public void StoreElementTest() throws InterruptedException {

        driver.get("https://demoblaze.com/");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a")).click();
        Thread.sleep(1000);

        WebElement productDetail = driver.findElement(By.xpath("//*[@id='tbodyid']/h2"));
        String productTitle = productDetail.getText();

        Assert.assertEquals(productTitle, "Samsung galaxy s6");
    }

    @Test
    public void testLoginMethod() throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(1000);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testSwagLabsCartBadgeHasItem() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();

        driver.findElement
                (By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@data-test='inventory-item-description']//button")).click();
        String itemCountInBadge = driver.findElement(By.xpath("//span[@data-test='shopping-cart-badge']")).getText();

        assertEquals(itemCountInBadge, "1");
    }
}