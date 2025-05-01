package school.redrover.page.signIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import school.redrover.common.BasePage;

public class SignInPage extends BasePage {

    @FindBy(xpath ="//h1[text()='Sign in to Jenkins']")
    private WebElement signIn;


    public SignInPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getSignIn(){
        return signIn.getText();
    }
}

