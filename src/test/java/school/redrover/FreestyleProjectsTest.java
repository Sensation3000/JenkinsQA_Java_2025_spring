package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.FreestyleProjectPage;
import school.redrover.page.HomePage;

public class FreestyleProjectsTest extends BaseTest {

    private static final String FREESTYLE_NAME = "Freestyle Project";

    @Test
    public void testCreateNewFreestyleProject() {
        final String projectDescription = "This is a freestyleProject description";

        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .createJob()
                .sendItemName(FREESTYLE_NAME)
                .selectFreestyleAndClickOk()
                .addDescription(projectDescription)
                .clickSaveButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), FREESTYLE_NAME);
        Assert.assertEquals(freestyleProjectPage.getDescription(), projectDescription);
    }

    @Test(dependsOnMethods = "testCreateNewFreestyleProject")
    public void testEditDescriptionFreestyleProject() {
        final String newProjectDescription = "This is a NEW freestyleProject description";

        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(FREESTYLE_NAME)
                .clickEditDescriptionButton()
                .sendDescription(newProjectDescription)
                .clickSave();

        Assert.assertEquals(freestyleProjectPage.getDescription(), newProjectDescription);
    }

//    @Test(dependsOnMethods = "testCreateNewFreestyleProject")
//    public void testRenameFreestyleProject() {
//        final String newProjectName = "NEW Freestyle NAME";
//
//        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
//                .clickOnJobInListOfItems(FREESTYLE_NAME)
//                .clickLeftSideMenuRename()
//                .sendName(newProjectName)
//                .clickRename();
//
//        Assert.assertEquals(freestyleProjectPage.getProjectName(), newProjectName);
//    }
//
//    @Test(dependsOnMethods = "testCreateNewFreestyleProject")
//    public void testDeleteFreestyleProject() {
//
//        new HomePage(getDriver())
//                .clickOnJobInListOfItems(FREESTYLE_NAME)
//                .clickLeftSideMenuDelete()
//                .clickPopUpYesDeleteProject();
//
//        Assert.assertEquals(
//                getDriver().findElement(By.xpath("//h1[text()='Welcome to Jenkins!']")).getText(),
//                "Welcome to Jenkins!");
//    }
}
