package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;

public class ChangeThemeNumber1Test extends BaseTest {

    @Test
    public void testOpenDashboardDropdown() throws InterruptedException {
        Actions actions = new Actions(getDriver());

        WebElement dashboardLink = getDriver().findElement(By.xpath("//a[contains(text(),'Dashboard')]"));

        actions.moveToElement(dashboardLink).perform();
        Actions actions1 = new Actions(getDriver());

        WebElement dashboard = getDriver().findElement(By.xpath("//a[contains(text(), 'Dashboard')]"));
        actions1.moveToElement(dashboard).perform();

        List<WebElement> dropdowns = getDriver().findElements(By.cssSelector("button.jenkins-menu-dropdown-chevron"));
        dropdowns.get(1).click();

        WebElement manageJenkins = getDriver().findElement(By.cssSelector("a.jenkins-dropdown__item[href='/manage']"));

        Actions actionss = new Actions(getDriver());
        actionss.moveToElement(manageJenkins).perform();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement appearance = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.jenkins-dropdown__item[href='/manage/appearance']")
        ));
        appearance.click();
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

        WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement searchInput = wait1.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input#settings-search-bar")
        ));

    }
}