package school.redrover.page.loginerrorpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import school.redrover.common.BasePage;

public class LogInErrorPage extends BasePage {

    @FindBy (className ="app-sign-in-register__error")
    private WebElement  logInErrorText;

    public LogInErrorPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean isErrorTextShown(){
        return logInErrorText.getText().equals("Invalid username or password");
    }
}
