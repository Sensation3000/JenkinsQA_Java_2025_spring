package school.redrover.page.credentials;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

public class DomainFolderPage extends BasePage {
    @FindBy(css = "a[href*='domain']")
    private WebElement domainName;

    public DomainFolderPage (WebDriver driver) {
        super(driver);
    }

    public GlobalCredentialsPage clickDomainFolderName() {
        domainName.click();

        return new GlobalCredentialsPage(getDriver());
    }
}


