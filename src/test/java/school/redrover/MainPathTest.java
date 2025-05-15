package school.redrover;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.common.BasePage;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.testdata.TestDataProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class MainPathTest extends BaseTest {

    private final String message = "Path is wrong";

    @Test(dataProvider = "urlEndpoints", dataProviderClass = TestDataProvider.class)
    public <T extends BasePage> void testSideMenuTaskLinks(String linkText, String expectedUrlEndpoint, Class<T> pageClass)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        HomePage homePage = new HomePage(getDriver());
        boolean isPageContainsExpectedUrl =
                homePage.clickOnSideMenuTaskLink(linkText, homePage.getInstanceOfClass(pageClass))
                        .getCurrentUrl()
                        .contains(expectedUrlEndpoint);

        assertTrue(isPageContainsExpectedUrl);
    }

    @Test
    public void SetupaAgentShouldBeLinkToNewJobTest() {
        getDriver().findElement(By.xpath("//a[@href='computer/new']")).click();
        assertTrue(getDriver().getCurrentUrl().contains("/computer/new"),
                message);
    }
    @Test
    public void ConfugureACloudShouldBeLinkToCloudTest() {
        getDriver().findElement(By.xpath("//a[@href='cloud/']")).click();
        assertTrue(getDriver().getCurrentUrl().contains("/cloud"),
                message);
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