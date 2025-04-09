package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;


public class GroupCodeCraftTest extends BaseTest {

    @Test
    public void testCreatePipeline() {
        final String pipelineName = "NewPipeline";

        WebDriver driver = getDriver();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tasks']/div[1]/span/a"))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input"))).sendKeys(pipelineName);

        driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("ok-button"))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        String title = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("job-index-headline"))).getText();

        Assert.assertEquals(title, pipelineName, "Pipeline title is not correct");
    }

    @Test
    public void testAboutJenkins() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/span/a/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[4]/a/dl/dt")).click();
        String appName = driver.findElement(By.className("app-about-heading")).getText();
        String appVersion = driver.findElement(By.className("app-about-version")).getText();

        Assert.assertEquals(appName, "Jenkins");
        Assert.assertEquals(appVersion, "Version 2.492.2");
    }

    @Test
    public void testNameBuildHistory() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a.task-link[href='/view/all/builds']")).click();
        String buildText = driver.findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(buildText, "Build History of Jenkins");
    }

    @Test
    public void testShowProperties() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/section[4]/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[3]/div/button[1]")).click();

        WebElement vendor = driver.findElement(By.xpath("//*[.='java.specification.vendor']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", vendor);
        String javaVendor = driver.findElement(By.xpath("//*[.='java.specification.vendor']/following-sibling::td/div[2]")).getText();

        Assert.assertEquals(javaVendor, "Oracle Corporation");
    }

    @Test
    public void testItemOrgFolder() throws InterruptedException {
        final String nameOrgFolder = "Folder archive 01";

        getDriver().findElement(By.xpath(
                "//a[span[contains(@class, 'task-link-text') and text()='New Item']]")).click();
        getDriver().findElement(By.id("name")).sendKeys(nameOrgFolder);

        WebElement newItemOrgFolder = getDriver().findElement(By.xpath(
                "//li[.//label/span[text()='Organization Folder']]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", newItemOrgFolder);
        newItemOrgFolder.click();
        getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]")).click();

        getDriver().findElement(By.xpath("//label[@for='enable-disable-project']")).click();

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath(
                "//*[@id=\"main-panel\"]/h1")).getText(), nameOrgFolder);
        Assert.assertEquals(getDriver().findElement(By.xpath(
                        "//*[@id='disabled-message']")).getText(),
                "This Organization Folder is currently disabled");
    }

    @Test
    public void testNewItemOkButtonSelectType() throws InterruptedException {
        final String nameItem = "New Item 0.01.4";

        getDriver().findElement(By.xpath(
                "//a[span[contains(@class, 'task-link-text') and text()='New Item']]")).click();

        getWait5().
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//button[contains(@class, 'disabled')]")));

        WebElement findFreestyleProject = getDriver().findElement(By.xpath(
                "//li[@class='hudson_model_FreeStyleProject' and @aria-checked='false']"));
        WebElement findPipeline = getDriver().findElement(By.xpath(
                "//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob' and @aria-checked='false']"));
        WebElement findMultiConfigurationProject = getDriver().findElement(By.xpath(
                "//li[@class='hudson_matrix_MatrixProject' and @aria-checked='false']"));
        WebElement findFolder = getDriver().findElement(By.xpath(
                "//li[@class='com_cloudbees_hudson_plugins_folder_Folder' and @aria-checked='false']"));
        WebElement findMultibranchPipeline = getDriver().findElement(By.xpath(
                "//li[@class='org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject' and @aria-checked='false']"));
        WebElement findOrganizationFolder = getDriver().findElement(By.xpath(
                "//li[.//label/span[text()='Organization Folder']]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", findOrganizationFolder);
        findOrganizationFolder.click();
        findFolder.click();
        findFreestyleProject.click();
        findMultibranchPipeline.click();
        findMultiConfigurationProject.click();
        findPipeline.click();

        getWait5().
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//button[contains(@class, 'disabled')]")));
        getWait5().
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div[@class='input-validation-message']")));
        getDriver().findElement(By.id("name")).sendKeys(nameItem);
        Thread.sleep(3000);
        WebElement okButton = getDriver().findElement(By.xpath(
                "//button[text()='OK']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", okButton);
        okButton.click();

        TestUtils.scrollAndClickWithJS(getDriver(),
                                getWait10().until(ExpectedConditions.elementToBeClickable
                                        (By.xpath("//button[@name='Submit']"))));

        Assert.assertEquals(getWait5().
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div[@class='jenkins-app-bar__content jenkins-build-caption']"))).getText(), nameItem);
    }

    @Test
    public void testBuildHistoryMoreActionsButton() {
        WebDriver driver = getDriver();

        driver.findElement(By.cssSelector("a.task-link[href='/view/all/builds']")).click();
        driver.findElement(By.xpath("//button[@tooltip='More actions']")).click();

        String buttonIcon = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("button-icon-legend"))).getText();
        String buttonAtom = driver.findElement(By.xpath("//*[@id='tippy-1']/div/div/div/button[2]")).getText();

        Assert.assertEquals(buttonIcon, "Icon legend");
        Assert.assertEquals(buttonAtom, "Atom feed");
    }

    @Test
    public void testCreateMultibranch() throws InterruptedException {
        final String nameOfDisplay = "Name of test";

        getDriver().findElement(By.cssSelector(" a[href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Test");

        WebElement buttonMultibranchPipe =
                getDriver().findElement(By.cssSelector("[class$='MultiBranchProject']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", buttonMultibranchPipe);
        buttonMultibranchPipe.click();

        getDriver().findElement(By.id("ok-button")).click();

        WebElement sendText = getDriver().findElement(By.cssSelector("input[name='_.displayNameOrNull']"));
        sendText.sendKeys(nameOfDisplay);

        WebElement buttonSave =
                getDriver().findElement(By.cssSelector(" button[name='Submit']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", buttonSave);
        buttonSave.click();

        Assert.assertEquals(getDriver().findElement(By.xpath(
                "//*[@id='main-panel']/h1")).getText(), nameOfDisplay);
        Assert.assertEquals(getDriver().findElement(By.xpath(
                        "//*[@id='main-panel']/div[3]/div/section/h2")).getText(),
                "This folder is empty");
    }

    @Test
    public void testMoreActionsVisible() {
        Actions actions = new Actions(getDriver());

        getWait5().until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[text()='Build History']/parent::a")))
                .click();

        WebElement tooltip = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//button[@tooltip='More actions']")));
        actions.moveToElement(tooltip).perform();
        String moveToElement = tooltip.getDomAttribute("aria-describedby");

        WebElement sortHeader = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.className("sortheader")));
        actions.moveToElement(sortHeader).perform();
        String moveOutElement = tooltip.getDomAttribute("aria-describedby");

        assertNotNull(moveToElement);
        assertNull(moveOutElement);
    }

    @Test
    public void testNewItemFreestyleProject() throws InterruptedException {
        final String nameItem1 = "New test ssN ~!@#$%^&*()_+}{[]`-=/.,<>?;':|";
        final String nameItem2 = "New Freestyle Project ssV ~()_+}{`-=.' 110.01.9";
        final String description = "New test Description ssV ~!@#$%^&*()_+}{[]`-=/.,<>?;':|";

        getDriver().findElement(By.xpath(
                "//a[span[contains(@class, 'task-link-text') and text()='New Item']]")).click();

        getDriver().findElement(By.xpath(
                "//li[@class='hudson_model_FreeStyleProject' and @aria-checked='false']")).click();

        WebElement fieldItem = getDriver().findElement(By.id("name"));
        fieldItem.sendKeys(nameItem1);
        getWait5().
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div[@id='itemname-invalid']")));
        fieldItem.clear();
        fieldItem.sendKeys(nameItem2);

        WebElement okButton = getDriver().findElement(By.xpath(
                "//button[text()='OK']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", okButton);
        okButton.click();

        getWait10().
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//textarea[@name='description']"))).sendKeys(description);

        TestUtils.scrollAndClickWithJS(getDriver(),
                getWait10().until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//button[@name='Submit']"))));

        Assert.assertEquals(getWait5().
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div[@class='jenkins-app-bar__content jenkins-build-caption']"))).getText(), nameItem2);
    }
}
