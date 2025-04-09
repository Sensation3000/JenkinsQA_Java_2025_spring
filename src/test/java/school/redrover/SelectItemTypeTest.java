package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class SelectItemTypeTest extends BaseTest {
    @Test
    public void testItemsTypeListIsNotEmpty() {

        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();
        List<WebElement> listOfItems = getDriver().findElements(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li"));
        List<WebElement> listOfFolders = getDriver().findElements(By.xpath("//div[@id='j-add-item-type-nested-projects']/ul/li"));
        List<WebElement> listOfJobs = new ArrayList<>();
        listOfJobs.addAll(listOfItems);
        listOfJobs.addAll(listOfFolders);

        Assert.assertNotEquals(listOfJobs.size(), 0);
    }

}
