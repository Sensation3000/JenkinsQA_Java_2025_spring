package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.FreestyleConfigurationPage;
import school.redrover.page.HomePage;

import java.util.List;

public class FreestyleProjectConfigurationTest extends BaseTest {
    private final String projectName = "Freestyle";

    @Test
    public void testDisableProject() {
        TestUtils.createFreestyleProject(getDriver(), projectName);

        String warningMessage = new FreestyleConfigurationPage(getDriver())
                .clickEnableDisableToggle()
                .clickSave()
                .getDisabledWarningMessageText();

        Assert.assertEquals(warningMessage, "This project is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testWarningMessageIsDisplayedAfterDisableProject() {
        boolean isWarningMessageDisplayed = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName)
                .isWarningMessageDisplayed();

        Assert.assertTrue(isWarningMessageDisplayed, "Warning message should be visible when the project is disabled.");
    }

    @Test(dependsOnMethods = "testWarningMessageIsDisplayedAfterDisableProject")
    public void testWarningMessageDisappearsAfterEnableProject() {
        List<WebElement> warningMessageList = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName)
                .clickEnableButton()
                .getWarningMessageList();

        Assert.assertTrue(warningMessageList.isEmpty(), "Warning message should disappear, but it is still present.");
    }

    @Test(dependsOnMethods = "testWarningMessageDisappearsAfterEnableProject")
    public void testEnableProject() {
        String toggleStatus = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName)
                .clickConfigure()
                .getToggleStatus();

        Assert.assertEquals(toggleStatus, "Enabled");
    }
}
