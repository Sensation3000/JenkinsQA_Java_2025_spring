package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SearchFunctionTest extends BaseTest {
    @Test
    public void getHelpTest() {
        WebDriver driver = getDriver();

        driver.findElement(By.id("button-open-command-palette")).click();
        driver.findElement(By.cssSelector("#search-results>a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.jenkins.io/doc/book/using/searchbox/");
    }
}
