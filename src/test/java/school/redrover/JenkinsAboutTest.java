package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class JenkinsAboutTest extends BaseTest {

    @Test
    public void checkJenkinsAboutFunction() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement footerButton = driver.findElement(By.cssSelector("footer .jenkins_ver"));
        footerButton.click();

        List<WebElement> dropdownMenuOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".jenkins-dropdown a")));
        List<String> footerDropdown = dropdownMenuOptions.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        List<String> expectedTitles = Arrays.asList("About Jenkins", "Get involved", "Website");

        assertTrue(footerDropdown.containsAll(expectedTitles),
                "Footer dropdown menu options do not match expected footer names. Found: " + footerDropdown);

        WebElement aboutOption = dropdownMenuOptions.stream()
                .filter(option -> option.getText().trim().equals("About Jenkins"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("'About Jenkins' option not found in the dropdown"));

        aboutOption.click();

        List<WebElement> breadcrumbMenu = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".jenkins-breadcrumbs a")));

        List<String> menuOptions = breadcrumbMenu.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        List<String> expectedOptions = Arrays.asList("Dashboard", "Manage Jenkins", "About Jenkins");

        assertTrue(menuOptions.containsAll(expectedOptions),
                "Breadcrumb menu options do not match expected menu options. Found: " + menuOptions);
    }
}