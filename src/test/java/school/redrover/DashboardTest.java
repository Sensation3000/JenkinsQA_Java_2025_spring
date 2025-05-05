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

    private List<String> expectedListOfJobs =
            new ArrayList<>(Arrays.asList(FOLDER_NAME,JOB_NAME,SUPERIOR_FOLDER_NAME));

    @Test
    public void testDashboardEnabled(){
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(1) > a"))
                .getText(), "Dashboard");
    }

    @Test
    public void testPossibleToCreateJobFromDashboard() {
        NewItemPage newItemPage = new HomePage(getDriver()).clickCreateJob();

        Assert.assertTrue(newItemPage.isNewItemPageOpened());
    }

    @Test
    public void testListJobsAndFolders(){

        new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(SUPERIOR_FOLDER_NAME)
                .selectFolderAndClickOk()
                .clickSave()
                .createJobInFolder()
                .sendItemName(JOB_IN_FOLDER_NAME)
                .selectFreestyleClickOkAndReturnToHomePage()
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

        if(!actualListOfJobs.isEmpty()) {
            Collections.sort(actualListOfJobs);}

        Assert.assertEquals(actualListOfJobs, expectedListOfJobs);
    }

    @Test(dependsOnMethods = "testListJobsAndFolders")
    public void testColumns() {

        Assert.assertEquals(new HomePage(getDriver()).getColumnNames(),
                List.of("S", "W", "Name\n  ↓", "Last Success", "Last Failure", "Last Duration"));
    }

    @Test(dependsOnMethods = {"testListJobsAndFolders","testColumns"})
    public void testSortNameList() {

        HomePage homePage = new HomePage(getDriver());
        homePage.clickColumnNameInDashboardTable("Name");

        if(homePage.ascendingSorting("Name")){
            Collections.sort(expectedListOfJobs);
        }
            else Collections.sort(expectedListOfJobs, Collections.reverseOrder());

        Assert.assertEquals(homePage.getProjectNameList(),expectedListOfJobs);

        homePage.clickColumnNameInDashboardTable("Name");
        if(homePage.ascendingSorting("Name")){
            Collections.sort(expectedListOfJobs);
        }
        else Collections.sort(expectedListOfJobs, Collections.reverseOrder());

        Assert.assertEquals(homePage.getProjectNameList(),expectedListOfJobs);
    }
}