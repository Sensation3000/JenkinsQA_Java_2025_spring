package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.folder.FolderConfigurationPage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.pipeline.PipelineConfigurationPage;
import school.redrover.testdata.TestDataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardTest extends BaseTest {

    private static final String SUPERIOR_FOLDER_NAME = "Main folder for jobs";
    private static final String FOLDER_NAME = "Folder";
    private static final String JOB_NAME = "Freestyle job";
    private static final String JOB_IN_FOLDER_NAME = "Job in folder";
    private static final String FAILED_JOB = "Pipeline job to fail";
    private static final String DESCRIPTION = "Dashboard description";
    private static List<String> ListReportsExpected = List.of();
    private static List<String> ListReportsActual1 = List.of();
    private static List<String> ListReportsActual2 = List.of();

    private List<String> expectedListOfJobs =
            new ArrayList<>(Arrays.asList(FOLDER_NAME, JOB_NAME, SUPERIOR_FOLDER_NAME));

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
                .clearDescription()
                .sendDescription(newDescription)
                .clickSaveDescriptionButton()
                .getDescriptionText();

        Assert.assertEquals(descriptionText, newDescription);
    }

    @Test(dependsOnMethods = "testEditDescription")
    public void testRemoveDescription() {
        boolean isDescriptionDisplayed = new HomePage(getDriver())
                .clickAddDescriptionButton()
                .clearDescription()
                .clickSaveDescriptionButton()
                .isDescriptionDisplayed();

        Assert.assertFalse(isDescriptionDisplayed);
    }

    @Test
    public void testCheckOptionsOfJenkinsVersionDropDown() {
        List<String> expectedTitles = Arrays.asList("About Jenkins", "Get involved", "Website");

        List<String> jenkinsVersionDropDownOptions = new HomePage(getDriver())
                .clickJenkinsVersionButton()
                .getJenkinsVersionDropDownOptions();

        Assert.assertEquals(jenkinsVersionDropDownOptions, expectedTitles);
    }

    @Test
    public void testDashboardEnabled() {
        Assert.assertTrue(new HomePage(getDriver()).enabledDashbord());
    }

    @Test
    public void testEmptyBuildQueue() {
        HomePage homePage = new HomePage(getDriver());

        Assert.assertTrue(homePage.isBuildQueueDisplayed());
        Assert.assertEquals(homePage.getBuildQueueBlockText(), "No builds in the queue.");
    }

    @Test
    public void testListJobsAndFolders() {
        List<String> actualListOfJobs = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(SUPERIOR_FOLDER_NAME, FolderConfigurationPage.class)
                .clickSave()
                .clickOnCreateJobButton()
                .sendItemName(JOB_IN_FOLDER_NAME)
                .selectFreestyle()
                .getHeader()
                .clickLogoIcon()
                .clickNewItemOnLeftSidePanel()
                .createNewItem(FOLDER_NAME, FolderConfigurationPage.class)
                .getHeader()
                .clickLogo()
                .clickNewItemOnLeftSidePanel()
                .createNewItem(JOB_NAME, FreestyleConfigurationPage.class)
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectNameList();

        Assert.assertEquals(actualListOfJobs, expectedListOfJobs);
    }

    @Test(dependsOnMethods = "testListJobsAndFolders")
    public void testColumns() {
        Assert.assertEquals(new HomePage(getDriver()).getColumnNames(),
                List.of("S", "W", "Name\n  ↓", "Last Success", "Last Failure", "Last Duration"));
    }

    @Test(dependsOnMethods = {"testColumns"})
    public void testSortNameList() {
        HomePage homePage = new HomePage(getDriver());

        ListReportsActual1 = homePage
                .clickColumnNameInDashboardTable("Name")
                .getReversedProjectNameList();

        ListReportsActual2 = homePage
                .clickColumnNameInDashboardTable("Name")
                .getProjectNameList();

        Assert.assertEquals(ListReportsActual1, expectedListOfJobs);
        Assert.assertEquals(ListReportsActual2, expectedListOfJobs);
    }

    @Test(dataProvider = "script", dataProviderClass = TestDataProvider.class, dependsOnMethods = {"testSortNameList"})
    public void testFailedJobDetails(String script){
        String lastFailure = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .createNewItem(FAILED_JOB, PipelineConfigurationPage.class)
                .setScript(script)
                .clickSave()
                .clickBuildNow()
                .getHeader()
                .clickLogo()
                .getJobLastFailure(FAILED_JOB);
        String lastSuccess = new HomePage(getDriver()).getJobLastSuccess(FAILED_JOB);

        Pattern pattern = Pattern.compile("#\\d+");
        Matcher matcher = pattern.matcher(lastFailure);

        Assert.assertTrue(lastFailure.contains("sec"));
        Assert.assertTrue(matcher.find());
        Assert.assertEquals(lastSuccess, "N/A");
    }

    @Test(dependsOnMethods = {"testFailedJobDetails"})
    public void testSortHealthReportColumnDashboard(){
        HomePage homePage = new HomePage(getDriver());

        ListReportsExpected = homePage
                .getListHealthReportFromDashboard();

        ListReportsActual1 = homePage
                .clickColumnNameInDashboardTable("W")
                .getListHealthReportFromDashboard();

        ListReportsActual2 = homePage
                .clickColumnNameInDashboardTable("W")
                .getReverseListHealthReportFromDashboard();

        Assert.assertEquals(ListReportsActual1, ListReportsExpected);
        Assert.assertEquals(ListReportsActual2, ListReportsExpected);
    }

    @Test(dependsOnMethods = {"testSortHealthReportColumnDashboard"})
    public void testSortStatusLastBuildColumnDashboard() {
        HomePage homePage = new HomePage(getDriver());

        ListReportsExpected = homePage
                .getListStatusLastBuildFromDashboard();

        ListReportsActual1 = homePage
                .clickColumnNameInDashboardTable("S")
                .getReversedListStatusLastBuildFromDashboard();

        ListReportsActual2 = homePage
                .clickColumnNameInDashboardTable("S")
                .getListStatusLastBuildFromDashboard();

        Assert.assertEquals(ListReportsActual1, ListReportsExpected);
        Assert.assertEquals(ListReportsActual2, ListReportsExpected);
    }

    @Test
    public void testOpenManageJenkinsFromDashboard() {
        String manageJenkinsTitleText = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .getManageJenkinsTitleText();

        Assert.assertEquals(manageJenkinsTitleText, "Manage Jenkins");
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
    public void testPossibleToCreateJobFromDashboard() {
        boolean isNewItemPageOpened = new HomePage(getDriver())
                .clickCreateJob()
                .isNewItemPageOpened();

        Assert.assertTrue(isNewItemPageOpened);
    }
}
