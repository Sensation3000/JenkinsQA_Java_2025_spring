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
    public void testOpenDashboardDropdown() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement dashboardLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Dashboard')]")
        ));
        new Actions(getDriver()).moveToElement(dashboardLink).perform();
        List<WebElement> dropdowns = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("button.jenkins-menu-dropdown-chevron"), 1));

        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(dropdowns.get(1)));
        dropdownButton.click();
        WebElement manageJenkins = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.jenkins-dropdown__item[href='/manage']")
        ));
        new Actions(getDriver()).moveToElement(manageJenkins).perform();
        WebElement appearance = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.jenkins-dropdown__item[href='/manage/appearance']")
        ));
        appearance.click();
        List<WebElement> themes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("input[type='radio'][name$='.theme']")
        ));

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
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[name='Submit']")
        ));
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input#settings-search-bar")
        ));
    }
}