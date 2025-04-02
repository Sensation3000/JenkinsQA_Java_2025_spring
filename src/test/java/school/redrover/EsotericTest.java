package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class EsotericTest extends BaseTest {

    @Test
    public void testVersionText() {

        WebDriver driver = getDriver();

        WebElement version_button = driver.findElement(By.cssSelector("button.jenkins_ver"));
        Assert.assertEquals(version_button.getText(), "Jenkins 2.492.2");
    }


    @Test
    public void testNaughtySpinnerWait() {

        WebDriver driver = getDriver();
        Actions builder = new Actions(driver);
        WebDriverWait wait30 = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement dashboard_crumb = driver.findElement(By.cssSelector("div#breadcrumbBar a.model-link"));
        WebElement dashboard_chevron = dashboard_crumb.findElement(By.cssSelector("button.jenkins-menu-dropdown-chevron"));

        builder.moveToElement(dashboard_crumb).moveToElement(dashboard_chevron).click().perform();
        wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("p.jenkins-spinner")));

        WebElement manageJenkins = driver.findElement(By.cssSelector("a.jenkins-dropdown__item span.jenkins-dropdown__item__chevron"));
        builder.moveToElement(manageJenkins).perform();

        WebElement aboutJenkins = driver.findElement(By.cssSelector("a[href='/manage/about']"));
        builder.moveToElement(aboutJenkins).click().perform();

        WebElement jenkinsVersion = driver.findElement(By.cssSelector("p.app-about-version"));
        Assert.assertEquals(jenkinsVersion.getText(), "Version 2.492.2");
    }
}
