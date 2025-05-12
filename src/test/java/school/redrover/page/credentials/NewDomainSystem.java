package school.redrover.page.credentials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

public class NewDomainSystem extends BasePage {

    @FindBy(xpath = "//button[@type='submit' and @name='Submit']")
    private WebElement createButton;

    @FindBy(xpath = "//*[@id='main-panel']/div[1]/div[1]/h1")
    private WebElement textDomainHeader;

    @FindBy(xpath = "//input[@name='_.name']")
    private WebElement nameDomain;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement nameDescription;

    public NewDomainSystem (WebDriver driver) {
        super(driver);
    }

    public NewDomainSystem createNewDomain(String domainName, String description){
        nameDomain.sendKeys(domainName);
        nameDescription.sendKeys(description);
        createButton.click();

        return this;
    }

    public String textDomainLink(String name){
        return getDriver().findElement(By.xpath("//a[contains(text(), '" + name + "')]")).getText();
    }

    public String textDomainDescription(String description){
        return getDriver().findElement(By.xpath("//div[text()='" + description + "']")).getText();
    }

    public String textDomainHeader(){
        return textDomainHeader.getText();
    }
}