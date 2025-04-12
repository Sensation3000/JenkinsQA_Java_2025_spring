package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class NewItem5Test extends BaseTest {

    @Test
    public void testItemsList() {
        List<String> expectedItemTypesTextList = List.of(
                "Freestyle project",
                "Pipeline",
                "Multi-configuration project",
                "Folder",
                "Multibranch Pipeline",
                "Organization Folder");

        TestUtils.waitForHomePageLoad(this);

        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        List<WebElement> webElementList = getDriver().findElements(By.xpath("//li[@role='radio']//span"));

        List<String> itemTypesTextList = new ArrayList<>();

        for (WebElement webElement: webElementList) {
            itemTypesTextList.add(webElement.getText());
        }

        Assert.assertEquals(itemTypesTextList, expectedItemTypesTextList);
    }
}
