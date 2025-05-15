package school.redrover.page.credentials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

public class DomainFolderPage extends BasePage {

    @FindBy(css = "tr:nth-child(1) .model-link")
    private WebElement domainName;

    @FindBy(xpath = "//*[@id='main-panel']/div[1]/div[2]/a")
    private WebElement buttonAddDomain;

    public DomainFolderPage (WebDriver driver) {
        super(driver);
    }

    public GlobalCredentialsPage clickDomainFolderName() {
        domainName.click();

        return new GlobalCredentialsPage(getDriver());
    }

    public NewDomainSystem clickAddDomain() {
        buttonAddDomain.click();

        return new NewDomainSystem(getDriver());
    }
}


