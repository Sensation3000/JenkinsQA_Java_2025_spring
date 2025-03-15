package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class GroupCodeCraftTest {

    @Test
    public void testSwagLabs() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com");

        String title = driver.getTitle();
        assertEquals(title, "Swag Labs");

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement backpack = driver.findElement
                (By.id("add-to-cart-sauce-labs-backpack"));
        backpack.click();

        WebElement removeBackpack = driver.findElement
                (By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory ']"));
        String getRemoveBackpackText = removeBackpack.getText();
        assertEquals(getRemoveBackpackText, "Remove");
        String color = removeBackpack.getCssValue("border");
        int rgbIndex = color.indexOf("rgb");
        assertEquals(color.substring(rgbIndex), "rgb(226, 35, 26)");

        WebElement cart = driver.findElement
                (By.xpath("//span[@class='shopping_cart_badge']"));
        String cartSize = cart.getText();
        assertEquals(cartSize, "1");

        WebElement jacket = driver.findElement
                (By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']"));
        jacket.click();

        assertEquals(cart.getText(), "2");

        cart.click();

        WebElement firstItem = driver.findElement
                (By.xpath("//a[@id='item_4_title_link']/div"));
        String firstItemText = firstItem.getText();
        assertEquals(firstItemText, "Sauce Labs Backpack");

        WebElement secondItem = driver.findElement
                (By.xpath("//a[@id='item_5_title_link']/div"));
        String secondItemText = secondItem.getText();
        assertEquals(secondItemText, "Sauce Labs Fleece Jacket");

        String firstPrice = driver.findElement
                        (By.xpath("//a[@id='item_4_title_link']/following-sibling::div[2]/div")).
                getText();
        firstPrice = firstPrice.substring(1);

        assertEquals(firstPrice, "29.99");
        double firstPriceDouble = Double.parseDouble(firstPrice);

        String secondString = driver.findElement
                        (By.xpath("//a[@id='item_5_title_link']/following-sibling::div[2]/div")).
                getText();
        secondString = secondString.substring(1);

        assertEquals(secondString, "49.99");
        double secondPriceDouble = Double.parseDouble(secondString);

        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        driver.findElement(By.xpath("//input[@id='first-name']")).
                sendKeys("Poopa");
        driver.findElement(By.xpath("//input[@id='last-name']")).
                sendKeys("Loopa");
        driver.findElement(By.xpath("//input[@id='postal-code']")).
                sendKeys("322228");
        driver.findElement(By.xpath("//input[@id='continue']")).
                click();

        assertEquals(driver.findElement(By.xpath("//div[@data-test='shipping-info-value']"))
                .getText(), "Free Pony Express Delivery!");

        double tax = (firstPriceDouble + secondPriceDouble) * 0.08;
        double totalPriceWithTax = firstPriceDouble + secondPriceDouble + tax;
        double totalPriceWithoutTax = firstPriceDouble + secondPriceDouble;

        double roundedTax = Math.round(tax * 100.0) / 100.0;
        double roundedPriceWithTax = Math.round(totalPriceWithTax * 100.0) / 100.0;
        double roundedPriceWithoutTax = Math.round(totalPriceWithoutTax * 100.0) / 100.0;

        assertEquals(driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"))
                .getText(), "Item total: $" + roundedPriceWithoutTax);

        assertEquals(driver.findElement(By.xpath("//div[@class='summary_tax_label']"))
                .getText(), "Tax: $" + roundedTax + "0");

        assertEquals(driver.findElement(By.xpath("//div[@data-test='total-label']"))
                .getText(), "Total: $" + roundedPriceWithTax);

        driver.findElement(By.xpath("//button[@id='finish']")).
                click();

        assertEquals(driver.findElement
                        (By.xpath("//h2")).getText(),
                "Thank you for your order!");

        driver.findElement(By.xpath("//button[@id='back-to-products']")).
                click();

        assertEquals(driver.findElement
                        (By.xpath("//div[@class='app_logo']")).
                getText(), "Swag Labs");

        Thread.sleep(2000);

        driver.quit();
    }
}