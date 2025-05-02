package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class FreestyleProjectConfigurationDescriptionTest extends BaseTest {
    private final String PROJECT_NAME = "FreestyleProject";
    private final String DESCRIPTION_TEXT = "Valid Freestyle Project Description";

    @Test
    public void testHidePreviewDescriptionOption() {
        TestUtils.createFreestyleProject(getDriver(), PROJECT_NAME);
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("general"))));
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.className("textarea-show-preview")).click();
        getDriver().findElement(By.className("textarea-hide-preview")).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.invisibilityOfElementWithText(
                By.className("textarea-hide-preview"), "Hide preview")));
        Assert.assertFalse(
                getDriver().findElement(By.className("textarea-preview")).isDisplayed());
    }
}
