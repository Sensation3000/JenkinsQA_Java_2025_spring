package school.redrover.page.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import java.util.List;

public class UsersPage extends BasePage {

    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUserButton() {
        getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='addUser']"))).click();

        return new CreateUserPage(getDriver());
    }

    public List<WebElement> getUsersList() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Ошибка: sleep прерван", e);
        }

        return getWait5().until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//table[@id='people']/tbody/tr")));
    }

    public List<WebElement> getUserIdList() {
        return getWait10().until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='people']//a[starts-with(@href, 'user/')]")));
    }

    public String getUserIdTdText() {

        return getDriver().findElement(By.xpath("//tbody/tr[2]/td[2]")).getText();
    }

    public String getNameTdText() {

        return getDriver().findElement(By.xpath("//tbody/tr[2]/td[3]")).getText();
    }

    public UsersPage clickDeleteUserButton(String userName) {
        String xpath = String.format("//a[@data-url='user/%s/doDelete']", userName.toLowerCase());
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

        return this;
    }

    public UsersPage clickSubmitDeleteUserButton() {
        getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[@data-id='ok']"))).click();

        return this;
    }
}
