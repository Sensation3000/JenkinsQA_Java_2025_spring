package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class NodeTest extends BaseTest {
    private static final String NODE_NAME = "New node name";
    private static final String ROOT_DIRECTORY = "c:\\jenkins";

    @Test
    public void testCreateNode() {
        List<String> listOfNodes = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickNodes()
                .clickNewNode()
                .sendNewNodeName(NODE_NAME)
                .selectPermanentAgent()
                .clickCreate()
                .sendRemoteRootDirectory(ROOT_DIRECTORY)
                .clickSave()
                .getNodesNameList();

        Assert.assertEquals(listOfNodes.get(1), NODE_NAME);
    }

    @Test(dependsOnMethods = "testCreateNode")
    public void testMarkNodeOffline() {
        String statusMessage = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickNodes()
                .clickNodeNameInTheList(NODE_NAME)
                .clickSetOffline()
                .clickSubmitMarkOffline()
                .getStatusMessage();

        Assert.assertEquals(statusMessage, "Disconnected by admin");
    }
}
