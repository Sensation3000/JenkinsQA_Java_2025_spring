package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

public class FreestyleProjectTest extends BaseTest {

    @Test
    public void testCreateNewFreestyleProject() {
        final String projectName = "Freestyle Project";

        HomePage homePage = new HomePage(getDriver());
        homePage
                .clickNewItemOnLeftSidePanel()
                .sendItemName(projectName)
                .selectFreestyleAndClickOk();
        TestUtils.clickOnJenkinsLogo(this);

        String actualItem = homePage.getNameFreestyleProjectText();
        Assert.assertEquals(actualItem, projectName);
    }
}