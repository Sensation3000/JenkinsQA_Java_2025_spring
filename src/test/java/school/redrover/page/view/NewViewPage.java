package school.redrover.page.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;
import school.redrover.page.freestyle.FreestyleProjectPage;

public class NewViewPage extends BasePage {

    @FindBy(css = "label[for='hudson.model.MyView']")
    private WebElement myView;

    @FindBy(id = "hudson.model.ListView")
    private WebElement listView;

    @FindBy(id = "ok")
    private WebElement buttonOk;

    public NewViewPage(WebDriver driver) {
        super(driver);
    }

    public NewViewPage addName(String name) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(name);

        return this;
    }

    public NewViewPage clickListView() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("label[for='hudson.model.ListView']"))).click();

        return this;
    }

    public NewViewPage clickMyView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(myView)).click();

            return this;
    }

    public BasePage clickCreateButton() {
        BasePage nextPage = listView.isSelected()
                ? new EditViewPage(getDriver())
                : new HomePage(getDriver());

        getWait5().until(ExpectedConditions.elementToBeClickable(buttonOk)).click();
        return nextPage;
    }
}
