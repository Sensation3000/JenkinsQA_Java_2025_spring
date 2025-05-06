package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.newitem.NewItemPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DashboardTest extends BaseTest {

    private static final String SUPERIOR_FOLDER_NAME = "Main folder for jobs";
    private static final String FOLDER_NAME = "Folder";
    private static final String JOB_NAME = "Freestyle job";
    private static final String JOB_IN_FOLDER_NAME = "Job in folder";
    private static final String DESCRIPTION = "Dashboard description";

    private List<String> expectedListOfJobs =
            new ArrayList<>(Arrays.asList(FOLDER_NAME, JOB_NAME, SUPERIOR_FOLDER_NAME));

    @Test
    public void testDashboardEnabled() {
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(1) > a"))
                .getText(), "Dashboard");
    }

    @Test
    public void testOpenManageJenkinsFromDashboard() {
        String manageJenkinsTitleText = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .getManageJenkinsTitleText();

        Assert.assertEquals(manageJenkinsTitleText, "Manage Jenkins");
    }

    @Test
    public void testPossibleToCreateJobFromDashboard() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickCreateJob();

        Assert.assertTrue(newItemPage.isNewItemPageOpened());
    }

    @Test
    public void testCancelJobCreationFromDashboard() {
        boolean isJobListEmpty = new HomePage(getDriver())
                .clickCreateJob()
                .getHeader()
                .clickLogoIcon()
                .isJobListEmpty();

        Assert.assertTrue(isJobListEmpty);
    }

    @Test
    public void testListJobsAndFolders() {

        new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(SUPERIOR_FOLDER_NAME)
                .selectFolderAndClickOk()
                .clickSave()
                .clickOnNewItemButton()
                .sendItemName(JOB_IN_FOLDER_NAME)
                .selectFreestyle()
                .getHeader()
                .clickLogoIcon()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(FOLDER_NAME)
                .selectFolderAndClickOk()
                .getHeader()
                .clickLogo()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(JOB_NAME)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo();

        List<String> actualListOfJobs = new ArrayList<>(new HomePage(getDriver()).getProjectNameList());

        if (!actualListOfJobs.isEmpty()) {
            Collections.sort(actualListOfJobs);
        }

        Assert.assertEquals(actualListOfJobs, expectedListOfJobs);
    }

    @Test(dependsOnMethods = "testListJobsAndFolders")
    public void testColumns() {

        Assert.assertEquals(new HomePage(getDriver()).getColumnNames(),
                List.of("S", "W", "Name\n  ↓", "Last Success", "Last Failure", "Last Duration"));
    }

    @Test(dependsOnMethods = {"testListJobsAndFolders", "testColumns"})
    public void testSortNameList() {

        HomePage homePage = new HomePage(getDriver());
        homePage.clickColumnNameInDashboardTable("Name");

        if (homePage.ascendingSorting("Name")) {
            Collections.sort(expectedListOfJobs);
        } else Collections.sort(expectedListOfJobs, Collections.reverseOrder());

        Assert.assertEquals(homePage.getProjectNameList(), expectedListOfJobs);

        homePage.clickColumnNameInDashboardTable("Name");
        if (homePage.ascendingSorting("Name")) {
            Collections.sort(expectedListOfJobs);
        } else Collections.sort(expectedListOfJobs, Collections.reverseOrder());

        Assert.assertEquals(homePage.getProjectNameList(), expectedListOfJobs);
    }

    @Test
    public void testAddDescription() {
        String descriptionText = new HomePage(getDriver())
                .clickAddDescriptionButton()
                .sendDescription(DESCRIPTION)
                .clickSaveDescriptionButton()
                .getDescriptionText();

        Assert.assertEquals(descriptionText, DESCRIPTION);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testEditDescription() {
        final String newDescription = "New dashboard description";

        String descriptionText = new HomePage(getDriver())
                .clickAddDescriptionButton()
                .sendDescription(newDescription)
                .clickSaveDescriptionButton()
                .getDescriptionText();

        Assert.assertEquals(descriptionText, newDescription);
    }

    @Test
    public void testEmptyBuildQueue() {
        HomePage homePage = new HomePage(getDriver());

        Assert.assertTrue(homePage.isBuildQueueDisplayed());
        Assert.assertEquals(homePage.getBuildQueueBlockText(), "No builds in the queue.");
    }
}
