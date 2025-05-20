package school.redrover.page.folder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.credentials.CredentialsPage;
import school.redrover.page.newitem.NewItemPage;

import java.util.List;

public class FolderProjectPage extends BasePage {

    @FindBy(linkText = "Create a job")
    private WebElement newItemButton;

    @FindBy (xpath = "//*[@id='description']/div[1]")
    private WebElement description;

    public FolderProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='main-panel']/h1"))).getText();
    }

    public String getDescription() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("view-message"))).getText();

    }

    public String getFolderStatus() {
        return getDriver().findElement(By.className("h4")).getText();
    }

    public NewItemPage clickOnNewItemButton(){
        newItemButton.click();

        return new NewItemPage(getDriver());
    }

    public FolderProjectPage sendItemName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public FolderConfigurationPage clickConfigure() {
        getDriver().findElement(By.cssSelector("a[href*='configure']")).click();

        return new FolderConfigurationPage(getDriver());
    }

    public FolderProjectPage clickAddDescriptionButton(){
        getDriver().findElement(By.id("description-link")).click();

        return this;
    }

    public FolderProjectPage clickSaveButton(){
        getDriver().findElement(By.name("Submit")).click();

        return  this;
    }

    public FolderProjectPage fillInDescriptionBox(String description){
        getDriver().findElement(By.name("description")).sendKeys(description);

        return this;
    }

    public String getDescriptionSecondLine() {

        return description.getText();
    }

    public List<String> getProjectNameList() {

        return getDriver().findElements(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).stream()
                .map(WebElement::getText).toList();
    }

    public String getItemIconTitleByJobName(String jobName) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//tr[@id='job_%s']/td[1]/div/*[name()='svg']".formatted(jobName))))
                .getDomAttribute("title");
    }

    public NewItemPage findNewItemAndClick() {
        getDriver().findElement(By.linkText("New Item")).click();

        return new NewItemPage(getDriver());
    }

    public String getSubFolderName() {
        return getDriver().findElement(By.className("jenkins-table__link")).getText();
    }

    public FolderRenamePage clickRenameOnLeftSidePanel(String folderName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(folderName)))).click();
        return new FolderRenamePage(getDriver());
    }

    public CredentialsPage clickLeftSideCredentials() {
        getWait5()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/TestFolder/credentials']")))
                .click();

        return new CredentialsPage(getDriver());
    }
}
