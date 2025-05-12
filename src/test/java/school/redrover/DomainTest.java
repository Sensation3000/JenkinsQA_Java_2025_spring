package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.credentials.NewDomainSystem;

import static org.testng.Assert.assertEquals;

public class DomainTest extends BaseTest {

    @Test
    public void testAddNewDomain(){
        final String name = "Test Domain";
        final String description = "Test domain description";

        NewDomainSystem newDomainSystem = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickCredentials()
                .clickSystem()
                .clickAddDomain()
                .createNewDomain(name, description);

        assertEquals(newDomainSystem.textDomainLink(name), name);
        assertEquals(newDomainSystem.textDomainDescription(description), description);
        assertEquals(newDomainSystem.textDomainHeader(), name);
    }
}
