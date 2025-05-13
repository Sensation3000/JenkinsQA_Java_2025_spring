package school.redrover.page.multibranch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;

public class MultibranchProjectPage extends BasePage {

    @FindBy(id = "view-message")
    private WebElement descriptionText;

    public MultibranchProjectPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchConfigurationPage goToConfigurationPage() {
        getDriver().findElement(By.xpath("//a[@href='./configure']")).click();

        return new MultibranchConfigurationPage(getDriver());
    }

    public String getProjectName() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-panel']/h1")))
                .getText();
    }

    public String getDescription() {
        return descriptionText.getText();
    }

    public HomePage deleteMultiBranchPipeline(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-title='Delete Multibranch Pipeline']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-id='ok']"))).click();

        return new HomePage(getDriver());
    }
}