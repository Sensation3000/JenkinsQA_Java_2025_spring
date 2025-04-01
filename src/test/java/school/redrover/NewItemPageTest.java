package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemPageTest extends BaseTest {

    private void openNewItemPage() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("#tasks>.task:first-child")).click();
    }

    @Test(testName = "Check New Item page heading")
    public void pageTitleTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "New Item");
    }

    @Test(testName = "Check the 'Name' field label")
    public void itemNameFieldLabelTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(By.xpath("//label[@for='name']")).getText(), "Enter an item name");
    }

    @Test(testName = "Check if 'Name' field is displayed")
    public void itemNameFieldIsDisplayedTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='name']")).isDisplayed());
    }

    @Test(testName = "Check the 'Type' block label")
    public void itemTypeLabelTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(
                By.xpath("//div[@class='jenkins-form-label']")).getText(), "Select an item type");
    }

    @Test(testName = "Check the 'Freestyle project' item type exist")
    public void freestyleProjectItemTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(
                By.xpath("//li[@class='hudson_model_FreeStyleProject']//span")).getText(), "Freestyle project");
    }

    @Test(testName = "Check the 'Pipeline' item type exist")
    public void pipelineItemTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(
                By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']//span")).getText(), "Pipeline"
        );
    }

    @Test(testName = "Check the 'Multi-configuration project' item type exist")
    public void multiConfigurationProjectItemTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(
                By.xpath("//li[@class='hudson_matrix_MatrixProject']//span")).getText(), "Multi-configuration project"
        );
    }

    @Test(testName = "Check the 'Folder' item type exist")
    public void folderItemTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(
                By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']//span")).getText(), "Folder"
        );
    }

    @Test(testName = "Check the 'Multibranch Pipeline' item type exist")
    public void multibranchPipelineItemTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(
                By.xpath("//li[@class='org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject']//span")).getText(), "Multibranch Pipeline"
        );
    }

    @Test(testName = "Check the 'Organization Folder' item type exist")
    public void organizationFolderItemTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertEquals(driver.findElement(
                By.xpath("//li[@class='jenkins_branch_OrganizationFolder']//span")).getText(), "Organization Folder"
        );
    }

    @Test(testName = "Check if OK button is disabled by default")
    public void okButtonIsDisabledTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        Assert.assertFalse(driver.findElement(By.id("ok-button")).isEnabled());
    }

    @Test(testName = "Check if OK button is disabled after 'Name' set")
    public void okButtonIsDisabledAfterNameSetTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Test folder");
        Assert.assertFalse(driver.findElement(By.id("ok-button")).isEnabled());
    }

    @Test(testName = "Check if OK button is enabled after 'Name' and 'Type' set")
    public void okButtonIsEnabledTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Test folder");
        driver.findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']//span")).click();
        Assert.assertTrue(driver.findElement(By.id("ok-button")).isEnabled());
    }

    @Test(testName = "Check if validation message is not displayed after 'Type' set")
    public void validationMessageIsDisabledAfterItemClickTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']//span")).click();
        Assert.assertEquals(driver.findElement(By.id("itemname-required")).getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test(testName = "Check if validation message is displayed after clicking OK button")
    public void validationMessageIsDisplayedAfterOkClickTest() {
        openNewItemPage();
        WebDriver driver = getDriver();

        driver.findElement(By.id("ok-button")).click();
        Assert.assertEquals(driver.findElement(By.id("itemname-required")).getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test(testName = "Check if 'Test folder' item is created after clicking OK button")
    public void newFolderItemIsCreatedTest() throws InterruptedException {
        openNewItemPage();
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Test folder");
        driver.findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']//span")).click();
        driver.findElement(By.id("ok-button")).click();
        Thread.sleep(100);
        driver.findElement(By.xpath("//a[text()='Dashboard']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".jenkins-table__link.model-link.inside")).getText(), "Test folder");
    }


}
