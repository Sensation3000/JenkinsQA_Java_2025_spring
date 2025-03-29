package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ThemeChangingTest extends BaseTest {

    @Test
    public void testDarkThemeSet() {
        getDriver().findElement(By.linkText("admin")).click();
        getDriver().findElement(By.linkText("Appearance")).click();
        getDriver().findElement(By.xpath("//input[@data-theme='dark']/ancestor::div[@class='app-theme-picker__item']")).click();
        getDriver().findElement(By.name("Submit")).click();

        WebElement bodyColor = getDriver().findElement(By.tagName("body"));
        WebElement textColor = getDriver().findElement(By.xpath("html[@data-theme='dark']"));

        Assert.assertEquals(bodyColor.getCssValue("background-color"), "rgba(31, 31, 35, 1)");
        Assert.assertEquals(textColor.getCssValue("color"), "rgba(250, 250, 255, 1)");
    }
}
