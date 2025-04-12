package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.Arrays;
import java.util.List;

public class ManageJenkinsPageTest extends BaseTest {

    @Test
    public void MajorSectionsPresentOnManageJenkinsPage() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a[href='/manage']")).click();

        List<WebElement> sections = driver.findElements(By.cssSelector(".jenkins-section__title"));
        List<String> actualTitles = sections.stream().map(WebElement::getText).toList();

        List<String> expectedTitles = Arrays.asList(
                "System Configuration",
                "Security",
                "Status Information",
                "Troubleshooting",
                "Tools and Actions"
        );

        for (String title : expectedTitles) {
            Assert.assertTrue(actualTitles.contains(title),
                    "Expected section title not found: " + title);
        }
    }
}
