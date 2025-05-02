package school.redrover.page.signIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;
import school.redrover.page.loginerrorpage.LogInErrorPage;


public class SignInPage extends BasePage {

    @FindBy(xpath = "//h1[text()='Sign in to Jenkins']")
    private WebElement signInText;

    @FindBy(name = "j_username")
    private WebElement userNameField;

    @FindBy(name = "j_password")
    private WebElement passwordField;

    @FindBy (name = "Submit")
    private WebElement signInButton;

    public SignInPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getSignInText(){
        return signInText.getText();
    }

    public SignInPage setPassword(String password){
        passwordField.sendKeys(password);
        return new SignInPage(getDriver());
    }

    public SignInPage setUserName(String username){
        userNameField.sendKeys(username);
        return new SignInPage(getDriver());
    }
    public HomePage clickSignInButton() {
        signInButton.click();
        return new HomePage(getDriver());
    }
    public LogInErrorPage clickSignInButtonUseWrongCredentials() {
        signInButton.click();
        return new LogInErrorPage(getDriver());
    }



    public HomePage goToHomePage(){
         return new HomePage(getDriver());
    }
}

