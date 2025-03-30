package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class NavigationTest extends BaseTest {
    @Test
    public void testBreadcrumbsToHomePage() throws InterruptedException {

        Thread.sleep(1000);

        String baseUrl = getDriver().getCurrentUrl();
        List<String> hrefAttributesList = new ArrayList<>();

        for (var webElement : getDriver().findElements(By.cssSelector(".task-link.task-link-no-confirm"))) {
            String href = webElement.getDomAttribute("href").substring(1);
            hrefAttributesList.add(href);
        }

        for (var element : hrefAttributesList) {
            String expectedURL = baseUrl + element;
            getDriver().findElement(By.xpath("//div[@id='side-panel']//a[@href ='/" + element +"']")).click();
            String actualURL = getDriver().getCurrentUrl();
            getDriver().findElement(By.xpath("//li[@class= 'jenkins-breadcrumbs__list-item']/a[@href='/']")).click();

            Assert.assertTrue(actualURL.contains(expectedURL));
            Assert.assertEquals(getDriver().getCurrentUrl(), baseUrl);
        }
    }
}