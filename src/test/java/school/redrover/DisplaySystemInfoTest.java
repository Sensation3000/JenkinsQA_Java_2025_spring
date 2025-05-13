package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.systeminfo.SystemInfoPage;

public class DisplaySystemInfoTest extends BaseTest {

    @Test
    public void testSectionName() {
        String sectionName = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemInfo()
                .getSystemInfoTitleText();

        Assert.assertEquals(sectionName, "System Information");
    }

    @Test
    public void testShowSystemProperties() {
        String javaVendor = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemInfo()
                .clickShowValuesButtonSP()
                .findJavaVendorProperty()
                .getJavaVendorName();

        Assert.assertEquals(javaVendor, "Oracle Corporation");
    }

    @Test
    public void testHiddenEnvironmentalVariables() {
        String infoHiddenClass = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemInfo()
                .clickEnvironmentalVariablesSubpage()
                .getElementClass();

        String infoShownClass = new SystemInfoPage(getDriver())
                .clickShowValuesButtonEV()
                .getElementClass();

        Assert.assertEquals(infoHiddenClass, "app-hidden-info-reveal");
        Assert.assertEquals(infoShownClass, "app-hidden-info-reveal jenkins-hidden");
    }

    @Test
    public void testPluginsInfo() {
        String plugin1 = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemInfo()
                .clickPluginsSubpage()
                .getFirstPlugin();

        String plugin2 = new SystemInfoPage(getDriver())
                .getSecondPlugin();

        Assert.assertNotNull(plugin1);
        Assert.assertNotNull(plugin2);
    }

    @Test
    public void testMemoryUsageDataSelectTimespan() {
        String defaultMediumTimespanGraph = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickSystemInfo()
                .clickMemoryUsageSubpage()
                .getTimespanGraph();

        String shortTimespanGraph = new SystemInfoPage(getDriver())
                .setShortTimespan()
                .getTimespanGraph();

        String longTimespanGraph = new SystemInfoPage(getDriver())
                .setLongTimespan()
                .getTimespanGraph();

        Assert.assertNotEquals(defaultMediumTimespanGraph, shortTimespanGraph, longTimespanGraph);
    }
}
