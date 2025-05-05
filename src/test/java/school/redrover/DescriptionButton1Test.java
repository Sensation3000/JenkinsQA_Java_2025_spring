package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import school.redrover.page.HomePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DescriptionButton1Test extends BaseTest {

    private static final String PROJECT_NAME = "Freestyle Project";
    private static final String DESCRIPTION_NAME = "description";

    private void addTextToForm (String text) {

        WebDriver driver = getDriver();
        WebElement inputText = driver.findElement(By.cssSelector("#description > form > div.jenkins-form-item.tr > div.setting-main.help-sibling > textarea"));
        inputText.sendKeys(text);

    }

    @Test
    public void testAddDescriptionButton() {
        WebDriver driver = getDriver();

        WebElement buttonElement = driver.findElement(By.cssSelector("#description-link"));
        buttonElement.click();
        String text = "new project";

        addTextToForm(text);

        WebElement buttonElementSave = driver.findElement(By.cssSelector("#description > form > div:nth-child(3) > button"));
        buttonElementSave.click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#description > div:nth-child(1)")).getText(), text);
    }

    @Test
    public void testAddDescriptionButtonPOM() {
        final String newDescription = "new description";

        String currentDescription = new HomePage(getDriver())
                .clickAddDescriptionButton()
                .sendDescription(newDescription)
                .clickSaveDescriptionButton()
                .getDescriptionText();

        Assert.assertEquals(currentDescription, newDescription);
    }

    @Test
    public void testAddDescription() {
        String description = new HomePage(getDriver())
                .clickNewItem()
                .sendItemName(PROJECT_NAME)
                .selectFreestyle()
                .clickOkButton()
                .clickSaveButton()
                .clickEditDescriptionButton()
                .sendDescription(DESCRIPTION_NAME)
                .clickSave()
                .getDescription();

        assertEquals(description, DESCRIPTION_NAME);
    }

    @Test
    public void testEditDescriptionButton() {

        WebDriver driver = getDriver();
        WebElement editButton = driver.findElement(By.cssSelector("#description-link"));
        editButton.click();

        WebElement inputForm = driver.findElement(By.cssSelector("#description > form > div.jenkins-form-item.tr > div.setting-main.help-sibling > textarea"));
        String oldText = inputForm.getText();
        inputForm.sendKeys(Keys.END);

        String newtext = "I am editing the description";
        addTextToForm(newtext);

        WebElement buttonElementSave = driver.findElement(By.cssSelector("#description > form > div:nth-child(3) > button"));
        buttonElementSave.click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#description > div:nth-child(1)")).getText(), oldText + newtext);
    }

    @Test
    public void testNewPipelIneCreation() {

        WebDriver driver = getDriver();
        WebElement addNew = driver.findElement(By.cssSelector("#tasks > div:nth-child(1) > span > a > span.task-icon-link > svg"));
        addNew.click();

        WebElement inputForm = driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[1]/div/input"));
        String namePipeline = "Pipeline 2025";
        inputForm.sendKeys(namePipeline);

        WebElement pressPipeLine = driver.findElement(By.cssSelector("#j-add-item-type-standalone-projects > ul > li.org_jenkinsci_plugins_workflow_job_WorkflowJob"));
        pressPipeLine.click();

        WebElement buttonOK = driver.findElement(By.xpath("//*[@id=\"ok-button\"]"));
        buttonOK.click();

        WebElement buttonSave = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[1]/div[6]/div/button[1]"));
        buttonSave.click();

        WebElement name = driver.findElement(By.cssSelector("#main-panel > div.jenkins-app-bar > div.jenkins-app-bar__content.jenkins-build-caption > h1"));
        Assert.assertEquals(name.getText(),namePipeline);
    }
}
