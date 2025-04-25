package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import school.redrover.common.BasePage;
import school.redrover.common.TestUtils;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewItemPage extends BasePage {

    public NewItemPage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage sendItemName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public String getInputValue() {
        return getDriver().findElement(By.id("name")).getShadowRoot().findElement(By.cssSelector("div")).getText();
    }

    public String getAlertMessageText() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid"))).getText();
    }

    public String getEmptyNameMessage() {

        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-required"))).getText();
    }

    public PipelineConfigurationPage selectPipelineAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        return new PipelineConfigurationPage(getDriver());
    }

    public FolderConfigurationPage selectFolderAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        return new FolderConfigurationPage(getDriver());
    }

    public String getNewItemPageHeaderText() {
        return getDriver().findElement(By.xpath("//h1[text()='New Item']")).getText();
    }

    public String getNewItemPageURL() {
        return getDriver().getCurrentUrl();
    }

    public NewItemPage selectFreestyle() {
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();

        return this;
    }

    public FreestyleConfigurationPage selectFreestyleAndClickOk() {
        selectFreestyle();
        getDriver().findElement(By.id("ok-button")).click();

        return new FreestyleConfigurationPage(getDriver());
    }

    public List<String> getItemTypesTextList() {
        List<String> itemTypesTextList = new ArrayList<>();
        List<WebElement> webElementList = getDriver().findElements(By.xpath("//li[@role='radio']//span"));
        for (WebElement webElement: webElementList) {
            itemTypesTextList.add(webElement.getText());
        }
        return itemTypesTextList;
    }
  
    public String getItemNameInvalidMessage() {
        
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid"))).getText();
    }
  
    public String getCopyFromFieldText() {
      
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("add-item-copy"))).getText();
    }

    public MultiConfigurationConfigurePage selectMultiConfigurationAndClickOk() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[4]"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        return new MultiConfigurationConfigurePage(getDriver());
    }

    public MultibranchConfigurationPage selectMultibranchAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Multibranch Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        return new MultibranchConfigurationPage(getDriver());
    }

    public FolderConfigurationPage selectFolderAndClickOkWithJS() {
        TestUtils.scrollAndClickWithJS(getDriver(),
                getDriver().findElement(By.xpath("//span[text()='Folder']")));
        getDriver().findElement(By.id("ok-button")).click();

        return new FolderConfigurationPage(getDriver());
    }

    public String getItemTypeText(String itemType){
        return getDriver().findElement(By.xpath("//span[text()='" + itemType + "']")).getText();
    }

    public List<String> getAllItemsTypesLabels() {
        return getDriver().findElements(By.className("label"))
                          .stream()
                          .map(itemType -> itemType.getText()).collect(Collectors.toList());
    }

    public NewItemPage enterProjectNameAndSelect(String nameProject, String projectTypeText) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-input"))).sendKeys(nameProject);
        String xpath = String.format("//span[text()='%s']", projectTypeText);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("ok-button"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        return new NewItemPage(getDriver());
    }


    public NewItemPage selectFreestyleAndClickOkNoPageChange() {
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        return this;
    }

    public FreestyleConfigurationPage waitInvisibilityCreateItemPage() {
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.id("createItem")));

        return new FreestyleConfigurationPage(getDriver());
    }

    public OrganizationFolderConfigurePage selectOrganizationFolderAndClickOk() {
        TestUtils.scrollAndClickWithJS(getDriver(),
                getDriver().findElement(By.xpath("//span[text()='Organization Folder']")));
        getDriver().findElement(By.id("ok-button")).click();

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

    public String getCopyFromText() {
        WebElement copyFromTextBlock = getDriver().findElement(By.xpath("//div[@class='add-item-copy']"));
        return copyFromTextBlock.getText().trim();
    }
}
