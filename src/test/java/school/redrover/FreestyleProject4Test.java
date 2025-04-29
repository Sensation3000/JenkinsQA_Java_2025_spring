package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.freestyle.FreestyleProjectPage;


import java.util.List;
import static org.testng.Assert.*;


public class FreestyleProject4Test extends BaseTest {
    private static final String JOB_NAME = "Test item";

    @Test
    public void testCreateFreestyleProjectWithNoneSCM() {
        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//label[text()='None']"))).perform();
        getDriver().findElement(By.name("Submit")).click();
        WebElement result = getDriver().findElement(
                By.cssSelector("#main-panel > div.jenkins-app-bar > div.jenkins-app-bar__content.jenkins-build-caption > h1"));
        Assert.assertEquals(result.getText(), JOB_NAME);
    }
}

