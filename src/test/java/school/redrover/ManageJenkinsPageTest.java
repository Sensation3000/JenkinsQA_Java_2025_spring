package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.testdata.TestDataProvider;

import java.util.*;

public class ManageJenkinsPageTest extends BaseTest {

    @Test
    public void testIfMainSectionsAreDisplayedOnManageJenkinsPage() {
        List<String> expectedSectionTitles = List.of(
                "System Configuration",
                "Security",
                "Status Information",
                "Troubleshooting",
                "Tools and Actions"
        );

        List<String> actualSectionTitles = new HomePage(getDriver())
                .clickOnManageJenkinsLink()
                .getMainSectionTitlesOnManageJenkinsPage();

        Assert.assertEquals(actualSectionTitles, expectedSectionTitles);
    }

    @Test(dataProvider = "subSectionTitles", dataProviderClass = TestDataProvider.class)
    public void testIfSubSectionTitlesAreDisplayed(int index, String[] expectedSubSectionTitles) {
        List<String> actualSubSectionTitles = new HomePage(getDriver())
                .clickOnManageJenkinsLink()
                .getSubSectionTitlesOnManageJenkinsPage(index);

        Assert.assertEquals(actualSubSectionTitles, Arrays.asList(expectedSubSectionTitles));
    }
}
