package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class CreateUserTest extends BaseTest {

    @Test
    public void testNavigationToUserSection() {
        getWait5().until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='Manage Jenkins']/preceding-sibling::span")))
                .click();

        TestUtils.scrollAndClickWithJS(getDriver(),
                getWait5().until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//dt[text()='Users']"))));

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.tagName("h1"))).getText().substring(0, 5), "Users");
        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(text(), 'Create User')]"))).isEnabled());
    }
}
