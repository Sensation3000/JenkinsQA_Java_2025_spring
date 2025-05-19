package school.redrover.page.newitem;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;
import school.redrover.page.error.ErrorPage;
import school.redrover.page.folder.FolderConfigurationPage;
import school.redrover.page.freestyle.FreestyleConfigurationPage;
import school.redrover.page.multibranch.MultibranchConfigurationPage;
import school.redrover.page.multiconfiguration.MultiConfigurationConfigurePage;
import school.redrover.page.organizationfolder.OrganizationFolderConfigurePage;
import school.redrover.page.pipeline.PipelineConfigurationPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NewItemPage extends BasePage {

    @FindBy(id = "name")
    private WebElement itemName;

    @FindBy(css = ".hudson_model_FreeStyleProject")
    private WebElement freeStyleProject;

    @FindBy(id = "ok-button")
    private WebElement buttonOk;

    @FindBy(xpath = "//span[text()='Folder']")
    private WebElement folder;

    @FindBy(id = "itemname-invalid")
    private WebElement itemNameInvalidMessage;

    @FindBy(id = "from")
    private List<WebElement> fromOptionInputs;

    private String projectPageClass(Class<?> pageClass) {
        Map<Class<?>, String> pageProjectType = Map.of(
                FreestyleConfigurationPage.class, "Freestyle project",
                PipelineConfigurationPage.class, "Pipeline",
                MultiConfigurationConfigurePage.class, "Multi-configuration project",
                FolderConfigurationPage.class, "Folder",
                MultibranchConfigurationPage.class, "Multibranch Pipeline",
                OrganizationFolderConfigurePage.class, "Organization Folder");

        return pageProjectType.get(pageClass);
    }

    public NewItemPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNewItemPageOpened() {
        return getWait5().until(ExpectedConditions.visibilityOf(
                getDriver().findElement(By.id("add-item-panel")))).isDisplayed();
    }

    public NewItemPage sendItemName(String name) {
        itemName.sendKeys(name);

        return this;
    }

    public boolean isOkButtonEnabled() {
        return buttonOk.isEnabled();
    }

    public FreestyleConfigurationPage clickOkButton() {
        buttonOk.click();

        return new FreestyleConfigurationPage(getDriver());
    }

    public ErrorPage clickOkButtonWithError() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonOk));
        buttonOk.click();

        return new ErrorPage(getDriver());
    }

    public String getEmptyNameMessage() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-required"))).getText();
    }

    public PipelineConfigurationPage selectPipelineAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        buttonOk.click();

        return new PipelineConfigurationPage(getDriver());
    }

    public FolderConfigurationPage selectFolderAndClickOk() {
        TestUtils.scrollAndClickWithJS(getDriver(),folder);
        folder.click();
        buttonOk.click();

        return new FolderConfigurationPage(getDriver());
    }

    public String getNewItemPageHeaderText() {
        return getDriver().findElement(By.xpath("//h1[text()='New Item']")).getText();
    }

    public String getNewItemPageURL() {
        return getDriver().getCurrentUrl();
    }

    public NewItemPage selectFreestyle() {
        freeStyleProject.click();

        return this;
    }

    public FreestyleConfigurationPage selectFreestyleAndClickOk() {
        selectFreestyle();
        buttonOk.click();

        return new FreestyleConfigurationPage(getDriver());
    }

    public List<String> getItemTypesTextList() {
        List<String> itemTypesTextList = new ArrayList<>();
        List<WebElement> webElementList = getDriver().findElements(By.xpath("//li[@role='radio']//span"));

        for (WebElement webElement : webElementList) {
            itemTypesTextList.add(webElement.getText());
        }

        return itemTypesTextList;
    }

    public String getItemNameInvalidMessage() {
        return getWait10().until(ExpectedConditions.visibilityOf(itemNameInvalidMessage)).getText();
    }

    public String getCopyFromFieldText() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("add-item-copy"))).getText();
    }

    public NewItemPage selectMultiConfiguration() {
        getDriver().findElement(By.cssSelector(".hudson_matrix_MatrixProject")).click();

        return this;
    }

    public MultiConfigurationConfigurePage selectMultiConfigurationAndClickOk() {
        getDriver().findElement(By.cssSelector(".hudson_matrix_MatrixProject")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonOk)).click();

        return new MultiConfigurationConfigurePage(getDriver());
    }

    public NewItemPage selectMultibranchPipeline() {
        getDriver().findElement(By.cssSelector("li[class$='multibranch_WorkflowMultiBranchProject']")).click();

        return this;
    }

    public MultibranchConfigurationPage selectMultibranchPipelineAndClickOkWithJS() {
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.cssSelector("li[class$='multibranch_WorkflowMultiBranchProject']")));
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button")));
        TestUtils.scrollAndClickWithJS(getDriver(), getDriver().findElement(By.id("ok-button")));

        return new MultibranchConfigurationPage(getDriver());
    }

    public MultiConfigurationConfigurePage redirectToMultiConfigurationConfigurePage() {
        TestUtils.scrollAndClickWithJS(getDriver(), buttonOk);
        getWait5().until(ExpectedConditions.urlContains("/job"));

        return new MultiConfigurationConfigurePage(getDriver());
    }

    public List<String> getAllItemsTypesLabels() {
        return getDriver().findElements(By.className("label"))
                .stream()
                .map(itemType -> itemType.getText()).collect(Collectors.toList());
    }

    public OrganizationFolderConfigurePage selectOrganizationFolderAndClickOk() {
        TestUtils.scrollAndClickWithJS(getDriver(),
                getDriver().findElement(By.xpath("//span[text()='Organization Folder']")));
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        return new OrganizationFolderConfigurePage(getDriver());
    }

    public List<String> getJobsDescriptions() {
        return getDriver().findElements(By.className("desc"))
                .stream()
                .map(jobDescription -> jobDescription.getText()).collect(Collectors.toList());
    }

    public List<String> getAllProjectTypeTitles() {
        return Arrays.asList(
                        "//span[@class][text()='Freestyle project']",
                        "//span[@class][text()='Pipeline']",
                        "//span[@class][text()='Multi-configuration project']",
                        "//span[@class][text()='Folder']",
                        "//span[@class][text()='Multibranch Pipeline']",
                        "//span[@class][text()='Organization Folder']"
                ).stream()
                .map(xpath -> getDriver().findElement(By.xpath(xpath)).getText())
                .toList();
    }

    public NewItemPage sendTextCopyForm(String text) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("from"))).sendKeys(text);

        return this;
    }

    public String getAutocompleteSuggestionText() {
        return getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("tippy-7")))
                .getText();
    }

    public boolean isListItemHighlighted(String itemLabel) {
        WebElement itemType = getDriver().findElement(By.xpath(String.format("//span[text()='%s']", itemLabel)));
        WebElement listItem = itemType.findElement(By.xpath("./ancestor::li"));

        return listItem.getDomAttribute("class").contains("active");
    }

    public String getErrorMessageOnEmptyField() {
        return getWait5().until
                (ExpectedConditions.visibilityOfElementLocated((By.cssSelector("#itemname-required.input-validation-message"))))
                .getText();
    }

    public boolean isCopyFromOptionInputDisplayed() {
        return !fromOptionInputs.isEmpty() && fromOptionInputs.get(0).isDisplayed();
    }

    public NewItemPage enterValueToCopyFromInput(String randomAlphaNumericValue) {
        WebElement copyFromInput = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.id("from")));

        TestUtils.scrollAndClickWithJS(getDriver(), copyFromInput);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].focus();", copyFromInput);
        copyFromInput.sendKeys(randomAlphaNumericValue);

        getWait10()
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".jenkins-dropdown.jenkins-dropdown--compact")))
                .click();

        return this;
    }

    public String getDropdownItemText() {
        return getWait10().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".jenkins-dropdown.jenkins-dropdown--compact"))).getText();
    }

    public NewItemPage selectItemByName(String projectName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='%s']".formatted(projectName)))).click();

        return this;
    }

    public NewItemPage selectPipeline() {
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        return this;
    }

    public String getItemNameInvalidMessageColor() {
        return getWait10().until(ExpectedConditions.visibilityOf(itemNameInvalidMessage))
                .getCssValue("color");
    }

    public NewItemPage selectItemType(Class<?> Page) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//span[@class][text()='%s']", projectPageClass(Page))))).click();

        return this;
    }

    public <T> T createNewItem(String name, Class<T> Page) {
        itemName.sendKeys(name);
        selectItemType(Page);
        buttonOk.click();

        try {
            return Page.getConstructor(WebDriver.class).newInstance(getDriver());
        } catch (Exception e) {
            throw new RuntimeException("Page instantiation failed for: " + Page.getSimpleName(), e);
        }
    }

    public boolean isUnsafeCharacterMessageDisplayed() {
        return getDriver().findElement(By.id("itemname-invalid")).isDisplayed();
    }
}
