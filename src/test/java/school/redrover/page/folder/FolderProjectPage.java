package school.redrover.page.folder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.newitem.NewItemPage;

import java.util.List;

public class FolderProjectPage extends BasePage {

    @FindBy(linkText = "Create a job")
    private WebElement newJobButton;

    @FindBy(xpath = "//*[@id='description']/div[1]")
    private WebElement description;

    @FindBy(xpath = "//*[@id='main-panel']/h1")
    private WebElement projectName;

    @FindBy(id = "view-message")
    private WebElement descriptionMessage;

    @FindBy(className = "h4")
    private WebElement folderStatus;

    @FindBy(id = "name")
    private WebElement itemNameField;

    @FindBy(css = "a[href*='configure']")
    private WebElement configureButtonLeftSidePanel;

    @FindBy(id = "description-link")
    private WebElement addDescriptionButton;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(name = "description")
    private WebElement descriptionEditBox;

    @FindBy(className = "jenkins-table__link")
    private WebElement subFolder;

    @FindBy(linkText = "New Item")
    private WebElement newItemButtonLeftSidePanel;

    @FindBy(css = ".jenkins-table__link > span:nth-child(1)")
    private List<WebElement> listOfProjects;

    public FolderProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {

        return projectName.getText();
    }

    public String getDescription() {
        return getWait5().until(ExpectedConditions.visibilityOf(descriptionMessage)).getText();
    }

    public String getFolderStatus() {
        return folderStatus.getText();
    }

    public NewItemPage clickOnCreateNewJobButton(){
        newJobButton.click();

        return new NewItemPage(getDriver());
    }

    public FolderProjectPage sendItemName(String name) {
        itemNameField.sendKeys(name);

        return this;
    }

    public FolderConfigurationPage clickConfigure() {
        configureButtonLeftSidePanel.click();

        return new FolderConfigurationPage(getDriver());
    }

    public FolderProjectPage clickAddDescriptionButton(){
        addDescriptionButton.click();

        return this;
    }

    public FolderProjectPage clickSaveButton(){
        saveButton.click();

        return  this;
    }

    public FolderProjectPage fillInDescriptionBox(String description){
        descriptionEditBox.sendKeys(description);

        return this;
    }

    public String getDescriptionSecondLine() {

        return description.getText();
    }

    public List<String> getProjectNameList() {

        return listOfProjects.stream().map(WebElement::getText).toList();
    }

    public String getItemIconTitleByJobName(String jobName) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//tr[@id='job_%s']/td[1]/div/*[name()='svg']".formatted(jobName))))
                .getDomAttribute("title");
    }

    public NewItemPage findNewItemAndClick() {
        newItemButtonLeftSidePanel.click();

        return new NewItemPage(getDriver());
    }

    public String getSubFolderName() {
        return subFolder.getText();
    }

    public FolderRenamePage clickRenameOnLeftSidePanel(String folderName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(folderName)))).click();

        return new FolderRenamePage(getDriver());
    }
}
