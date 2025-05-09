package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.freestyle.FreestyleProjectPage;
import school.redrover.page.HomePage;

public class BreadCrumbsDropDownMenuTest extends BaseTest {

    @Test
    public void testVerifyDropDownMenuWithLeftSideMenuWithoutStatus() {
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName("New Freestyle Project")
                .selectFreestyleAndClickOk()
                .addDescription("Freestyle Project Description")
                .clickSaveButton()
                .clickProjectBreadcrumbsDropDownMenu();

        Assert.assertEquals(
                freestyleProjectPage.getDropDownMenuItemsText(),
                freestyleProjectPage.getLeftSideMenuWithoutStatus());
    }
}
