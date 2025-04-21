package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

public class NewItem6Test extends BaseTest {

    @Ignore
    @Test
    public void testItemAlreadyExists() {
        final String freestyleProjectName = "New Project";

        HomePage homePage = new HomePage(getDriver());
        homePage
                .clickNewItemOnLeftSidePanel()
                .sendItemName(freestyleProjectName)
                .selectFreestyleAndClickOk();
        TestUtils.clickOnJenkinsLogo(this);

        String invalidItemMessage = homePage
                .clickNewItemOnLeftSidePanel()
                .sendItemName(freestyleProjectName)
                .getItemNameInvalidMessage();

        Assert.assertEquals(invalidItemMessage, "» A job already exists with the name ‘New Project’");
    }
}
