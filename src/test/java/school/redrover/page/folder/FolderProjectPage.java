package school.redrover.page.folder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;
import school.redrover.page.credentials.CredentialsPage;
import school.redrover.page.newitem.NewItemPage;

import java.util.List;

public class FolderProjectPage extends BasePage {

    @FindBy(linkText = "Create a job")
    private WebElement newItemButton;

    public FolderProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        return getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText();
    }

    public String getDescription() {
        return getDriver().findElement(By.id("view-message")).getText();
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

    public HomePage selectFreestyleClickOkAndReturnToHomePage() {
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getHeader().clickLogo();

        return new HomePage(getDriver());
    }

    public FolderConfigurationPage clickConfigure() {
        getDriver().findElement(By.cssSelector("a[href*='configure']")).click();

        return new FolderConfigurationPage(getDriver());
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

    public CredentialsPage clickLeftSideCredentials() {
        getWait5()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/TestFolder/credentials']")))
                .click();

        return new CredentialsPage(getDriver());
    }
}
