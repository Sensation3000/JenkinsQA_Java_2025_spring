package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.multibranch.MultibranchProjectPage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;

import static org.testng.Assert.assertEquals;


public class MultibranchPipelineTest extends BaseTest {

    private static final String MULTIBRANCH_NAME = "Multibranch Pipeline Job Test";
    private static final String PROJECT_DESCRIPTION = "This is a NEW MultibranchPipeline description";

    @Test
    public void testCreate() {
        MultibranchProjectPage multibranchProjectPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendItemName(MULTIBRANCH_NAME)
                .selectMultibranchAndClickOk()
                .clickSaveButton();

        Assert.assertEquals(multibranchProjectPage.getProjectName(), MULTIBRANCH_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testAddDescription() {
        String actualDescription = new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_NAME, new MultibranchProjectPage(getDriver()))
                .goToConfigurationPage()
                .sendDescription(PROJECT_DESCRIPTION)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescription, PROJECT_DESCRIPTION);
    }

    @Test
    public void testCreateWithSpecialSymbols() {
        String[] specialCharacters = {"!", "%", "&", "#", "@", "*", "$", "?", "^", "|", "/", "]", "["};

        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        getDriver().findElement(By.cssSelector("[class$='MultiBranchProject']")).click();
        WebElement nameField = getDriver().findElement(By.xpath("//input[@name='name']"));

        for (String specChar : specialCharacters) {
            nameField.clear();
            nameField.sendKeys("Mult" + specChar + "branch");

            WebElement actualMessage = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("//div[@id='itemname-invalid']")));

            String expectMessage = "» ‘" + specChar + "’ is an unsafe character";
            Assert.assertEquals(actualMessage.getText(), expectMessage, "Message is not displayed");
        }
    }

    @Test(dependsOnMethods = "testTryCreateProjectExistName")
    public void testVerifySectionHasTooltip() {
        new HomePage(getDriver())
                .clickOnJobInListOfItems(MULTIBRANCH_NAME, new MultiConfigurationConfigurePage(getDriver()))
                .clickConfigure()
                .clickAdvanced();

        assertEquals(new FreestyleConfigurationPage(getDriver()).numberHelpTooltips(), 22);
    }

    @Test
    public void testTryCreateProjectExistName() {
        String errorMessage = new HomePage(getDriver())
                .clickNewItemOnLeftSidePanel()
                .sendItemName(MULTIBRANCH_NAME)
                .selectMultiConfigurationAndClickOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .clickNewItemOnLeftSidePanel()
                .sendItemName(MULTIBRANCH_NAME).selectMultiConfiguration()
                .getItemNameInvalidMessage();

        assertEquals(errorMessage, "» A job already exists with the name " + "‘" + MULTIBRANCH_NAME + "’");
    }
}
