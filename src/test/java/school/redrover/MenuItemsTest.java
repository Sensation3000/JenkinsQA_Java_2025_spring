package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsTest extends BaseTest {

    @Test
    public void testCheckMenuItems() {
        final List<String> expectedMenuItems = new ArrayList<>(List.of("New Item", "Build History", "Manage Jenkins", "My Views"));

        List<WebElement> menuItems = getDriver().findElements(By.className("task-link-text"));
        List<String> actualMenuItems = new ArrayList<>();
        for (WebElement element : menuItems) {
            actualMenuItems.add(element.getDomProperty("innerText"));
        }

        Assert.assertEquals(actualMenuItems, expectedMenuItems, "Error!");
    }
}
