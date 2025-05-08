package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;

public class ThemeTest extends BaseTest {
    private WebDriverWait wait5;

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    @Test
    public void testOpenDashboardDropdown() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));",
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//li[@class='jenkins-breadcrumbs__list-item']"))));
        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));",
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and @data-href='http://localhost:8080/']"))));
        js.executeScript("arguments[0].click();",
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and @data-href='http://localhost:8080/']"))));
        Thread.sleep(1000);

        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));",
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(@href,'manage') and contains(@class,'dropdown')]"))));
        js.executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));",
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[@href='/manage/appearance']"))));
        js.executeScript("arguments[0].click();",
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[@href='/manage/appearance']"))));
        Thread.sleep(1000);

        List<WebElement> themes = getDriver().findElements(By.cssSelector("input[type='radio'][name$='.theme']"));
        int selectedIndex = -1;
        for (int i = 0; i < themes.size(); i++) {
            if (themes.get(i).isSelected()) {
                selectedIndex = i;
                break;
            }
        }
        int nextIndex = (selectedIndex + 1) % themes.size();
        String nextId = themes.get(nextIndex).getAttribute("id");
        WebElement label = getDriver().findElement(By.cssSelector("label[for='" + nextId + "']"));
        label.click();
        WebElement saveButton = getDriver().findElement(By.cssSelector("button[name='Submit']"));
        saveButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#settings-search-bar")));
    }
}
