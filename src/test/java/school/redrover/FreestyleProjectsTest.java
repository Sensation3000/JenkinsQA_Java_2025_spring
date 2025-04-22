package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.FreestyleProjectPage;
import school.redrover.page.HomePage;

public class FreestyleProjectsTest extends BaseTest {

    private String projectName = "Freestyle Project";
    private String updatedProjectName = "NEW Freestyle NAME";


    @Test
    public void testCreateNewFreestyleProject() {
        final String projectDescription = "This is a freestyleProject description";

        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .createJob()
                .sendItemName(projectName)
                .selectFreestyleAndClickOk()
                .addDescription(projectDescription)
                .clickSaveButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), projectName);
        Assert.assertEquals(freestyleProjectPage.getDescription(), projectDescription);
    }

    @Test(dependsOnMethods = "testCreateNewFreestyleProject")
    public void testEditDescriptionFreestyleProject() {
        final String newProjectDescription = "This is a NEW freestyleProject description";

        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName)
                .clickEditDescriptionButton()
                .sendDescription(newProjectDescription)
                .clickSave();

        Assert.assertEquals(freestyleProjectPage.getDescription(), newProjectDescription);
    }

    @Test(dependsOnMethods = "testEditDescriptionFreestyleProject")
    public void testRenameFreestyleProject() {
        final String newProjectName = "NEW Freestyle NAME";

        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickOnJobInListOfItems(projectName)
                .clickLeftSideMenuRename()
                .sendName(newProjectName)
                .clickRename();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), newProjectName);

        projectName = updatedProjectName;
    }

    @Test(dependsOnMethods = "testRenameFreestyleProject")
    public void testDeleteFreestyleProject() {

        new HomePage(getDriver())
                .clickOnJobInListOfItems(updatedProjectName)
                .clickLeftSideMenuDelete()
                .clickPopUpYesDeleteProject();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//h1[text()='Welcome to Jenkins!']")).getText(),
                "Welcome to Jenkins!");
    }
}
