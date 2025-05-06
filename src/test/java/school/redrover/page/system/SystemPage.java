package school.redrover.page.system;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;

public class SystemPage extends BasePage {

    @FindBys({
            @FindBy(css = "h1"),
            @FindBy(xpath = "//*[@id='main-panel']/div[1]/div[1]/h1")
    })
    private WebElement h1;

    @FindBy(name = "_.numExecutors")
    private WebElement ofExecutors;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement buttonSave;

    public SystemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean isAccessSystemPage(){
        return h1.getText().equals("System");
    }
    public SystemPage clearOfExecutors(){
        ofExecutors.clear();

        return this;
    }

    public HomePage clickButtonSave(){
        buttonSave.click();

        return new HomePage(getDriver());
    }

    public SystemPage sendOfExecutors(String string){
        ofExecutors.sendKeys(string);

        return this;
    }

    public String getOfExecutors(){
        return ofExecutors.getDomProperty("value");
    }
}
