package school.redrover.page.system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import school.redrover.common.BasePage;

public class SystemPage extends BasePage {

    @FindBys({
            @FindBy(css = "h1"),
            @FindBy(xpath = "//*[@id='main-panel']/div[1]/div[1]/h1")
    })
    private WebElement h1;

    public SystemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean isAccessSystemPage(){
        return h1.getText().equals("System");
    }
}
