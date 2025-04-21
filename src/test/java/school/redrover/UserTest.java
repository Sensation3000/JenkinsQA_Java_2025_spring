package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.AccountSettingsPage;
import school.redrover.page.HomePage;

public class UserTest extends BaseTest {

    @Test
    public void testEditDescriptionField() {
        final String descriptionText = "User Description";

        AccountSettingsPage page = new HomePage(getDriver())
                .goToAccountSettingsPage()
                .goToAccountPage()
                .sendDescription(descriptionText)
                .clickSaveDescriptionButton();

        String actualDescription = page.getDescription();

        Assert.assertEquals(actualDescription, descriptionText);

        page
                .goToAccountPage()
                .clearDescription();
    }
}
