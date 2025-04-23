package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickAddDescriptionButton() {
        getDriver().findElement(By.id("description-link")).click();

        return this;
    }

    public boolean isDescriptionFieldDisplayed() {
        return getDriver().findElement(By.name("description")).isDisplayed();
    }

    public HomePage sendDescription(String text) {
        getDriver().findElement(By.cssSelector("#description > form > div.jenkins-form-item.tr > div.setting-main.help-sibling > textarea"))
                .sendKeys(text);

        return this;
    }

    public HomePage clickSaveDescriptionButton() {
        getDriver().findElement(By.cssSelector("#description > form > div:nth-child(3) > button"))
                .click();

        return this;
    }

    public String getDescriptionText() {
        return getDriver().findElement(By.cssSelector("#description > div:nth-child(1)")).getText();
    }

    public String getWelcomeMessage(){

        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//h1"))))
                .getText();
    }

    public NewItemPage createJob() {
        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();

        return new NewItemPage(getDriver());
    }

    public NewItemPage clickNewItemOnLeftSidePanel() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='New Item']/ancestor::span[@class='task-link-wrapper ']"))).click();

        return new NewItemPage(getDriver());
    }

    public FreestyleProjectPage clickOnJobInListOfItemsOnHP(String nameItem) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td/a[@href='job/" + nameItem.replace(" ", "%20") + "/']"))).click();

        return new  FreestyleProjectPage(getDriver());
    }

    public FreestyleProjectPage clickOnJobInListOfItems(String nameItem) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nameItem + "']"))).click();

        return new FreestyleProjectPage(getDriver());
    }

    public String getNameFreestyleProjectText() {

        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='jenkins-table__link model-link inside']"))).getText();
    }

    public AccountSettingsPage goToAccountSettingsPage() {
        getDriver().findElement(By.xpath("//div[@class='login page-header__hyperlinks']/a[@class='model-link']")).click();

        return new AccountSettingsPage(getDriver());
    }

    public ManageJenkinsPage clickManageJenkinsOnLeftSidePanel(){
        getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='/manage']"))).click();

        return new ManageJenkinsPage(getDriver());
    }

    public OrganizationFolderPage clickOnOrganizationFolderInListOfItems(String nameItem) {
        getWait5().until(ExpectedConditions.visibilityOf(getDriver()
                        .findElement(By.xpath("//span[text()='" + nameItem + "']")))).click();

        return new OrganizationFolderPage(getDriver());
    }

    public MultibranchProjectPage clickOnMultibranchJobInListOfItems(String nameItem) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + nameItem + "']"))).click();

        return new MultibranchProjectPage(getDriver());
    }

    public PipelineConfigurationPage createNewPipeline(String projectName) {

        getDriver().findElement(By.xpath("//span[text()='New Item']/ancestor::span[@class='task-link-wrapper ']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        return new PipelineConfigurationPage(getDriver());
    }

    public BuildHistoryPage clickBuildHistoryTab() {
       getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();

       return new BuildHistoryPage(getDriver());
    }

    public List<String> getProjectNameList() {
        return getDriver().findElements(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).stream()
                .map(WebElement::getText).toList();
    }

    public NewItemPage clickNewItem() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();

        return new NewItemPage(getDriver());
    }
}
