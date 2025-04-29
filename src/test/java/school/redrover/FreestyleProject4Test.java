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
    public void testCheckDiscardOldBuilds() {
        int expectedClicks = 8;
        int logLimit = 5;

        TestUtils.createFreestyleProject(getDriver(), JOB_NAME);
        getDriver().findElement(By.xpath("//label[contains(text(),'Discard old builds')]")).click();
        getDriver().findElement(By.name("_.numToKeepStr")).sendKeys(String.valueOf(logLimit));
        getDriver().findElement(By.name("Submit")).click();

        WebElement buildButton = getDriver().findElement(
                By.xpath("//a[@href='/job/Test%20item/build?delay=0sec']"));

        for (int i = 0; i < expectedClicks; i++) {
            buildButton.click();
            getWait10().until(ExpectedConditions.numberOfElementsToBe(
                    By.className("app-builds-container__item"),
                    Math.min(i + 1, logLimit)));
        }

        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.className("app-builds-container")));
        List<WebElement> entries = getDriver().findElements(By.className("app-builds-container__item"));

        assertEquals(entries.size(), logLimit);
    }
}
