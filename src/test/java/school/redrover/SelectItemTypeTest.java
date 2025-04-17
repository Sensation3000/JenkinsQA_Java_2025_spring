package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void testItemsTypeList() {
        final List<String> items = Arrays.asList
                ("Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();

        List<WebElement> listOfItems = getDriver().findElements(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li/div/label"));
        List<WebElement> listOfFolders = getDriver().findElements(By.xpath("//div[@id='j-add-item-type-nested-projects']/ul/li/div/label"));

        List<WebElement> listOfJobs = new ArrayList<>();
        listOfJobs.addAll(listOfItems);
        listOfJobs.addAll(listOfFolders);

        List<String> listOfNames = listOfJobs.stream().map(WebElement::getText).toList();

        Assert.assertTrue(items.containsAll(listOfNames));
    }

    @Test
    public void testItemsTypeDescriptionExist() {
        final List<String> itemsDisc = Arrays.asList
                ("Classic, general-purpose job type that checks out from up to one SCM, executes build steps serially, followed by post-build steps like archiving artifacts and sending email notifications.",
                "Orchestrates long-running activities that can span multiple build agents. Suitable for building pipelines (formerly known as workflows) and/or organizing complex activities that do not easily fit in free-style job type.",
                "Suitable for projects that need a large number of different configurations, such as testing on multiple environments, platform-specific builds, etc.",
                "Creates a container that stores nested items in it. Useful for grouping things together. Unlike view, which is just a filter, a folder creates a separate namespace, so you can have multiple things of the same name as long as they are in different folders.",
                "Creates a set of Pipeline projects according to detected branches in one SCM repository.",
                "Creates a set of multibranch project subfolders by scanning for repositories.");

        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();

        List<WebElement> listOfItemsDisc = getDriver().findElements(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li//div[@class='desc']"));
        List<WebElement> listOfFoldersDisc = getDriver().findElements(By.xpath("//div[@id='j-add-item-type-nested-projects']/ul/li//div[@class='desc']"));

        List<WebElement> listOfJobsDisc = new ArrayList<>();
        listOfJobsDisc.addAll(listOfItemsDisc);
        listOfJobsDisc.addAll(listOfFoldersDisc);

        List<String> listOfNamesDisc = listOfJobsDisc.stream().map(WebElement::getText).toList();

        Assert.assertTrue(listOfNamesDisc.containsAll(itemsDisc));
    }
}
