package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.time.Duration;
import java.util.Objects;

import static org.testng.Assert.*;

public class GroupRedRoverJavaUTC3Test {

    private WebDriver driver;

    private WebDriverWait wait5;
    private WebDriverWait wait10;
    private Actions actions;
    private SoftAssert softAssert;

    protected SoftAssert getSoftAssert() {
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }

        return softAssert;
    }

    protected Actions getActions() {
        if (actions == null) {
            actions = new Actions(getDriver());
        }

        return actions;
    }

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }

        return wait5;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }

        return wait10;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    protected void start() {
        driver = new ChromeDriver();

        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int browserWidth = Math.min(1920, screenWidth);
        int browserHeight = Math.min(1080, screenHeight);

        Dimension dimension = new Dimension(browserWidth, browserHeight);
        driver.manage().window().setSize(dimension);
    }

    @AfterMethod
    protected void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait5 = null;
            wait10 = null;
            softAssert = null;
            actions = null;
        }
    }

    private boolean isDisplayed(WebElement badgeElement) {
        try {
            return badgeElement.isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    @Test
    public void testAuthorization() {

        getDriver().get("https://school.qa.guru/cms/system/login?required=true");

        getDriver().findElement(By.name("email")).sendKeys("gjcjavxusj@zvvzuv.com");
        getDriver().findElement(By.name("password")).sendKeys("E3&i&d1B");
        getDriver().findElement(By.id("xdget33092_1_1")).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Профиль']"))).click();

        getDriver().findElement(By.xpath("//*[@target='_self']")).click();

        WebElement getName = getDriver().findElement(By.xpath("//h1[contains(text(),'Мой профиль')]"));

        Assert.assertEquals(getName.getText(), "Мой профиль");
    }

    @Test

    public void testRickAstley() throws InterruptedException {

        final String xPathPlayButton = "//button[@aria-keyshortcuts='k']";
        final String xPathReject = "//button[contains(@aria-label, 'Reject the use of cookies')]";

        getDriver().get("https://www.youtube.com/watch?v=hPr-Yc92qaY");

        try {
            getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath(xPathReject))).click();
        } catch (TimeoutException e) {
            System.out.println("The 'Reject' button didn't appear");
        }

        getDriver().findElement(By.xpath(xPathPlayButton)).click();

        int count = 0;

        WebElement button = getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath(xPathPlayButton)));

        for (int i = 0; i < 10; i++) {
            Thread.sleep(8000);
            button.click();
            count++;
        }

        Assert.assertEquals(count, 10);
    }

    @Test
    public void testCheckBox() {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        getDriver().findElement(By.cssSelector("#my-check-2")).click();

        getDriver().findElement(By.xpath("//button[@class='btn btn-outline-primary mt-3']")).click();

        WebElement message = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        System.out.println("Actual message text: " + message.getText());
        Assert.assertEquals(message.getText(), "Received!");

    }

    @Test
    public void testChangeLanguageAmazon() {
        getDriver().get("https://www.amazon.com/customer-preferences/edit");

        try {
            getWait5().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[contains(@class, 'glow-toaster-button-dismiss')]"))).click();
        } catch (TimeoutException e) {
            System.out.println("No toaster appeared within 5 seconds, moving on!");
        }

        getDriver().findElement(By.xpath("(//i[@class='a-icon a-icon-radio'])[2]")).click();
        getDriver().findElement(By.id("icp-save-button")).click();
        getWait5().until(ExpectedConditions.textToBe(
                By.xpath("//span[@aria-label='Estados Unidos']/following-sibling::div"), "ES"));

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//span[@aria-label='Estados Unidos']/following-sibling::div")).getText(), "ES");
    }

    @Test
    public void testImageFormatCheck() {
        // Открываем страницу
        getDriver().get("https://redrover.school/?lang=en");

        // Ожидаем, пока изображение загрузится
        WebElement image = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[contains(@alt, 'Sergey')]")));

        // Получаем URL изображения
        String imageUrl = image.getDomAttribute("src");
        String safeImageUrl = Objects.toString(imageUrl, "");

        // Проверяем, что ссылка заканчивается на .png
        Assert.assertTrue(safeImageUrl.toLowerCase().endsWith(".png"), "Image URL does not end with .png: " + safeImageUrl);
    }


    @Test
    public void testItemAddRemoveToCart() {
        getDriver().get("https://www.saucedemo.com/");

        getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        getDriver().findElement(By.id("password")).sendKeys("secret_sauce");

        getDriver().findElement(By.cssSelector("#login-button")).click();

        getDriver().findElement(By.cssSelector("#item_0_title_link > div")).click();
        getDriver().findElement(By.xpath("//*[@id='add-to-cart']")).click();

        WebElement badgeCart = driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a/span"));
        assertEquals(badgeCart.getText(), "1");

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='shopping_cart_container']/a"))).click();
        getDriver().findElement(By.xpath("//*[@id='remove-sauce-labs-bike-light']")).click();

        getWait10().until(ExpectedConditions.invisibilityOf(badgeCart));

        assertFalse(isDisplayed(badgeCart));
    }

    @Test
    public void testShopsSearch() {
        getDriver().get("https://sadovy.ru/semena/");

        getDriver().findElement(By.xpath("//*[@id='search_input']")).sendKeys("S020130");
        getDriver().findElement(By.className("ty-search-magnifier")).click();

        WebElement searchResult = driver.findElement(By.className("product-title"));

        assertEquals(searchResult.getText(), "Томат Мохнатый шмель 0.05г (Семена Алтая)");
    }

    @Test
    public void testSeasonvar() {
        getDriver().get("http://seasonvar.ru/");

        WebElement field = getDriver().findElement(By.cssSelector("div .awesomplete input"));
        field.click();
        field.sendKeys("Severance");

        getDriver().findElement(By.cssSelector("button.btn.head-btn")).click();

        String nameText = getDriver().findElement(By.xpath("//div[@class='pgs-search-info']/a[2]")).getText();
        Assert.assertEquals(nameText, "Severance");
    }

    @Test
    public void testAuthenticationForm() {

        getDriver().get("https://the-internet.herokuapp.com/login");

        getDriver().findElement(By.id("username")).sendKeys("tomsmith");

        getDriver().findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        getDriver().findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        String messageText = getDriver().findElement(By.xpath("//h4[@class='subheader']")).getText();


        Assert.assertEquals(messageText, "Welcome to the Secure Area. When you are done click logout below.");

    }

    @Test
    public void testTPlus() {

        getDriver().get("https://www.tplusgroup.ru/");

        getDriver().findElement(By.xpath("//*[@id=\"website-menu\"]/ul/li[1]/a")).click();

        String value = getDriver().findElement(By.xpath("//*[@id=\"content-header\"]/h1")).getText();
        Assert.assertEquals(value, "О компании");

    }

    @Test
    public void testEbayRedirectionToLoginPage() {

        getDriver().get("https://www.ebay.com/");

        getDriver().findElement(By.xpath("//*[@id=\"gh\"]/nav/div[1]/span[1]/span/a")).click();

        String currentTitle = getDriver().getTitle();
        String currentUrl = getDriver().getCurrentUrl();

        getSoftAssert().assertEquals(currentTitle, "Sign in or Register | eBay");
        getSoftAssert().assertEquals(currentUrl, "https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&sgfl=gh&ru=https%3A%2F%2Fwww.ebay.com%2F");

        getSoftAssert().assertAll();
    }

    @Test
    public void testHorizontalSliderTBank() {
        getDriver().get("https://www.tbank.ru/loans/cash-loan/realty/form/autoloan/");

        getActions().dragAndDropBy(getDriver().findElement(
                        By.xpath("//div[@data-field-name='cashloan_calculator_amount_field']//div[@data-qa-type='uikit/Draggable']")),
                -77, 0).perform();
        getActions().dragAndDropBy(getDriver().findElement(
                        By.xpath("//div[@data-field-name='cashloan_calculator_term_field']//div[@data-qa-type='uikit/Draggable']")),
                -40, 0).perform();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@data-qa-type='uikit/titleAndSubtitle.textPrimary']/div")).getText(), "27 600 ₽");
    }

    @Test
    public void testHorizontalSliderTestSite() {
        getDriver().get("https://the-internet.herokuapp.com/horizontal_slider");

        getActions().dragAndDropBy(getDriver().findElement(By.xpath("//input[@type='range']")), 10, 0).perform();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[@id='range']")).getText(), "3");
    }

    @Test
    public void testLogIn() {
        getDriver().get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(getDriver().findElement(
                By.tagName("p")).getText().contains("Congratulations! You must have the proper credentials"));
    }

    @Test

    public void testAlertButton() {
        getDriver().get("https://the-internet.herokuapp.com/javascript_alerts");

        getDriver().findElement(By.xpath("//button[@onclick='jsAlert()']")).click();

        getDriver().switchTo().alert().accept();

        String result = getDriver().findElement(By.xpath("//p[@id='result']")).getText();

        Assert.assertEquals(result, "You successfully clicked an alert");
    }

    @Test
    public void testYearsSliderTBank() {
        getDriver().get("https://www.tbank.ru/loans/cash-loan/realty/form/autoloan/");

        getWait5().until(d -> getDriver().getTitle() != null);

        WebElement slider = getDriver().findElement(By.xpath("//div[@data-field-name='cashloan_calculator_term_field']//div[@data-qa-type='uikit/Draggable']"));

        getActions().dragAndDropBy(slider, 100, 0).perform();

        WebElement element = getDriver().findElement(By.xpath("//div[@data-field-name='cashloan_calculator_term_field']//input[@data-qa-type ='uikit/inlineInput.input' ]"));
        String inputValue = element.getAttribute("value");

        Assert.assertEquals(inputValue, "15\u00a0лет");
    }

    @Test
    public void testGunShopEntranceForm() {
        getDriver().get("https://dld-vip.com/");
        getDriver().findElement(By.xpath(
                "//button[contains (@class,'TermsDialog_agreeButton__la1Q4')][contains(@type,'button')]")).click();
        getDriver().findElement(By.xpath(
                "//div[@aria-controls=':r0:']")).click();
        getDriver().findElement(By.xpath(
                "//li[contains (@class, 'MuiButtonBase-root MuiMenuItem-root')][contains (@data-value,'05')]")).click();
        getDriver().findElement(By.xpath(
                "//div[@aria-controls=':r1:']")).click();
        getDriver().findElement(By.xpath(
                "//li[contains (@class, 'MuiButtonBase-root MuiMenuItem-root')][contains (@data-value,'05')]")).click();
        getDriver().findElement(By.xpath(
                "//div[@aria-controls=':r2:']")).click();
        getDriver().findElement(By.xpath(
                "//li[@data-value='1954']")).click();
        getDriver().findElement(By.xpath(
                "//button[contains (@class,'MuiButton-contained')][contains(@type,'button')]")).click();
        String welcomeText = getDriver().findElement(By.xpath(
                "//div[@class='FeaturedItems_heroText__ck_yf FeaturedItems_invertColor__Hk_X3']//h3")).getText();

        Assert.assertEquals(welcomeText, "Come see our Knife Room");
    }

    @Test
    public void testFileUpload() {
        final String imageFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\uploadFiles\\java.png";

        getDriver().get("https://the-internet.herokuapp.com/upload");
        getDriver().findElement(By.id("file-upload")).sendKeys(imageFilePath);

        getDriver().findElement(By.id("file-submit")).click();
        getDriver().findElement(By.xpath("//div[@id='content']//h3")).getText();

        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//div[@id='content']//h3")).getText(),
                "File Uploaded!");
    }

    @Test
    public void testOrderFormCopiruem() {
        getDriver().get("https://copyruem.ru/online-calculator");

        Select format = new Select(getDriver().findElement(
                By.xpath("//select[@id='input_1620735450517']")));
        format.selectByValue("А0");

        Select color = new Select(getDriver().findElement(
                By.xpath("//select[@id='input_1620735508057']")));
        color.selectByValue("Цветная, заливка до 100%");

        Select folding = new Select(getDriver().findElement(
                By.xpath("//select[@id='input_1620735646958']")));
        folding.selectByValue("В формат А4");

        getDriver().findElement(By.xpath("//input[@id='input_1620735722133']")).clear();
        getDriver().findElement(By.xpath("//input[@id='input_1620735722133']")).sendKeys("100");

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[@data-calc-expr='format*count']")).getText(),
                "53000");
    }

    @Test
    public void testMonthlyPayment() {
        getDriver().get("https://www.tbank.ru/loans/cash-loan/realty/form/autoloan/");

        getWait5().until(d -> getDriver().getTitle() != null);

        getWait10().until(ExpectedConditions.visibilityOf(getDriver().findElement(
                By.xpath("//div[@data-field-name='cashloan_calculator_amount_field']//input[@data-qa-type ='uikit/inlineInput.input' ]"))));

        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)");

        getActions().dragAndDropBy(getDriver().findElement(By.xpath("//div[@aria-valuemin='200000']")), -77, 0).perform();
        By creditSum = By.xpath("//div[2]/div/div/div/div//div[2]/div/div/div[1]/div[2]/div");
        String actualCreditSum = getWait10().until(ExpectedConditions.visibilityOfElementLocated(creditSum)).getText();

        getActions().dragAndDropBy(getDriver().findElement(By.xpath("//div[@aria-valuenow='5']")), 50, 0).perform();
        By creditTerm = By.xpath("//div/div/div/div/div[2]/div/div/div[2]/div[2]/div");
        String actualTerm = getWait10().until(ExpectedConditions.visibilityOfElementLocated(creditTerm)).getText();

        By monthlyPayment = By.xpath("(//div[contains(text(),'15 550 ₽')])[1]");
        String actualPayment = getWait10().until(ExpectedConditions.visibilityOfElementLocated(monthlyPayment)).getText();

        getSoftAssert().assertEquals(actualCreditSum, "700 000 ₽");
        getSoftAssert().assertEquals(actualTerm, "8 лет");
        getSoftAssert().assertEquals(actualPayment, "15 550 ₽");

        getSoftAssert().assertAll();
    }
}