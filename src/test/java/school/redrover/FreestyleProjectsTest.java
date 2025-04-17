package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.FreestyleProjectPage;
import school.redrover.page.HomePage;

public class FreestyleProjectsTest extends BaseTest {

    @Test
    public void testCreateNewFreestyleProject() {

        final String freestyleName = "Freestyle";
        final String projectDescription = "This is a freestyleProject description";

        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .createJob()
                .sendItemName(freestyleName)
                .selectFreestyleAndClickOk()
                .sendDescription(projectDescription)
                .clickSave();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), freestyleName);
        Assert.assertEquals(freestyleProjectPage.getDescription(), projectDescription);
    }
}