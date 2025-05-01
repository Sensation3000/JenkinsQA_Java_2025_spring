package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardTest extends BaseTest {

    private static final String SUPERIOR_FOLDER_NAME = "Main folder for jobs";
    private static final String FOLDER_NAME = "Folder";
    private static final String JOB_NAME = "Freestyle job";
    private static final String JOB_IN_FOLDER_NAME = "Job in folder";


    @Test
    public void testDashboardEnabled(){
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(By.cssSelector("#breadcrumbs > li:nth-child(1) > a"))
                .getText(), "Dashboard");
    }

    @Test
    public void testListJobsAndFolders(){

        new HomePage(getDriver())
                .createJob()
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
        List<String> expectedListOfJobs =
                List.of(FOLDER_NAME,JOB_NAME,SUPERIOR_FOLDER_NAME);

        if(!actualListOfJobs.isEmpty()) {
            Collections.sort(actualListOfJobs);}

        Assert.assertEquals(actualListOfJobs, expectedListOfJobs);
    }
}