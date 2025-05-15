package school.redrover;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.common.BasePage;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.testdata.TestDataProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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
    public void LearnShouldBeLinkToLearn()  {
        getDriver().findElement(By.xpath("//a[@target='_blank']")).click();
        getDriver().switchTo().window(new ArrayList<>(getDriver()
                .getWindowHandles())
                .get((new ArrayList<>(getDriver()
                        .getWindowHandles()).indexOf(getDriver()
                        .getWindowHandle()) + 1) % getDriver().getWindowHandles().size()));
        assertEquals(getDriver().getCurrentUrl(),
                "https://www.jenkins.io/doc/book/scaling/architecting-for-scale/#distributed-builds-architecture");
    }
}
