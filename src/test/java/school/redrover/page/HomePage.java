package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;
import school.redrover.page.buildhistory.BuildHistoryPage;
import school.redrover.page.managejenkins.ManageJenkinsPage;
import school.redrover.page.newitem.NewItemPage;
import school.redrover.page.signIn.SignInPage;
import school.redrover.page.view.NewViewPage;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "#description > div:nth-child(1)")
    private WebElement descriptionText;

    @FindBy(xpath = "//span[text()='log out']")
    private WebElement logOutButton;

    @FindBy(xpath ="//a[@href='/view/all/newJob']")
    private WebElement newItemButtonOnLeftSidePanel;

    @FindBy(name = "description")
    private WebElement descriptionTextArea;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickAddDescriptionButton() {
        getDriver().findElement(By.id("description-link")).click();

        return this;
    }

    public boolean isJobListEmpty() {
        return getDriver().findElement(By.id("main-panel")).getText().contains("Welcome to Jenkins!");
    }

    public HomePage sendDescription(String text) {
        descriptionTextArea.clear();
        descriptionTextArea.sendKeys(text);

        return this;
    }

    public HomePage clickSaveDescriptionButton() {
        getDriver().findElement(By.cssSelector("#description > form > div:nth-child(3) > button"))
                .click();

        return this;
    }

    public String getDescriptionText() {
        return descriptionText.getText();
    }

     public String getNameProject() {

        return getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By
                        .xpath("//*[@id='job_My name']/td[3]/a"))))
                .getText();
    }

    public NewItemPage clickCreateJob() {
        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();

        return new NewItemPage(getDriver());
    }

    public NewItemPage clickNewItemOnLeftSidePanel() {
        getWait10().until(ExpectedConditions.elementToBeClickable(newItemButtonOnLeftSidePanel)).click();

        return new NewItemPage(getDriver());
    }

    public <T> T clickOnJobInListOfItems(String nameItem, T resultPage) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='%s']".formatted(nameItem)))).click();

        return resultPage;
    }

    public ManageJenkinsPage clickManageJenkinsOnLeftSidePanel() {
        getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='/manage']"))).click();

        return new ManageJenkinsPage(getDriver());
    }

    public BuildHistoryPage clickBuildHistoryTab() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();

        return new BuildHistoryPage(getDriver());
    }

    public List<String> getProjectNameList() {
        if (isJobListEmpty()) {
            return List.of();
        }

        return getDriver().findElements(By.cssSelector(".jenkins-table__link > span:nth-child(1)")).stream()
                .map(WebElement::getText).toList();
    }

    public NewViewPage clickNewView() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[aria-label='New View']"))).click();

        return new NewViewPage(getDriver());
    }

    public String getNameOfView(String viewName) {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/" + viewName + "/']"))).getText();
    }

    public boolean isJobDisplayed(String jobName) {
            return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='job_" + jobName + "']/td[3]/a/span"))).isDisplayed();
    }

    public NewItemPage clickOnNewItemLinkWithChevron(String projectName) {
        WebElement jobTableLink = getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector(String.format("a[href='job/%s/'].jenkins-table__link", projectName)))));

        new Actions(getDriver()).moveToElement(jobTableLink).perform();
        TestUtils.moveAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector(String.format("button[data-href$='job/%s/']", projectName))));
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector(".jenkins-dropdown a[href$='/newJob'")));

        return new NewItemPage(getDriver());
    }

    public String getProjectName() {
        return getWait5()
                         .until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("a[href*='job'].jenkins-table__link"))))
                         .getText();
    }

    public HomePage showDropdownOnHoverByJobName(String jobName) {
        By dropdownButtonLocator = By.xpath("//tr[@id='job_%s']//button".formatted(jobName));

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//tr[@id='job_%s']//a".formatted(jobName)))).perform();

        WebElement dropdownButton = getWait5().until(ExpectedConditions.elementToBeClickable(dropdownButtonLocator));
        TestUtils.moveAndClickWithJS(getDriver(), dropdownButton);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tippy-6 .jenkins-dropdown__item")));

        return this;
    }

    public List<WebElement> getDropdownOptionsList() {
        return getDriver().findElements(By.cssSelector("#tippy-6 .jenkins-dropdown__item"));
    }

    public boolean isOptionPresentedInDropdown(String optionName) {
        List<WebElement> dropdownOptionsList = getDropdownOptionsList();

        return dropdownOptionsList.stream()
                .anyMatch(webElement -> webElement.getText().contains(optionName));
    }

    public HomePage clickDeleteItemFromDropdown(String itemName) {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.xpath("//button[@href='/job/%s/doDelete']".formatted(itemName))))).click();

        return this;
    }

    public HomePage clickYesOnDeletionConfirmationPopup() {
        getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();

        return new HomePage(getDriver());
    }

    public SignInPage clickLogOutButton(){
        logOutButton.click();
        return new SignInPage(getDriver());
    }

    public List<String> getColumnNames() {

        return getDriver().findElements(By.xpath("//th/a")).stream()
                .map(WebElement::getText).toList();
    }

    public String getLogOutButtonText() {
        return logOutButton.getText();
    }

    public boolean isSvgIconDifferentBetweenProjects(String firstProjectName, String secondProjectName) {
        return getSvgTitle(firstProjectName).equals(getSvgTitle(secondProjectName));
    }

    private String getSvgTitle(String projectName) {
        String xpath = String.format("//a[@href='job/%s/']/ancestor::tr//*[name()='svg']", projectName);

        return getWait5()
                         .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)))
                         .getDomAttribute("title");
    }

    public void clickColumnNameInDashboardTable(String columnName){
        getDriver().findElement(By.xpath(String.format("//th/a[text()='%s']", columnName))).click();
    }

    public boolean ascendingSorting(String columnName) {
    String sign = getDriver().findElement(By.xpath(String.format("//th/a[text()='%s']", columnName)))
            .findElement(By.cssSelector("span.sortarrow")).getText();
        return sign.contains("↓");
    } 

    public String getJobIconTitle (String jobName) {

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[@id='job_%s']/td[1]/div/*[name()='svg']".formatted(jobName))))
                .getDomAttribute("title");
    }
}