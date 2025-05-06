package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.*;

public class AppearanceTest extends BaseTest {

    @Test
    public void testDarkTheme() {
        HomePage homePage = new HomePage(getDriver());
        String popUpSaveButtonText = homePage
                    .clickManageJenkinsOnLeftSidePanel()
                    .clickAppearanse()
                    .clickDarkSistemTheme()
                    .clickDefaultTheme()
                    .clickDarkTheme()
                    .clickSaveButton()
                    .getPopUpSaveButtonText();

        Assert.assertEquals(popUpSaveButtonText, "Saved");
    }
}
