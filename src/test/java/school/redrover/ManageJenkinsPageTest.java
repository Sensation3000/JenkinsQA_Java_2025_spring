package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.*;

public class ManageJenkinsPageTest extends BaseTest {

    private List<String> getExpectedSectionTitles() {
        return Arrays.asList(
                "System Configuration",
                "Security",
                "Status Information",
                "Troubleshooting",
                "Tools and Actions"
        );
    }

    private Map<String, List<String>> getExpectedSectionButtons() {
        Map<String, List<String>> sectionButtons = new HashMap<>();
        sectionButtons.put("System Configuration",
                List.of("System", "Tools", "Plugins", "Nodes", "Clouds", "Appearance"));
        sectionButtons.put("Security",
                List.of("Security", "Credentials", "Credential Providers", "Users"));
        sectionButtons.put("Status Information",
                List.of("System Information", "System Log", "Load Statistics", "About Jenkins"));
        sectionButtons.put("Troubleshooting",
                List.of("Manage Old Data"));
        sectionButtons.put("Tools and Actions",
                List.of("Reload Configuration from Disk", "Jenkins CLI", "Script Console", "Prepare for Shutdown"));
        return sectionButtons;
    }

    private List<WebElement> getManaJenkinsSections(WebDriver driver) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-section")));
        return driver.findElements(By.cssSelector(".jenkins-section"));
    }

    @Test
    public void MajorSectionsPresentOnManageJenkinsPage() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a[href='/manage']")).click();

        List<WebElement> sections = driver.findElements(By.cssSelector(".jenkins-section__title"));
        List<String> actualTitles = sections.stream().map(WebElement::getText).toList();

        List<String> expectedTitles = getExpectedSectionTitles();

        for (String title : expectedTitles) {
            Assert.assertTrue(actualTitles.contains(title),
                    "Expected section title not found: " + title);
        }
    }

    @Test
    public void testKeyButtonsVisible() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a[href='/manage']")).click();

        Map<String, List<String>> sectionButtons = getExpectedSectionButtons();
        List<WebElement> sections = getManaJenkinsSections(driver);

        for (WebElement section : sections) {
            String sectionTitle = section.findElement(By.tagName("h2")).getText().trim();

            if (sectionButtons.containsKey(sectionTitle)) {
                List<String> expectedButtons = sectionButtons.get(sectionTitle);
                for (String buttonText : expectedButtons) {
                    List<WebElement> matchingButtons = section.findElements(
                            By.xpath(".//*[text()='"+ buttonText +"']"));

                    Assert.assertFalse(matchingButtons.isEmpty(),
                            "Button '"+ buttonText +"' not found in section '"+ sectionTitle +"'");
                }
            }
        }
    }
}
