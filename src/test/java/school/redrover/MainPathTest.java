package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BasePage;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.testdata.TestDataProvider;

import java.lang.reflect.InvocationTargetException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class MainPathTest extends BaseTest {

    @Test(dataProvider = "urlEndpoints", dataProviderClass = TestDataProvider.class)
    public <T extends BasePage> void testHomePageLinks(String linkText, String expectedUrlEndpoint, Class<T> pageClass)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        boolean isPageContainsExpectedUrl = new HomePage(getDriver())
                .clickOnLink(linkText, TestUtils.getInstanceOfClass(getDriver(), pageClass))
                .getCurrentUrl()
                .contains(expectedUrlEndpoint);

        assertTrue(isPageContainsExpectedUrl);
    }

    @Test
    public void testLearnMoreAboutDistributedBuildsLink()  {
        HomePage homePage = new HomePage(getDriver());

        assertTrue(homePage.isLearnMoreAboutDistributedBuildsLinkEnabled());
        assertEquals(homePage.getLearnMoreAboutDistributedBuildsLinkText(), "Learn more about distributed builds");
    }
}
